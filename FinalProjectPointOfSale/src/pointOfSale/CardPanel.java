package pointOfSale;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CardPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;  //Added to satisfy the compiler
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static final String RECEIPT_PATH = "Files/Receipts";
	private static final String RECEIPT_LIST = RECEIPT_PATH + "/ReceiptList";
	private static final String[] tabText = {"", "Tip: ", "Card Number: ", "Experation Date (MMYY): "};
	
	private static JPanel tabPanel = new JPanel(new GridLayout(1,3));
	private static JTextField display = new JTextField("", 20);
	private static JPanel buttonPanel = new JPanel(new GridLayout(4,3));
	private static JPanel bottomPanel = new JPanel(new GridLayout(1,3));
	private static MenuButton tipButton = null, cardNumButton = null, cardExpButton = null;
	private static String tabStrings[] = {"","","",""};
	private static String current = "";
	private static int selection = 0;
	private static File receiptSave = null;
	private static String firstLine = "";
	private static boolean isAdmin;
	private static String swipe = "";
	private static KeyBarsListener listen = null;
	
	public CardPanel(boolean isAdmin__)
	{
		isAdmin = isAdmin__;
		tabPanel.removeAll();
		display.removeAll();
		display.removeKeyListener(listen);
		listen = new KeyBarsListener();
		display.addKeyListener(listen);
		buttonPanel.removeAll();
		bottomPanel.removeAll();
		this.removeAll();
		setLayout(new GridLayout(4, 1));
		setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, DARK_CHAMPAGNE));
		
		tipButton = new MenuButton("Tip","13", this);
		cardNumButton = new MenuButton("Card Number","14", this);
		cardExpButton = new MenuButton("Exp. Date","15", this);
		tabPanel.add(tipButton);
		tabPanel.add(cardNumButton);
		tabPanel.add(cardExpButton);
	
		
		buttonPanel.add(new MenuButton("1", "1", this));
		buttonPanel.add(new MenuButton("2", "2", this));
		buttonPanel.add(new MenuButton("3", "3", this));
		
		buttonPanel.add(new MenuButton("4", "4", this));
		buttonPanel.add(new MenuButton("5", "5", this));
		buttonPanel.add(new MenuButton("6", "6", this));
		
		buttonPanel.add(new MenuButton("7", "7", this));
		buttonPanel.add(new MenuButton("8", "8", this));
		buttonPanel.add(new MenuButton("9", "9", this));
		
		buttonPanel.add(new MenuButton("<-", "10", this));
		buttonPanel.add(new MenuButton("0", "0", this));
		buttonPanel.add(new MenuButton(".", "11", this));
		
		if(isAdmin) {
			bottomPanel.add(new MenuButton("VOID", "17", this));
		} else {
			Tools.addBlankSpace(bottomPanel, 1);
		}
		Tools.addBlankSpace(bottomPanel, 1);
		bottomPanel.add(new MenuButton("Done", "16", this));
		
		add(tabPanel);
		add(display);
		add(buttonPanel);
		add(bottomPanel);
		
	}
	
	private class KeyBarsListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if(e.getKeyChar() == '\n') {
				parseSwipe();
				swipe = "";
				if(firstLine.equalsIgnoreCase("OPEN"))
				{
					//get response
					String[] one = {getInvoiceNo() + "", getInvoiceNo() + "", "POS BRAVO v1.0", tabStrings[2], tabStrings[3], tabStrings[0], tabStrings[0]};
					Response response1 = new Response(1, one);
					saveTransaction(response1.getXML(), response1.getResponse(), 1);
					if(response1.getResponse().contains("Approved")) {
						ProcessPanel.closeReceipt("PROGRESS");
						System.out.println("HERE");
						tabStrings = new String[]{"","","",""};
						SystemInit.setTransactionScreen();
					} else {
						display.setText("Rejected");
						tabStrings[2] = tabStrings[3] = "";
						Tools.update(display);
					}
					return;
				}
				//System.out.println(tabStrings[2]);
			} else {
				swipe += e.getKeyChar();
			}
		}
	}
	
	private void parseSwipe() {
		Scanner regex = new Scanner(swipe);
		System.out.println(swipe);
		String temp = regex.findInLine(";\\d{10,20}=");
		tabStrings[2] = temp.substring(1, temp.length() - 1);
		regex.close();
		regex = new Scanner(swipe);
		temp = regex.findInLine("=\\d*");
		temp = temp.substring(1, 5);
		temp = temp.substring(2) + temp.subSequence(0, 2);
		tabStrings[3] = temp;
		regex.close();
	}
	
	public static void loadReciept(File receipt)
	{
		tabStrings = new String[]{"","","",""};
		receiptSave = receipt;
		Scanner reader = null;
		Scanner regex = null;
		try {
			reader = new Scanner(receipt);
			String read = reader.nextLine();
			firstLine = read;
			if(read != null)
				tipButton.setVisible(read.contains("OPEN") ? false : true);
			
			cardNumButton.setVisible(!tipButton.isVisible());
			cardExpButton.setVisible(!tipButton.isVisible());
			
			while(reader.hasNextLine()) {
				read = reader.nextLine();
				regex = new Scanner(read);
				if(read.toLowerCase().contains("total")) {
					tabStrings[0] = regex.findInLine("\\d*\\.\\d{0,2}");
					System.out.println(tabStrings[0]);
				} else if(read.toLowerCase().contains("tip")) {
					tabStrings[1] = regex.findInLine("\\d*\\.\\d{0,2}");
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selection = tipButton.isVisible() ? 1 : 2;
		current = tabStrings[selection];
		display.setText(tabText[selection] + tabStrings[selection]);
		Tools.update(display);
	}
	
	private boolean checkReady(String num, String exp)
	{
		System.out.println(num + " and " + exp);
		if(num.matches("\\d{10,17}") && exp.matches("\\d{4}"))
		{
			return true;
		}
		return false;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		int command = Integer.parseInt(event.getActionCommand());

		if (command < 10)
		{
			current += command;
		} else {
			switch(command)
			{
			case 10: 
				if(current.length() > 0) {
					current = current.substring(0,current.length() - 1);
				}
				break;
			case 11: if(!current.contains(".") && selection == 1) {
					current += ".";
				}
				break;
//			case 12: tabStrings[selection] = current;
//				selection = 0;
//				current = tabStrings[selection];
//				break;
			case 13: tabStrings[selection] = current;
				selection = 1;
				current = tabStrings[selection];
				break;
			case 14: tabStrings[selection] = current;
				selection = 2;
				current = tabStrings[selection];
				break;
			case 15: tabStrings[selection] = current;
				selection = 3;
				current = tabStrings[selection];
				break;
			case 16:
				tabStrings[selection] = current;
//				final String temp2 = tabStrings[2], temp3 = tabStrings[3];
//				
//				current = tabStrings[2];
//				System.out.println("HERE!");
//				System.out.println(temp2 + " first " + temp3);
				if(checkReady(tabStrings[2], tabStrings[3])) {
					//
					System.out.println(firstLine);
					if(firstLine.equalsIgnoreCase("OPEN"))
					{
						//get response
						String[] one = {getInvoiceNo() + "", getInvoiceNo() + "", "POS BRAVO v1.0", tabStrings[2], tabStrings[3], tabStrings[0], tabStrings[0]};
						Response response1 = new Response(1, one);
						saveTransaction(response1.getXML(), response1.getResponse(), 1);
						if(response1.getResponse().contains("Approved")) {
							ProcessPanel.closeReceipt("PROGRESS");
							System.out.println("HERE");
							tabStrings = new String[]{"","","",""};
							SystemInit.setTransactionScreen();
						} else {
							display.setText("Rejected");
							tabStrings[2] = tabStrings[3] = "";
							Tools.update(display);
						}
						return;
					} 
				}
				if(firstLine.equalsIgnoreCase("PROGRESS") && tabStrings[1].matches("\\d{0,}\\.?\\d{0,2}"))					{
				{
					updateTip(tabStrings[1]);
					String[] two = num2();
					two[3] = tabStrings[0];
					two[4] = tabStrings[0];
					two[5] = tabStrings[1];
					Response response2 = new Response(2, two);
					saveTransaction(response2.getXML(), response2.getResponse(), 2);
					if(response2.getResponse().contains("Approved")) {
						ProcessPanel.closeReceipt("SWIPED");
						tabStrings = new String[]{"","","",""};
						SystemInit.setTransactionScreen();
					} else {
						display.setText("Rejected");
						tabStrings[2] = tabStrings[3] = "";
						Tools.update(display);
					}
					return;
				}
				}
			break;
			case 17: if(firstLine.equalsIgnoreCase("VOID")) {
					Response response3 = new Response(3, num3());
					saveTransaction(response3.getXML(), response3.getResponse(), 3);
					if(response3.getResponse().contains("Approved")) {
						ProcessPanel.closeReceipt("VOIDED");
						tabStrings = new String[]{"","","",""};
						SystemInit.setTransactionScreen();
					} else {
						display.setText("Unable to Void");
						tabStrings[2] = tabStrings[3] = "";
						Tools.update(display);
					}
				}
			}
		}
		//for keyboard input you will have to parse the text in display when the tabs are changed
		System.out.println(current);
		display.setText(tabText[selection] + current);
		Tools.update(display);
		
	}
	
	private void saveTransaction(String sent, String response, int rev) {
		PrintWriter printer = null;
		
		try {
			printer = new PrintWriter("Files/Transaction/Sent/" + rev + "/" + receiptSave.getName() + ".xml");
			printer.println(sent);
			printer.flush();
			printer.close();
			printer = new PrintWriter("Files/Transaction/Response/" + rev + "/" + receiptSave.getName() + ".xml");
			printer.println(response);
			printer.flush();
			printer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void updateTip(String tip)
	{
		File file = receiptSave;
		Scanner reader = null;
		PrintWriter printer = null;
		String toPrint = "";
		
		try {
			reader = new Scanner(file);
			String read = reader.nextLine();
			toPrint += read;
			while(reader.hasNextLine())
			{
				read = reader.nextLine();
				if(read.contains("Tip"))
				{
					toPrint += ("\n" + Tools.toMoney(tip) + manualTab(Tools.toMoney(tip)) + "Tip").replaceAll("\\.\\.", ".");
				}
				else
				{
					toPrint += "\n" + read;
				}
			}
			reader.close();
			printer = new PrintWriter(file);
			printer.println(toPrint);
			printer.flush();
			printer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static int getInvoiceNo()
	{
		File dir = new File("Files/Transaction/Sent/1/");
		return dir.list().length + 1;
	}
	
	private String[] num2()
	{
		String toReturn[] = new String[8];
		
		Scanner reader = null;
		Scanner regex = null;
		
		try {
			reader = new Scanner(new File("Files/Transaction/Response/1/" + receiptSave.getName() + ".xml"));
			while(reader.hasNextLine())
			{
				String read = reader.nextLine();
				read = read.trim();
				regex = new Scanner(read);
				if(read.contains("AuthCode")) {
					toReturn[6] = regex.findInLine("<AuthCode>[\\da-zA-Z]*</AuthCode>");
					toReturn[6] = toReturn[6].substring("<AuthCode>".length(), toReturn[6].length() - "</AuthCode>".length());
				} else if(read.contains("AcqRefData")) {
					System.out.println(read);
					toReturn[7] = regex.findInLine("<AcqRefData>[\\d a-zA-Z]*</AcqRefData>");
					System.out.println(toReturn[7]);
					toReturn[7] = toReturn[7].substring("<AcqRefData>".length(), toReturn[7].length() - "</AcqRefData>".length());
				} else if(read.contains("RecordNo")) {
					toReturn[2] = regex.findInLine("<RecordNo>.*</RecordNo>");
					toReturn[2] = toReturn[2].substring("<RecordNo>".length(), toReturn[2].length() - "</RecordNo>".length());
				}
			}
			reader.close();
			reader = new Scanner(new File("Files/Transaction/Sent/1/" + receiptSave.getName() + ".xml"));
			while(reader.hasNextLine())
			{
				String read = reader.nextLine();
				read = read.trim();
				regex = new Scanner(read);
				if(read.contains("InvoiceNo")) {
					toReturn[0] = regex.findInLine("<InvoiceNo>[\\da-zA-Z]*</InvoiceNo>");
					toReturn[0] = toReturn[0].substring("<InvoiceNo>".length(), toReturn[0].length() - "</InvoiceNo>".length());
				} else if(read.contains("RefNo")) {
					toReturn[1] = regex.findInLine("<RefNo>[\\da-zA-Z]*</RefNo>");
					toReturn[1] = toReturn[1].substring("<RefNo>".length(), toReturn[1].length() - "</RefNo>".length());
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return toReturn;
	}
	
	private String[] num3() {
		String toReturn[] = new String[8];
		
		String file1 = "Files/Transaction/", file2 = "/" + receiptSave.getName() + ".xml";
		Scanner reader = null;
		Scanner regex = null;
		
		try {
			reader = new Scanner(new File(file1 + "Sent/1" + file2));
		} catch (FileNotFoundException e) {
			try {
				reader = new Scanner(new File(file1 + "Sent/2" + file2));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				System.out.println("HERE2");
				return null;
			}
		}
		while(reader.hasNextLine()) {
			String read = reader.nextLine();
			regex = new Scanner(read);
			if(read.contains("InvoiceNo")) {
				toReturn[0] = regex.findInLine("<InvoiceNo>[\\da-zA-Z]*</InvoiceNo>");
				toReturn[0] = toReturn[0].substring("<InvoiceNo>".length(), toReturn[0].length() - "</InvoiceNo>".length());
			} else if(read.contains("RefNo")) {
				//try getting from recieved
				toReturn[1] = regex.findInLine("<RefNo>[\\da-zA-Z]*</RefNo>");
				toReturn[1] = toReturn[1].substring("<RefNo>".length(), toReturn[1].length() - "</RefNo>".length());
			} else if(read.contains("Purchase")) {
				toReturn[4] = regex.findInLine("<Purchase>[\\d\\.]*</Purchase>");
				toReturn[4] = toReturn[4].substring("<Purchase>".length(), toReturn[4].length() - "</Purchase>".length());
			} else if(read.contains("Memo")) {
				toReturn[2] = regex.findInLine("<Memo>[\\da-zA-Z \\.]*</Memo>");
				toReturn[2] = toReturn[2].substring("<Memo>".length(), toReturn[2].length() - "</Memo>".length());
			} else if(read.contains("RecordNo")) {
				toReturn[3] = regex.findInLine("<RecordNo>.*</RecordNo>");
				toReturn[3] = toReturn[3].substring("<RecordNo>".length(), toReturn[3].length() - "</RecordNo>".length());
			} else if(read.contains("AuthCode")) {
				toReturn[7] = regex.findInLine("<AuthCode>[\\da-zA-Z]</AuthCode>");
				toReturn[7] = toReturn[7].substring("<AuthCode>".length(), toReturn[7].length() - "</AuthCode>".length());
			} else if(read.contains("AcqRefData")) {
				toReturn[5] = regex.findInLine("<AcqRefData>[\\da-zA-Z ]*</AcqRefData>");
				toReturn[5] = toReturn[5].substring("<AcqRefData>".length(), toReturn[5].length() - "</AcqRefData>".length());
			}
		}
		reader.close();
		try {
			reader = new Scanner(new File(file1 + "Response/1" + file2));
		} catch (FileNotFoundException e) {
			try {
				reader = new Scanner(new File(file1 + "Response/2" + file2));
			} catch (FileNotFoundException e1) {
				System.out.println("HERE3");
				return null;
			}
		}
		while(reader.hasNextLine()) {
			String read = reader.nextLine();
			regex = new Scanner(read);
			if(read.contains("InvoiceNo")) {
				toReturn[0] = regex.findInLine("<InvoiceNo>[\\da-zA-Z]*</InvoiceNo>");
				toReturn[0] = toReturn[0].substring("<InvoiceNo>".length(), toReturn[0].length() - "</InvoiceNo>".length());
			} else if(read.contains("RefNo")) {
				//try getting from recieved
				toReturn[1] = regex.findInLine("<RefNo>[\\da-zA-Z]*</RefNo>");
				toReturn[1] = toReturn[1].substring("<RefNo>".length(), toReturn[1].length() - "</RefNo>".length());
			} else if(read.contains("Purchase")) {
				toReturn[4] = regex.findInLine("<Purchase>[\\d\\.]*</Purchase>");
				toReturn[4] = toReturn[4].substring("<Purchase>".length(), toReturn[4].length() - "</Purchase>".length());
			} else if(read.contains("Memo")) {
				toReturn[2] = regex.findInLine("<Memo>[\\da-zA-Z \\.]*</Memo>");
				toReturn[2] = toReturn[2].substring("<Memo>".length(), toReturn[2].length() - "</Memo>".length());
			} else if(read.contains("RecordNo")) {
				toReturn[3] = regex.findInLine("<RecordNo>.*</RecordNo>");
				toReturn[3] = toReturn[3].substring("<RecordNo>".length(), toReturn[3].length() - "</RecordNo>".length());
			} else if(read.contains("ProcessData")) {
				toReturn[6] = regex.findInLine("<ProcessData>[\\d\\|]*</ProcessData>");
				toReturn[6] = toReturn[6].substring("<ProcessData>".length(), toReturn[6].length() - "</ProcessData>".length());
			} else if(read.contains("AuthCode")) {
				toReturn[7] = regex.findInLine("<AuthCode>[\\da-zA-Z]*</AuthCode>");
				toReturn[7] = toReturn[7].substring("<AuthCode>".length(), toReturn[7].length() - "</AuthCode>".length());
			} else if(read.contains("AcqRefData")) {
				toReturn[5] = regex.findInLine("<AcqRefData>[\\da-zA-Z ]*</AcqRefData>");
				toReturn[5] = toReturn[5].substring("<AcqRefData>".length(), toReturn[5].length() - "</AcqRefData>".length());
			}
			
			
		}
		reader.close();
		
		return toReturn;
	}
	
	private static String manualTab(String entry)
	{
		String tab = "";
		for(int count=0; count < 15 - entry.length(); count++)
			tab += " ";
		return tab;
	}
}
