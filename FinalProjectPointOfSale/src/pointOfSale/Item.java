package pointOfSale;

/**
 * 
 * @author Stephen Collins, Vanessa Harris, Kolter Bradshaw, Cristhian Ramirez
 * (Date: 4/24/2013) 
 * Purpose: 
 *
 */
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
	public void setPrice(String newPrice)
	{
		itemPrice = newPrice;
	}
	public void setName(String newName)
	{
		itemName = newName;
	}
	public void addItem(String newPrice, String newName)
	{
		itemPrice = newPrice;
		itemName = newName;
		isActive = true;
	}
	public void deleteItem()
	{
		itemPrice = "Empty";
		itemName = "Empty";
		isActive = false;
	}
}
