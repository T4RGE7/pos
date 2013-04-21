package pointOfSale;

public class Item implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;  //Added to satisfy compiler
	private String itemName;
	private String itemPrice;
	private String itemNumber;
	private boolean isActive;
	
	Item(String newPrice, String newName, String newNumber, boolean activeState)
	{
		itemPrice = newPrice;
		itemName = newName;
		itemNumber = newNumber;
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
	public String getNumber()
	{
		return itemNumber;
	}
}
