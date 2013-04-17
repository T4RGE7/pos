package pointOfSale;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReceiptPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private static final String RECEIPT_PATH = "Files/Receipts/";
	private static final String RECEIPT_LIST_FILE = RECEIPT_PATH + "ReceiptList";
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static final  Color PALE_GOLDENROD = new Color(238,232,170);
	private static JTextArea receiptArea = new JTextArea("",501,51);
	private static String receiptItems = "";
	private static String receiptTotal = "";
	private static int totalDollars = 0;
	private static int totalCents = 0;
	
	ReceiptPanel()
	{
		setBorder(BorderFactory.createMatteBorder(10,10,10,10,DARK_CHAMPAGNE));
		setLayout(new GridLayout(1,1));
		setBackground(DARK_CHAMPAGNE);
		
		receiptArea.setEditable(false);
		receiptArea.setBackground(PALE_GOLDENROD);
		add(receiptArea);
	}
	public static void addItem(String itemLine, String price)
	{
		receiptItems = receiptItems + itemLine + "\n";
		
		totalDollars = totalDollars + Integer.parseInt(price.substring(0,price.indexOf(".")));
		totalCents = totalCents + Integer.parseInt(price.substring(price.indexOf(".")+1));
		if (totalCents >= 100)
		{
			totalCents = totalCents - 100;
			totalDollars++;
		}
		if(totalCents > 9)
			receiptTotal = "\t$" + String.valueOf(totalDollars) + "." + String.valueOf(totalCents);
		else
			receiptTotal = "\t$" + String.valueOf(totalDollars) + ".0" + String.valueOf(totalCents);
		
		receiptArea.setText(receiptItems
							+ "\n"
							+ receiptTotal + "\tTotal");
	}
	public static void deleteItem(String itemNumber, JTextField deleteField)
	{
		if(receiptItems.indexOf("["+itemNumber+"]") > -1)
		{
			int beginIndex = receiptItems.indexOf("["+itemNumber+"]");
			int endIndex = receiptItems.substring(beginIndex).indexOf("\n") + beginIndex + 1;
			String price = receiptItems.substring(beginIndex,endIndex).trim();
			price = price.substring(price.indexOf("$")+1);
			price = price.substring(0,price.indexOf("\t"));
			
			receiptItems = receiptItems.substring(0,beginIndex) + receiptItems.substring(endIndex);
			
			totalDollars = totalDollars - Integer.parseInt(price.substring(0,price.indexOf(".")));
			totalCents = totalCents - Integer.parseInt(price.substring(price.indexOf(".")+1));
			if(totalCents < 0)
			{
				totalCents = totalCents + 100;
				totalDollars--;
			}
			if(totalCents > 9)
				receiptTotal = "\t$" + String.valueOf(totalDollars) + "." + String.valueOf(totalCents);
			else
				receiptTotal = "\t$" + String.valueOf(totalDollars) + ".0" + String.valueOf(totalCents);
			
			receiptArea.setText(receiptItems
					+ "\n"
					+ receiptTotal + "\tTotal");
			
			deleteField.setText("");
		}
		else
			deleteField.setText("NOT FOUND");
		
		if(receiptItems.equals(""))
		{
			receiptTotal = "";
			receiptArea.setText("");
			totalDollars = 0;
			totalCents = 0;
		}
	}
	public static void clearReceipt()
	{
		receiptItems = "";
		receiptTotal = "";
		totalDollars = 0;
		totalCents = 0;
		receiptArea.setText("");
	}
	public static void saveReceipt()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
		Date date = new Date();
		String receiptContentFile = dateFormat.format(date);
		
		PrintWriter listWriter = null;
		PrintWriter contentWriter = null;
		
		try
		{
			listWriter = new PrintWriter(new FileOutputStream(RECEIPT_LIST_FILE, true));
			contentWriter = new PrintWriter(RECEIPT_PATH + receiptContentFile);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		listWriter.println(receiptContentFile);
		contentWriter.println(receiptItems
							+ "\n"
							+ receiptTotal + "\tTotal");
		listWriter.close();
		contentWriter.close();
		clearReceipt();
	}
	public static void loadReceipt(String receiptFile)
	{
		clearReceipt();
		
		receiptFile = RECEIPT_PATH + receiptFile;
		
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new File(receiptFile));
			receiptArea.setText("");
			while(inputStream.hasNextLine())
				receiptArea.setText(receiptArea.getText() + inputStream.nextLine() + "\n");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		
		
	}
}
