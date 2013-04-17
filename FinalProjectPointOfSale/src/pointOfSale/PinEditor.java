package pointOfSale;
import javax.swing.*;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PinEditor extends JPanel
{
	private static final long serialVersionUID = 1L;  //Added to satisfy compiler
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static final String SECURITY_FILE = "Files/System/SecurityCodes";
	
	private JPanel optionPanel = new JPanel(new GridLayout(8,1));
	private JTextField userField = new JTextField("",31);
	private JTextField pinField = new JTextField("",11);
	private JTextArea userArea = new JTextArea("",20,41);
	private MenuButton addButton = new MenuButton("Add","Add");
	private MenuButton deleteButton = new MenuButton("Delete","Delete");
	private String[] accessOption = {"Server", "Administrator"};
	private String pinText = "";
	private int users = 0;
	private JComboBox accessBox = new JComboBox(accessOption);
	
	PinEditor()
	{
		setLayout(new GridLayout(2,1));
		setBorder(BorderFactory.createMatteBorder(10,10,10,10,DARK_CHAMPAGNE));
		setBackground(DARK_CHAMPAGNE);
		
		accessBox.setSelectedIndex(0);
		
		readSecurityFile();
		
		userArea.setRows(users);
		userArea.setText(pinText);
		
		optionPanel.add(accessBox);
		optionPanel.add(userField);
		optionPanel.add(addButton);
		Tools.addBlankSpace(optionPanel, 1);
		optionPanel.add(pinField);
		optionPanel.add(deleteButton);
		Tools.addBlankSpace(optionPanel, 2);
		
		add(optionPanel);
		add(new JScrollPane(userArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
	}
	private void readSecurityFile()
	{
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new File(SECURITY_FILE));
			
			while(inputStream.hasNextLine())
			{
				String line = inputStream.nextLine().trim();
				if(line.equals(""))
					;
				else
				{
					pinText = pinText + line + "\n";
					users++;
				}
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
		}
	}
	
	private void saveSecurityFile()
	{
		
	}
	
}
