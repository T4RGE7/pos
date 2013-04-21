package pointOfSale;

public class Item implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;  //Added to satisfy compiler
	private String itemName;
	private String itemPrice;
	private boolean isActive;
	
	Item(String newPrice, String newName, boolean activeState)
	{
		itemPrice = newPrice;
		itemName = newName;
		isActive = activeState;
	}
	public String getPrice()
	{
		return itemPrice;
	}
	public String getName()
	{
		return itemName;
	}
	public boolean isActive()
	{
		return isActive;
	}
}
