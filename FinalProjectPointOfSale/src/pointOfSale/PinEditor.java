package pointOfSale;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PinEditor extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;  //Added to satisfy compiler
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static final String SECURITY_FILE = "Files/System/SecurityCodes";
	
	private JPanel optionPanel = new JPanel(new GridLayout(6,1));
	private JPanel addPanel = new JPanel(new GridLayout(1,2));
	private JPanel deletePanel = new JPanel(new GridLayout(1,2));
	private JTextField userField = new JTextField("Enter User Name",31);
	private JLabel titleLabel = new JLabel("Edit Security Codes", SwingConstants.CENTER);
	private JLabel addLabel = new JLabel("Add user:", SwingConstants.LEFT);
	private JLabel deleteLabel = new JLabel("Delete user (Select user from the list below):", SwingConstants.LEFT);
	private String[] accessOption = {"Server", "Administrator"};
	private JComboBox<String> accessBox = new JComboBox<String>(accessOption);
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JList<String> userList = new JList<String>(listModel);
	private int administrators=0;
	
	PinEditor()
	{
		setLayout(new GridLayout(2,1));
		setBorder(BorderFactory.createMatteBorder(10,10,10,10,DARK_CHAMPAGNE));
		setBackground(DARK_CHAMPAGNE);
		
		titleLabel.setVerticalAlignment(SwingConstants.TOP);
		titleLabel.setFont(new Font(Font.SERIF, Font.BOLD, 24));
		addLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		addLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 18));
		deleteLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		deleteLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 18));
		
		accessBox.setSelectedIndex(0);
		
		readSecurityFile();
		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		addPanel.add(new MenuButton("Add","Add",this));
		addPanel.add(userField);
		
		deletePanel.setBackground(DARK_CHAMPAGNE);
		deletePanel.add(new MenuButton("Delete","Delete",this));
		Tools.addBlankSpace(deletePanel,1);
		
		optionPanel.setBackground(DARK_CHAMPAGNE);
		optionPanel.add(titleLabel);
		optionPanel.add(addLabel);
		optionPanel.add(accessBox);
		optionPanel.add(addPanel);
		optionPanel.add(deleteLabel);
		optionPanel.add(deletePanel);
		
		add(optionPanel);
		add(new JScrollPane(userList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if(event.getActionCommand().equals("Add"))
		{
			String name = userField.getText().trim();
			if (name.equals(""))
				JOptionPane.showMessageDialog(null, "Invalid User Name");
			else
			{
				String key = randomKeyGenerator();
				while(isDuplicateKey(key))
					key = randomKeyGenerator();
				listModel.addElement(key + " " + accessOption[accessBox.getSelectedIndex()].substring(0,1)
						+ " " + name);
				saveSecurityFile();
				userField.setText("");
			}
		}
		else if(event.getActionCommand().equals("Delete") && userList.getSelectedIndex() > -1)
		{
			String user = userList.getSelectedValue();
			
			if(user.substring(7,8).equals("A") && administrators == 1)
				JOptionPane.showMessageDialog(null,"ERROR: Must Maintain At Least One Administrator Account");
			else
			{
				listModel.removeElementAt(userList.getSelectedIndex());
				if(user.substring(7,8).equals("A"))
					administrators--;
				saveSecurityFile();
			}
		}
			
	}
	private String randomKeyGenerator()
	{
		String key = String.valueOf(Math.round(Math.random() * 1000000));
		while(key.length() < 6)
			key = "0" + key;
		return key;
	}
	private boolean isDuplicateKey(String key)
	{
		int index = -1;
		for(int count=0; count < listModel.getSize() && index == -1; count++)
			listModel.getElementAt(count).indexOf(key);
		return index > -1;
	}
	private void readSecurityFile()
	{
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new File(SECURITY_FILE));
			
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
			{
				listModel.addElement(line);
				if(line.substring(7,8).equals("A"))
					administrators++;
			}
		}
		inputStream.close();
	}
	
	private void saveSecurityFile()
	{
		PrintWriter outputStream = null;
		try
		{
			outputStream = new PrintWriter(new File(SECURITY_FILE));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		String output = "";
		for(int count=0; count < listModel.getSize(); count++)
			output = output + listModel.getElementAt(count) + "\n";
		outputStream.print(output);
		outputStream.close();
	}
	
}
