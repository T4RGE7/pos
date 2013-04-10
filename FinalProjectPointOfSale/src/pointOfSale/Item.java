package pointOfSale;

public class Item 
{
	private String itemName;
	private String itemPrice;
	private String itemNumber;
	
	Item(String newPrice, String newName, String newNumber)
	{
		itemPrice = newPrice;
		itemName = newName;
		itemNumber = newNumber;
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
