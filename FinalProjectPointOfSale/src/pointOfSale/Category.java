package pointOfSale;

public class Category implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;  //Added to satisfy compiler
	
	private String categoryName;
	private boolean isActive;
	
	Category(String newName, boolean activeState)
	{
		categoryName = newName;
		isActive = activeState;
	}
	
	public String getCategoryName()
	{
		return categoryName;
	}
	public boolean isActive()
	{
		return isActive;
	}
	public void setCategoryName(String newName)
	{
		categoryName = newName;
	}
	public void addCategory(String newName)
	{
		categoryName = newName;
		isActive = true;
	}
	public void deleteCategory()
	{
		categoryName = "Empty";
		isActive = false;
	}
}
