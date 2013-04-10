package pointOfSale;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	
	private MenuButton[] button = new MenuButton[32];
	private Category[] itemCategory = new Category[32];
	private Item[][] menuItem = new Item[32][32];

	ItemPanel(Category[] newCategory, Item[][] newMenu)
	{
		setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, DARK_CHAMPAGNE));
		setBackground(DARK_CHAMPAGNE);
		setLayout(new GridLayout(8,4));
		itemCategory = newCategory;
		menuItem = newMenu;
		initializeButtons();
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
			String itemLine = "[" + menuItem[category][item].getNumber() + "]\t"
					+ "$" + menuItem[category][item].getPrice() + "\t"
					+ menuItem[category][item].getName();
			
			ReceiptPanel.addItem(itemLine, menuItem[category][item].getPrice());
		}
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
					itemCategory[count].getCategoryNumber());
			add(button[count]);
			button[count].addActionListener(this);
			count++;
		}
		while(count < 32)
		{
			button[count] = new MenuButton("null","null");
			button[count].setVisible(false);
			add(button[count]);
			button[count].addActionListener(this);
			count++;
		}
	}
}
