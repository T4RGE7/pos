package pointOfSale;
import javax.swing.*;
import javax.swing.table.*;
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
	
	private static DefaultTableModel tableModel = new DefaultTableModel(0,2);
	private static JTable receiptTable = new JTable(tableModel);
	private JScrollPane receiptPane = new JScrollPane(receiptTable, 
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	private static int totalDollars = 0;
	private static int totalCents = 0;
	
	ReceiptPanel()
	{
		setBorder(BorderFactory.createMatteBorder(10,10,10,10,DARK_CHAMPAGNE));
		setLayout(new GridLayout(1,1));
		setBackground(DARK_CHAMPAGNE);
		
		receiptTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		receiptTable.setBackground(PALE_GOLDENROD);
		receiptTable.setGridColor(PALE_GOLDENROD);
		
		receiptPane.setBackground(DARK_CHAMPAGNE);
		receiptPane.getViewport().setBackground(PALE_GOLDENROD);
		
		add(receiptPane);
	}
	public static void addItem(String itemPrice, String itemName)
	{
		tableModel.addRow(new String[] {"$"+itemPrice, itemName});
		
		totalDollars = totalDollars + Integer.parseInt(itemPrice.substring(0,itemPrice.indexOf(".")));
		totalCents = totalCents + Integer.parseInt(itemPrice.substring(itemPrice.indexOf(".")+1));
		if (totalCents >= 100)
		{
			totalCents = totalCents - 100;
			totalDollars++;
		}
	}
	public static void deleteItem(String itemNumber, JTextField deleteField)
	{
		
	}
	public static void clearReceipt()
	{
		totalDollars = 0;
		totalCents = 0;
	}
	public static void saveReceipt()
	{
		
	}
	public static void loadReceipt(String receiptFile)
	{
		
	}
}
