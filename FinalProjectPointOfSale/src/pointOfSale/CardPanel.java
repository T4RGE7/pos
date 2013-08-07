package pointOfSale;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CardPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;  //Added to satisfy the compiler
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static final String RECEIPT_PATH = "Files/Receipts";
	private static final String RECEIPT_LIST = RECEIPT_PATH + "/ReceiptList";
	
	private static JPanel tabPanel = new JPanel(new GridLayout(1,3));
	private static JTextField display = new JTextField("", 20);
	private static JPanel buttonPanel = new JPanel(new GridLayout(4,3));
	private static JPanel bottomPanel = new JPanel(new GridLayout(1,3));
	private static String tabStrings[] = {"","","",""};
	private static String current = "";
	private static int selection = 0;
	private static File receiptSave = null;
	
	
	public CardPanel()
	{
		tabPanel.removeAll();
		display.removeAll();
		buttonPanel.removeAll();
		bottomPanel.removeAll();
		this.removeAll();
		setLayout(new GridLayout(4, 1));
		setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, DARK_CHAMPAGNE));
		
		tabPanel.add(new MenuButton("Tip","13", this));
		tabPanel.add(new MenuButton("Card Number","14", this));
		tabPanel.add(new MenuButton("Exp. Date","15", this));
	
		
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
		
		Tools.addBlankSpace(bottomPanel, 2);
		bottomPanel.add(new MenuButton("Done", "16", this));
		
		add(tabPanel);
		add(display);
		add(buttonPanel);
		add(bottomPanel);
		
	}
	
	public static void loadReciept(File receipt)
	{
		receiptSave = receipt;
		Scanner reader = null;
		Scanner regex = null;
		try {
			reader = new Scanner(receipt);
			while(reader.hasNextLine()) {
				String read = reader.nextLine();
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
		selection = 1;
		current = tabStrings[1];
		display.setText(tabStrings[1]);
		Tools.update(display);
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
			case 10: current = current.substring(0,current.length() - 1);
				break;
			case 11: if(!current.contains(".")) {
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
			}
		}
		
		display.setText(current);
		Tools.update(display);
		
	}

	
	
}
