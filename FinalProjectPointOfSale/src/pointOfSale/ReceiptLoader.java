package pointOfSale;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReceiptLoader extends JPanel
{
	private static final long serialVersionUID = 1L;  //Added to satisfy the compiler
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static final String RECEIPT_LIST = "Files/Receipts/ReceiptList";
	
	private JPanel upperPanel = new JPanel(new GridLayout(6,1));
	private JPanel headerPanel = new JPanel(new GridLayout(1,2));
	private JTextField entryField = new JTextField(11);
	private JTextArea receiptArea = new JTextArea(20,41);
	private MenuButton loadButton = new MenuButton("Load","Load");
	private String receiptText = "";
	private int receipts = 1;
	
	ReceiptLoader()
	{
		setLayout(new GridLayout(2,1));
		setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, DARK_CHAMPAGNE));
		
		readReceipts();
		
		if(receipts > 20)
			receiptArea.setRows(receipts);
		receiptArea.setText(receiptText);
		receiptArea.setEditable(false);
		
		headerPanel.setBackground(DARK_CHAMPAGNE);
		headerPanel.add(new JLabel("Enter Receipt to Load:",SwingConstants.LEFT));
		headerPanel.add(loadButton);
		
		upperPanel.setBackground(DARK_CHAMPAGNE);
		Tools.addBlankSpace(upperPanel,2);
		upperPanel.add(headerPanel);
		upperPanel.add(entryField);
		Tools.addBlankSpace(upperPanel,2);
		
		add(upperPanel);
		add(new JScrollPane(receiptArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
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
			String line = inputStream.nextLine();
			
			if(line.equals(""))
				;
			else
			{
				receiptText = receiptText + "[" + receipts + "]\t" + line + "\n";
				receipts++;
			}
		}
	}
}
