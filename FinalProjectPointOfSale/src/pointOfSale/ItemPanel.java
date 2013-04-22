package pointOfSale;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ItemPanel extends JPanel implements ActionListener, MouseListener
{
	private static final long serialVersionUID = 1L;
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static final String CATEGORY_ARRAY = "Files/Menu/CategoryArray";
	private static final String ITEM_ARRAY = "Files/Menu/MenuItemArray";
	
	private MenuButton[] button = new MenuButton[32];
	private Category[] category = new Category[32];
	private Item[][] item = new Item[32][32];

	ItemPanel()
	{
		setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, DARK_CHAMPAGNE));
		setBackground(DARK_CHAMPAGNE);
		setLayout(new GridLayout(8,4));
		
		addMouseListener(this);
		
		initializeArrays();
		initializeButtons();
		displayCategories();
	}
	
	public void actionPerformed(ActionEvent event)
	{
		int index = event.getActionCommand().indexOf(".");
		
		if(index == -1)
			displayItems(Integer.parseInt(event.getActionCommand()));
		else
		{
			int catNumber = Integer.parseInt(event.getActionCommand().substring(0,index));
			int itemNumber = Integer.parseInt(event.getActionCommand().substring(index+1));
			ReceiptPanel.addItem(item[catNumber][itemNumber].getPrice(), item[catNumber][itemNumber].getName());
		}
	}
	public void mousePressed(MouseEvent event)
	{
		if(event.getButton() == MouseEvent.BUTTON3)
			displayCategories();
	}
	public void mouseClicked(MouseEvent event)
	{
	}
	public void mouseReleased(MouseEvent event)
	{
	}
	public void mouseEntered(MouseEvent event)
	{
	}
	public void mouseExited(MouseEvent event)
	{
	}
	public void displayCategories()
	{
		int count = 0;
		for(; category[count].isActive() && count < 32; count++)
		{
			button[count].setText(category[count].getCategoryName());
			button[count].setActionCommand(String.valueOf(count));
			button[count].setVisible(true);
		}
		for(; count < 32; count++)
			button[count].setVisible(false);
		
		Tools.update(this);
	}
	
	private void displayItems(int category)
	{
		int count = 0;
		for(; item[category][count].isActive() && count < 32; count++)
		{
			button[count].setText(item[category][count].getName());
			button[count].setActionCommand(String.valueOf(category) + "." + String.valueOf(count));
			button[count].setVisible(true);
		}
		for(; count<32; count++)
			button[count].setVisible(false);
			
		Tools.update(this);
	}
	
	private void initializeButtons()
	{
		for(int count=0; count<32; count++)
		{
			button[count] = new MenuButton("Empty","null",this);
			button[count].addMouseListener(this);
			button[count].setVisible(false);
			add(button[count]);
		}
	}
	
	private void initializeArrays()
	{
		ObjectInputStream readCategories = null;
		ObjectInputStream readItems = null;
		
		try
		{
			readCategories = new ObjectInputStream(new FileInputStream(CATEGORY_ARRAY));
			readItems = new ObjectInputStream(new FileInputStream(ITEM_ARRAY));
			
			category = (Category[]) readCategories.readObject();
			item = (Item[][]) readItems.readObject();

			readCategories.close();
			readItems.close();
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null,"ERROR: Arrays Not Loaded Correctly");
		}
		catch(ClassNotFoundException e)
		{
			JOptionPane.showMessageDialog(null,"ERROR: Local Array Class Not Found");
		}
	}
}
