package pointOfSale;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ReceiptPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private static final String RECEIPT_PATH = "Files/Receipts/";
	private static final String RECEIPT_LIST_FILE = RECEIPT_PATH + "ReceiptList";
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static final  Color PALE_GOLDENROD = new Color(238,232,170);
	
	private static DefaultListModel<String> listModel = new DefaultListModel<String>();
	private static JList<String> receiptList = new JList<String>(listModel);
	private static int subtotalAmount = 0;
	private static String subtotalString = "";
	
	ReceiptPanel()
	{
		setBorder(BorderFactory.createMatteBorder(10,10,10,10,DARK_CHAMPAGNE));
		setLayout(new GridLayout(1,1));
		setBackground(DARK_CHAMPAGNE);
		
		receiptList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		receiptList.setBackground(PALE_GOLDENROD);
		receiptList.setFont(new Font(Font.SERIF,Font.PLAIN,16));

		add(new JScrollPane(receiptList, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
	}
	public static void addItem(String itemPrice, String itemName)
	{
		subtotalAmount = subtotalAmount + Integer.parseInt(itemPrice.substring(0,itemPrice.indexOf("."))) * 100
				+ Integer.parseInt(itemPrice.substring(itemPrice.indexOf(".")+1));
		updateTotals();
		
		if(listModel.getSize() > 1)
			for(int count=0; count < 4; count++)
				listModel.removeElementAt(listModel.getSize()-1);
		listModel.addElement("$" + itemPrice + manualTab(itemPrice) + itemName);
		listModel.addElement(" ");
		listModel.addElement(subtotalString + manualTab(subtotalString) + "Subtotal");
		listModel.addElement("Tax");
		listModel.addElement("Total");
	}
	public static void deleteItem()
	{
		if(receiptList.getSelectedIndex() < listModel.getSize()-4)
		{
			String itemPrice = receiptList.getSelectedValue();
			itemPrice = itemPrice.substring(1,itemPrice.indexOf(" "));
			
			subtotalAmount = subtotalAmount - Integer.parseInt(itemPrice.substring(0,itemPrice.indexOf("."))) * 100
					- Integer.parseInt(itemPrice.substring(itemPrice.indexOf(".")+1));
			updateTotals();
			
			listModel.removeElementAt(receiptList.getSelectedIndex());
			if(listModel.getSize() == 4)
				clearReceipt();
			else
			{
				for(int count=0; count < 4; count++)
					listModel.removeElementAt(listModel.getSize()-1);
				listModel.addElement(" ");
				listModel.addElement(subtotalString + manualTab(subtotalString) + "Subtotal");
				listModel.addElement("Tax");
				listModel.addElement("Total");
			}
		}
	}
	public static void clearReceipt()
	{
		listModel.removeAllElements();
		subtotalAmount = 0;
	}
	public static void saveReceipt()
	{
		
	}
	public static void loadReceipt(String receiptFile)
	{
		
	}
	private static String manualTab(String entry)
	{
		String tab = "";
		for(int count=0; count < 15 - entry.length(); count++)
			tab += " ";
		return tab;
	}
	private static void updateTotals()
	{
		if(subtotalAmount % 100 > 9)
			subtotalString = "$" + String.valueOf(subtotalAmount/100) + "." + String.valueOf(subtotalAmount%100);
		else
			subtotalString = "$" + String.valueOf(subtotalAmount/100) + ".0" + String.valueOf(subtotalAmount%100);
	}
}
