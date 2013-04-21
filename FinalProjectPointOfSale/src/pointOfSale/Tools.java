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
	public static String toMoney(int number)
	{
		return toMoney(String.valueOf(number));
	}
}
