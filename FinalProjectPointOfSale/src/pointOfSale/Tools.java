package pointOfSale;

import javax.swing.JLabel;
import java.awt.Container;

public class Tools 
{
	/**
	 * Adds a specified number of blank spaces to a container in the form of invisible labels.  Intended
	 * to be used with the GridLayout layout manager when customizing the interface.
	 * @param contentPane Container which blank spaces will be added to
	 * @param spaces Number of blank spaces to add.
	 */
	public static void addBlankSpace(Container contentPane, int spaces)
	{
		for(;spaces > 0; spaces--)
		{
			blankSpace(contentPane);
		}
	}
	private static void blankSpace(Container contentPane)
	{
		JLabel blankSpace = new JLabel();
		contentPane.add(blankSpace);
		blankSpace.setVisible(false);
	}
	public static void update(Container contentPane)
	{
		contentPane.validate();
		contentPane.setVisible(false);
		contentPane.setVisible(true);
	}
	public static String toMoney(String number)
	{
		if(number.length() == 1)
			return "$.0" + number;
		else if(number.length() == 2)
			return "$." + number;
		else
			return "$" + number.substring(0,number.length()-2) + "." + number.substring(number.length()-2);
	}
	public static String toMoney(long number)
	{
		return toMoney(String.valueOf(number));
	}
	public static long toAmount(String price)
	{
		if(price.charAt(0) == '$')
			price = price.substring(1);
		price = price.substring(0,price.indexOf(".")) + price.substring(price.indexOf(".")+1);
		
		return Long.parseLong(price);
	}
	public static boolean isMoney(String amount)
	{
		if(amount.charAt(0) == '$' && amount.length() > 1)
			amount = amount.substring(1);
		System.out.println(amount.substring(amount.indexOf(".")));
		return amount.indexOf(".") > -1  &&
			   amount.indexOf(".") < amount.length()-1  &&
			   amount.substring(amount.indexOf(".")).length() == 3  &&
			   isNonNegativeNumber(amount);
	}
	public static boolean isNonNegativeNumber(String value)
	{
		try
		{
			return Double.parseDouble(value) >= 0;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
}
