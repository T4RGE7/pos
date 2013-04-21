package pointOfSale;

public class Category implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;  //Added to satisfy compiler
	
	private String categoryName;
	private String categoryNumber;
	private int categoryItems;
	private boolean isActive;
	
	Category(String newName, int newNumber, boolean activeState)
	{
		categoryName = newName;
		categoryNumber = String.valueOf(newNumber);
		categoryItems = 0;
		isActive = activeState;
	}
	
	public String getCategoryName()
	{
		return categoryName;
	}
	public String getCategoryNumber()
	{
		return categoryNumber;
	}
	public int getCategoryItems()
	{
		return categoryItems;
	}
	public boolean isActive()
	{
		return isActive;
	}
	public void setCategoryName(String newName)
	{
		categoryName = newName;
	}
	public void setCategoryNumber(int newNumber)
	{
		categoryNumber = String.valueOf(newNumber);
	}
	public void setActive(boolean activeState)
	{
		isActive = activeState;
	}
	public void addCategoryItem()
	{
		categoryItems++;
	}
}
