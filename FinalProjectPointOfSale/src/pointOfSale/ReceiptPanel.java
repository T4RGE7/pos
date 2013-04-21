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
	
	/**
	 * Constructs the ReceiptPanel.  Sets the initial conditions of the panel and the receiptList JList object.
	 * Adds a JScrollPane containing receiptList to this JPanel.
	 */
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
	/**
	 * Static method called by the ItemPanel class to add an item to the receiptList.
	 * Also adjusts the subtotals/totals to match the new receiptList elements.
	 * @param itemPrice (String) Price of user selected item
	 * @param itemName (String) Name of user selected item
	 */
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
	/**
	 * Called by the Delete button in the CheckOutPanel class to remove an item from the receiptList.
	 * Also adjusts the subtotals/totals to match the new receiptList elements.
	 */
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
	/**
	 * Removes all elements from the receiptList and resets the total price to zero.
	 */
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
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new File(RECEIPT_PATH + receiptFile));
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null,"File Not Found");
		}
		clearReceipt();
		while(inputStream.hasNextLine())
			listModel.addElement(inputStream.nextLine());
	}
	/**
	 * JLists do not recgonize the tab character, so this inserts a manual tab that, while not perfect,
	 * gets the job done.
	 * @param entry First charcter, which the "tab" will follow
	 * @return A variable number of blank spaces to act as a tab
	 */
	private static String manualTab(String entry)
	{
		String tab = "";
		for(int count=0; count < 15 - entry.length(); count++)
			tab += " ";
		return tab;
	}
	/**
	 * Changes the subtotal/total String representations to reflect the subtotal/total integer amount
	 */
	private static void updateTotals()
	{
		if(subtotalAmount % 100 > 9)
			subtotalString = "$" + String.valueOf(subtotalAmount/100) + "." + String.valueOf(subtotalAmount%100);
		else
			subtotalString = "$" + String.valueOf(subtotalAmount/100) + ".0" + String.valueOf(subtotalAmount%100);
	}
}
