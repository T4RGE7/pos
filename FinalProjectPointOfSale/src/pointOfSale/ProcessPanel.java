package pointOfSale;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class ProcessPanel  extends JPanel {

	private static final long serialVersionUID = 1L;  //Added to satisfy the compiler
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static final String RECEIPT_PATH = "Files/Receipts";
	private static final String RECEIPT_LIST = RECEIPT_PATH + "/ReceiptList";
	
	private JPanel upperPanel = new JPanel(new GridLayout(3,1));
	private JPanel buttonPanel = new JPanel(new GridLayout(2,2));
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JList<String> receiptList = new JList<String>(listModel);
	private JLabel titleLabel = new JLabel("Load Saved Receipts", SwingConstants.CENTER);
	private JLabel listLabel = new JLabel("Select Receipt from list below", SwingConstants.LEFT);
	
	
	public ProcessPanel()
	{
		setLayout(new GridLayout(2,1));
		setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, DARK_CHAMPAGNE));
		
		readReceipts();
		receiptList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
	}
	
	private void openReciept(String fileName) {
		
	}
	
	
	private void readReceipts()
	{
		Scanner inputStream = null;
		Scanner reader = null;
		try
		{
			inputStream = new Scanner(new File(RECEIPT_LIST));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		while(inputStream.hasNextLine())
		{
			String line = inputStream.nextLine().trim();
			System.out.println(RECEIPT_PATH + "/" + line);
			try {
				reader = new Scanner(new File(RECEIPT_PATH + "/" + line));
			} catch (FileNotFoundException e) {
				System.out.println("Reciept file not found");
				continue;
			}
			
			if(line.equals(""))
				;
			else {
				String read = reader.nextLine();
				System.out.println(read.matches("OPEN"));
				if(read.matches("OPEN")) {
					listModel.addElement(line);
				}
			}
		}
		reader.close();
		inputStream.close();
	}
	
}
