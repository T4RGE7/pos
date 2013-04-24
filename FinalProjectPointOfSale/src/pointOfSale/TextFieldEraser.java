package pointOfSale;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class TextFieldEraser implements MouseListener
{
	private boolean fieldClicked = false;
	
	public void mousePressed(MouseEvent event)
	{
		if(!fieldClicked)
		{
			((JTextField) event.getComponent()).setText("");
			fieldClicked = true;
		}
	}
	public void mouseClicked(MouseEvent event)
	{
	}
	public void mouseReleased(MouseEvent event)
	{
	}
	public void mouseEntered(MouseEvent event)
	{
	}
	public void mouseExited(MouseEvent event)
	{
	}
}
