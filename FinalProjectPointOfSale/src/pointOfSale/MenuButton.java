package pointOfSale;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.event.ActionListener;

/**
 * 
 * @author Stephen Collins, Vanessa Harris, Kolter Bradshaw, Cristhian Ramirez
 * (Date: 4/24/2013) 
 * Purpose: 
 *
 */
public class MenuButton extends JButton
{
	private static final long serialVersionUID = 1L;
	private static final  Color PALE_GOLDENROD = new Color(238,232,170);
	
	MenuButton(String label, String command, ActionListener listener)
	{
		setText(label);
		setActionCommand(command);
		setBackground(PALE_GOLDENROD);
		setBorder(BorderFactory.createRaisedBevelBorder());
		addActionListener(listener);
	}
}
