package pointOfSale;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ItemPanel extends JPanel implements ActionListener, MouseListener
{
	private static final long serialVersionUID = 1L;
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static final String CATEGORY_FILE = "Files/Menu/Categories";
	
	private MenuButton[] button = new MenuButton[32];
	private Category[] itemCategory = new Category[32];
	private Item[][] menuItem = new Item[32][32];

	ItemPanel()
	{
		setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, DARK_CHAMPAGNE));
		setBackground(DARK_CHAMPAGNE);
		setLayout(new GridLayout(8,4));
		
		initializeCategories();
		initializeButtons();
		
		addMouseListener(this);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		int index = event.getActionCommand().indexOf(".");
		
		if(index == -1)
			displayItems(Integer.parseInt(event.getActionCommand()));
		else
		{
			int category = Integer.parseInt(event.getActionCommand().substring(0,index));
			int item = Integer.parseInt(event.getActionCommand().substring(index+1));
			ReceiptPanel.addItem(menuItem[category][item].getPrice(), menuItem[category][item].getName());
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
	private void displayItems(int category)
	{
		int count = 0;
		while (count < itemCategory[category].getCategoryItems())
		{
			button[count].setText(menuItem[category][count].getName());
			button[count].setActionCommand(menuItem[category][count].getNumber());
			button[count].setVisible(true);
			count++;
		}
		while(count < 32)
		{
			button[count].setVisible(false);
			count++;
		}
		Tools.update(this);
	}
	
	public void displayCategories()
	{
		for(int count = 0; count < Category.getActiveCategories(); count++)
		{
			button[count].setText(itemCategory[count].getCategoryName());
			button[count].setActionCommand(itemCategory[count].getCategoryNumber());
			button[count].setVisible(true);
		}
		for(int count = Category.getActiveCategories(); count < 32; count++)
		{
			button[count].setVisible(false);
		}
		Tools.update(this);
	}
	
	private void initializeButtons()
	{
		int count = 0;
		while(count < Category.getActiveCategories())
		{
			button[count] = new MenuButton(itemCategory[count].getCategoryName(),
					itemCategory[count].getCategoryNumber(),this);
			button[count].addMouseListener(this);
			add(button[count]);
			count++;
		}
		while(count < 32)
		{
			button[count] = new MenuButton("null","null",this);
			button[count].addMouseListener(this);
			button[count].setVisible(false);
			add(button[count]);
			count++;
		}
	}
	private void initializeCategories()
	{
		Category.resetActiveCategories();
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new File(CATEGORY_FILE));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		int count = 0;
		while(inputStream.hasNextLine())
		{
			String line = inputStream.nextLine().trim();
			
			if(line.equals(""))
				;
			else
			{
				itemCategory[count] = new Category(line,count);
				initializeMenuItems(count);
				count++;
			}
		}
		inputStream.close();
	}
	private void initializeMenuItems(int categoryNumber)
	{
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new File(itemCategory[categoryNumber].getCategoryFile()));
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null,"ERROR: File Not Found");
		}
		
		int count = 0;
		while(inputStream.hasNextLine())
		{
			String line = inputStream.nextLine().trim();
			
			if(line.equals(""))
				;
			else
			{
				String price = line.substring(0, line.indexOf(" "));
				String name = line.substring(line.indexOf(" ")+1);
				String itemNumber = String.valueOf(categoryNumber) + "." + String.valueOf(count);
				
				menuItem[categoryNumber][count] = new Item(price,name,itemNumber);
				itemCategory[categoryNumber].addCategoryItem();
				count++;
			}
		}
	}
}
