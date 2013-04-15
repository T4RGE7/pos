package pointOfSale;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;

public class AdministratorGUI extends JPanel
{
	private static final long serialVersionUID = 1L; //Added to satisfy compiler
	private static final Color DARK_GREEN = new Color(0,100,0);
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	
	AdministratorGUI()
	{
		setLayout(new GridLayout(1,4));
		setBackground(DARK_CHAMPAGNE);
		setBorder(BorderFactory.createMatteBorder(20, 20, 20, 20, DARK_GREEN));
		
		add(new ReceiptPanel());
		Tools.addBlankSpace(this,3);
	}
}
