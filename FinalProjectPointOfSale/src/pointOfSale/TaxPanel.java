package pointOfSale;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaxPanel extends JPanel
{
	private static final long serialVersionUID = 1L;  //Added to satisfy compiler
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static final String TAX_FILE = "Files/Tax/SalesTax";
	
	private JLabel panelLabel = new JLabel("Edit Sales Tax",SwingConstants.CENTER);
	private double salesTax=0;
	
	TaxPanel()
	{
		setLayout(new GridLayout(12,1));
		setBorder(BorderFactory.createMatteBorder(10,10,10,10,DARK_CHAMPAGNE));
		setBackground(DARK_CHAMPAGNE);
		
		readTax();
		
		panelLabel.setVerticalAlignment(SwingConstants.TOP);
		panelLabel.setFont(new Font(Font.SERIF, Font.BOLD, 24));
		
		add(panelLabel);
		Tools.addBlankSpace(this,11);
	}
	
	/**
	 * Private helper method which reads the local sales tax amount 
	 * from a text file and sets a double value equal to it.
	 */
	private void readTax()
	{

		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new File(TAX_FILE));
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null,"File Not Found");
		}
		while(inputStream.hasNext())
			salesTax = Double.parseDouble(inputStream.next());
	}
}
