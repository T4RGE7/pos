package pointOfSale;

public class Category 
{
	private final String FILE_PREFIX = "Files/Menu/Category";
	private static int activeCategories = 0;
	
	private String categoryName;
	private String categoryNumber;
	private String categoryFile;
	private int categoryItems;
	
	Category(String newName, int newNumber)
	{
		categoryName = newName;
		categoryNumber = String.valueOf(newNumber);
		categoryFile = FILE_PREFIX + String.valueOf(newNumber+1);
		categoryItems = 0;
		activeCategories++;
	}
	
	public String getCategoryName()
	{
		return categoryName;
	}
	public String getCategoryNumber()
	{
		return categoryNumber;
	}
	public String getCategoryFile()
	{
		return categoryFile;
	}
	public int getCategoryItems()
	{
		return categoryItems;
	}
	public void addCategoryItem()
	{
		categoryItems++;
	}
	public static int getActiveCategories()
	{
		return activeCategories;
	}
	public static void resetActiveCategories()
	{
		activeCategories = 0;
	}
}
