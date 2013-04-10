package pointOfSale;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TransactionGUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static final Color DARK_GREEN = new Color(0,100,0);
	private static final String CATEGORY_FILE = "Files/Menu/Categories";
	private static boolean adminPrivilege;
	
	private ItemPanel restaurantMenu = null;
	private JPanel halfPanel = new JPanel(new GridLayout(1,2));
	private JPanel quarterPanel = new JPanel(new GridLayout(2,1));
	private Category[] itemCategory = new Category[32];
	private Item[][] menuItem = new Item[32][32];
	
	TransactionGUI(boolean accessLevel)
	{
		setLayout(new GridLayout(1,2));
		setBackground(DARK_CHAMPAGNE);
		setBorder(BorderFactory.createMatteBorder(20, 20, 20, 20, DARK_GREEN));
		
		initializeCategories();
		restaurantMenu = new ItemPanel(itemCategory, menuItem);
		
		adminPrivilege = accessLevel;
		
		Tools.addBlankSpace(quarterPanel,1);
		quarterPanel.add(new CheckOutPanel(adminPrivilege,restaurantMenu));
		
		halfPanel.add(new ReceiptPanel());
		halfPanel.add(quarterPanel);
		
		add(halfPanel);
		add(restaurantMenu);
		
	}
	
	TransactionGUI()
	{
		this(adminPrivilege);
	}
	
	private void initializeCategories()
	{
		Category.resetActiveCategories();
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new File(CATEGORY_FILE));
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
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
		}
	}
	private void initializeMenuItems(int categoryNumber)
	{
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new File(itemCategory[categoryNumber].getCategoryFile()));
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
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		
	}
}
