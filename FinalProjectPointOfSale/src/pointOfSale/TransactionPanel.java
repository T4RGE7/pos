package pointOfSale;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import nimaprinting.Print;

public class TransactionPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;  //Added to satisfy the compiler
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static final String RECEIPT_PATH = "Files/Receipts";
	private static final String RECEIPT_LIST = RECEIPT_PATH + "/ReceiptList";
	
	private JPanel upperPanel = new JPanel(new GridLayout(3,1));
	private JPanel buttonPanel = new JPanel(new GridLayout(2,2));
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JList<String> receiptList = new JList<String>(listModel);
	private JLabel titleLabel = new JLabel("Load Saved Transactions", SwingConstants.CENTER);
	private JLabel listLabel = new JLabel("Select Transaction from list below", SwingConstants.LEFT);
	
	public TransactionPanel()
	{
		setLayout(new GridLayout(2,1));
		setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, DARK_CHAMPAGNE));
		
		listModel.removeAllElements();
		readTransactions();
		receiptList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		titleLabel.setVerticalAlignment(SwingConstants.TOP);
		titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 24));
		listLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		listLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 18));
		
		buttonPanel.setBackground(DARK_CHAMPAGNE);
		buttonPanel.add(new MenuButton("Load","Load",this));
//		buttonPanel.add(new MenuButton("Delete","Delete",this));
//		buttonPanel.add(new MenuButton("Void", "Void", this));
//		buttonPanel.add(new MenuButton("Delete All","Delete All",this));
		buttonPanel.add(new MenuButton("Print", "Print", this));
		Tools.addBlankSpace(buttonPanel, 2);
		
		upperPanel.setBackground(DARK_CHAMPAGNE);
		upperPanel.add(titleLabel);
		upperPanel.add(buttonPanel);
		upperPanel.add(listLabel);
		
		add(upperPanel);
		add(new JScrollPane(receiptList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS));
	}
	
	public void readTransactions()
	{
		File response = new File("Files/Transaction/Response/");
		for(String num : response.list()) {
			File sub = new File("Files/Transaction/Response/" + num);
			for(String num1 : sub.list()) {
				listModel.addElement("Files/Transaction/Response/" + num + "/" + num1);
			}
		}
		response = new File("Files/Transaction/Sent/");
		for(String num : response.list()) {
			File sub = new File("Files/Transaction/Sent/" + num);
			for(String num1 : sub.list()) {
				listModel.addElement("Files/Transaction/Sent/" + num + "/" + num1);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getActionCommand().equals("Load") && receiptList.getSelectedIndex() > -1)
			ReceiptPanel.loadReceipt(receiptList.getSelectedValue());
		if(event.getActionCommand().equals("Print") && ReceiptPanel.getTransaction().length() > 0) {
			Scanner reader = null;
			try {
				reader = new Scanner(new File(ReceiptPanel.getTransaction()));
				String toPrint = reader.nextLine();
				while(reader.hasNextLine()) {
					toPrint += "\n" + reader.nextLine();
				}
				Print print = new Print(toPrint);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}

	
	
}
