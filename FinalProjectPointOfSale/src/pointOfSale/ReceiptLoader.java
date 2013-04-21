package pointOfSale;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ReceiptLoader extends JPanel implements ActionListener
{
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
	
	ReceiptLoader()
	{
		setLayout(new GridLayout(2,1));
		setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, DARK_CHAMPAGNE));
		
		readReceipts();
		receiptList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		titleLabel.setVerticalAlignment(SwingConstants.TOP);
		titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 24));
		listLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		listLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 18));
		
		buttonPanel.setBackground(DARK_CHAMPAGNE);
		buttonPanel.add(new MenuButton("Load","Load",this));
		buttonPanel.add(new MenuButton("Delete","Delete",this));
		Tools.addBlankSpace(buttonPanel, 1);
		buttonPanel.add(new MenuButton("Delete All","Delete All",this));
		
		upperPanel.setBackground(DARK_CHAMPAGNE);
		upperPanel.add(titleLabel);
		upperPanel.add(buttonPanel);
		upperPanel.add(listLabel);
		
		add(upperPanel);
		add(new JScrollPane(receiptList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if(event.getActionCommand().equals("Load") && receiptList.getSelectedIndex() > -1)
			ReceiptPanel.loadReceipt(receiptList.getSelectedValue());
		if(event.getActionCommand().equals("Delete") && receiptList.getSelectedIndex() > -1)
			deleteReceipt();
		if(event.getActionCommand().equals("Delete All"))
			deleteAll();
	}
	
	private void readReceipts()
	{
		Scanner inputStream = null;
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
			
			if(line.equals(""))
				;
			else
				listModel.addElement(line);
		}
	}
	private void deleteReceipt()
	{
		File file = new File(RECEIPT_PATH + "/" + receiptList.getSelectedValue());
		file.delete();
		
		listModel.removeElementAt(receiptList.getSelectedIndex());
		
		saveReceiptList();
		ReceiptPanel.clearReceipt();
	}
	private void deleteAll()
	{
		File receiptDirectory = new File(RECEIPT_PATH);
		File[] file = receiptDirectory.listFiles();
		
		for(int count=0; count < file.length; count++)
			file[count].delete();
		listModel.removeAllElements();
		saveReceiptList();
		ReceiptPanel.clearReceipt();
	}
	private void saveReceiptList()
	{
		PrintWriter listWriter = null;
		try
		{
			listWriter = new PrintWriter(RECEIPT_LIST);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		for(int count = 0; count < listModel.getSize(); count++)
			listWriter.println(listModel.getElementAt(count));
		listWriter.close();
	}
}
