package pointOfSale;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class MenuEditor extends JPanel implements ActionListener, ListSelectionListener
{
	private static final long serialVersionUID = 1L;  //Added to satisfy compiler
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static final String CATEGORY_ARRAY = "Files/Menu/CategoryArray";
	private static final String ITEM_ARRAY = "Files/Menu/MenuItemArray";
	
	private Category[] category = new Category[32];
	private Item[][] item = new Item[32][32];
	private DefaultListModel<String> categoryModel = new DefaultListModel<String>();
	private DefaultListModel<String> itemModel = new DefaultListModel<String>();
	private JList<String> categoryList = new JList<String>(categoryModel);
	private JList<String> itemList = new JList<String>(itemModel);
	private JTextField categoryNameField = new JTextField("",31);
	private JTextField itemNameField = new JTextField("",31);
	private JTextField itemPriceField = new JTextField("",31);
	private JLabel titleLabel = new JLabel("Edit Menu",SwingConstants.CENTER);
	private JLabel categoryLabel = null;
	private JLabel itemLabel = new JLabel("Select an Item (" + itemModel.getSize() +"/32)");
	private JPanel categoryPanel = new JPanel(new GridLayout(3,1));
	private JPanel categoryHeader = new JPanel(new GridLayout(2,1));
	private JPanel categoryLower = new JPanel(new GridLayout(3,1));
	private JPanel categoryButtons = new JPanel(new GridLayout(1,3));
	private JPanel categoryFieldPanel = new JPanel(new GridLayout(1,2));
	private JPanel itemPanel = new JPanel(new GridLayout(3,1));
	private JPanel itemLower = new JPanel(new GridLayout(3,1));
	private JPanel itemButtons = new JPanel(new GridLayout(1,3));
	private JPanel itemNameFieldPanel = new JPanel(new GridLayout(1,2));
	private JPanel itemPriceFieldPanel = new JPanel(new GridLayout(1,2));
	private int catIndex;
	private int itemIndex;
	private int activeCats;
	private int activeItems;
	
	MenuEditor()
	{
		setBackground(DARK_CHAMPAGNE);
		setBorder(BorderFactory.createMatteBorder(10,10,10,10,DARK_CHAMPAGNE));
		setLayout(new GridLayout(2,1));
		
		readArrays();
		for(int count=0; category[count].isActive() && count<32; count++)
			categoryModel.addElement(category[count].getCategoryName());
		
		categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		categoryList.addListSelectionListener(this);
		itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		titleLabel.setVerticalAlignment(SwingConstants.TOP);
		titleLabel.setFont(new Font(Font.SERIF,Font.BOLD,24));
		categoryLabel = new JLabel("Select a Category (" + categoryModel.getSize() + "/32)");
		categoryLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		categoryLabel.setFont(new Font(Font.SERIF,Font.ITALIC,18));
		itemLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		itemLabel.setFont(new Font(Font.SERIF,Font.ITALIC,18));
		
		categoryNameField.setBorder(BorderFactory.createMatteBorder(10,0,10,0,DARK_CHAMPAGNE));
		itemNameField.setBorder(BorderFactory.createMatteBorder(10,0,10,0,DARK_CHAMPAGNE));
		itemPriceField.setBorder(BorderFactory.createMatteBorder(10,0,10,0,DARK_CHAMPAGNE));
		
		categoryHeader.setBackground(DARK_CHAMPAGNE);
		categoryHeader.add(titleLabel);
		categoryHeader.add(categoryLabel);
		
		categoryButtons.add(new MenuButton("Add","CatAdd",this));
		categoryButtons.add(new MenuButton("Edit","CatEdit",this));
		categoryButtons.add(new MenuButton("Delete","CatDelete",this));
		
		categoryFieldPanel.setBackground(DARK_CHAMPAGNE);
		categoryFieldPanel.add(new JLabel("New Category Name:",SwingConstants.RIGHT));
		categoryFieldPanel.add(categoryNameField);
		
		categoryLower.setBackground(DARK_CHAMPAGNE);
		categoryLower.add(categoryButtons);
		categoryLower.add(categoryFieldPanel);
		Tools.addBlankSpace(categoryLower,1);
		
		categoryPanel.add(categoryHeader);
		categoryPanel.add(new JScrollPane(categoryList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
		categoryPanel.add(categoryLower);
		
		itemButtons.add(new MenuButton("Add","ItemAdd",this));
		itemButtons.add(new MenuButton("Edit","ItemEdit",this));
		itemButtons.add(new MenuButton("Delete","ItemDelete",this));
		
		itemNameFieldPanel.setBackground(DARK_CHAMPAGNE);
		itemNameFieldPanel.add(new JLabel("New Item Name:",SwingConstants.RIGHT));
		itemNameFieldPanel.add(itemNameField);
		
		itemPriceFieldPanel.setBackground(DARK_CHAMPAGNE);
		itemPriceFieldPanel.add(new JLabel("New Item Price:",SwingConstants.RIGHT));
		itemPriceFieldPanel.add(itemPriceField);
		
		itemLower.add(itemButtons);
		itemLower.add(itemNameFieldPanel);
		itemLower.add(itemPriceFieldPanel);
		
		itemPanel.setBackground(DARK_CHAMPAGNE);
		itemPanel.add(itemLabel);
		itemPanel.add(new JScrollPane(itemList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
		itemPanel.add(itemLower);
		
		add(categoryPanel);
		add(itemPanel);
	}
	public void valueChanged(ListSelectionEvent event)
	{
		try
		{
			catIndex = categoryList.getSelectedIndex();
		
			itemModel.removeAllElements();
			for(int count=0; item[catIndex][count].isActive() && count<32; count++)
				itemModel.addElement(Tools.toMoney(item[catIndex][count].getPrice()) + "     "
												 + item[catIndex][count].getName());
			
			itemLabel.setText("Select an Item (" + itemModel.getSize() +"/32)");
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			itemLabel.setText("Select an Item (" + 0 +"/32)");
		}
	}
	public void actionPerformed(ActionEvent event)
	{
		catIndex = categoryList.getSelectedIndex();
		itemIndex = itemList.getSelectedIndex();
		activeCats = categoryModel.getSize();
		activeItems = itemModel.getSize();
		
		if(event.getActionCommand().equals("CatAdd"))
		{
			String newName = categoryNameField.getText().trim();
			
			if(activeCats == 32)
				JOptionPane.showMessageDialog(null,"ERROR: Category Limit Reached");
			else if(newName.equals(""))
				JOptionPane.showMessageDialog(null,"ERROR: Invalid Category Name");
			else
			{
				category[activeCats].addCategory(newName);
				resetLists();
				categoryList.setSelectedIndex(categoryModel.getSize()-1);
			}
			categoryNameField.setText("");
		}
		if(event.getActionCommand().equals("CatDelete"))
		{
			for(int count = catIndex; count+1 < activeCats; count++)
				category[count].addCategory(category[count+1].getCategoryName());
			
			category[activeCats-1].deleteCategory();
			
			for(int count=0; count<32; count++)
				item[catIndex][count].deleteItem();
			
			resetLists();
		}
		if(event.getActionCommand().equals("CatEdit"))
		{
			String newName = categoryNameField.getText().trim();
			if(newName.equals(""))
				JOptionPane.showMessageDialog(null,"ERROR: Invalid Category Name");
			else
			{
				category[catIndex].setCategoryName(newName);
				resetLists();
			}
			categoryNameField.setText("");
		}
		if(event.getActionCommand().equals("ItemAdd"))
		{
			String newName = itemNameField.getText().trim();
			String newPrice = itemPriceField.getText().trim();
			
			if(activeItems == 32)
				JOptionPane.showMessageDialog(null,"ERROR: Item Limit Reached");
			else if(newName.equals("") || newPrice.equals(""))
				JOptionPane.showMessageDialog(null,"ERROR: Invalid Input");
			else if()
		}
		Tools.update(this);
	}
	private void resetLists()
	{
		categoryModel.removeAllElements();
		itemModel.removeAllElements();
		for(int count=0; category[count].isActive() && count<32; count++)
			categoryModel.addElement(category[count].getCategoryName());
		categoryLabel.setText("Select a Category (" + categoryModel.getSize() + "/32)");
	}
	private void readArrays()
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
	private void saveArrays()
	{
		ObjectOutputStream saveCategories = null;
		ObjectOutputStream saveItems = null;
		
		try
		{
			saveCategories = new ObjectOutputStream(new FileOutputStream(CATEGORY_ARRAY));
			saveItems = new ObjectOutputStream(new FileOutputStream(ITEM_ARRAY));
			
			saveCategories.writeObject(category);
			saveItems.writeObject(item);
			
			saveCategories.close();
			saveItems.close();
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null,"ERROR: Arrays Not Saved Correctly");
		}
	}
}
