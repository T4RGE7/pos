package pointOfSale;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;

public class AdministratorGUI extends JPanel
{
	private static final long serialVersionUID = 1L; //Added to satisfy compiler
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	
	private JPanel quarterPanel1 = new JPanel(new GridLayout(2,1));
	private JPanel quarterPanel2 = new JPanel(new GridLayout(2,1));
	
	AdministratorGUI()
	{
		setLayout(new GridLayout(1,4));
		setBackground(DARK_CHAMPAGNE);
		setBorder(BorderFactory.createLoweredBevelBorder());
		
		ReceiptPanel.clearReceipt();
		
		quarterPanel1.add(new ReceiptLoader());
		quarterPanel1.add(new PinEditor());
		
		quarterPanel2.setBackground(DARK_CHAMPAGNE);
		Tools.addBlankSpace(quarterPanel2,1);
		quarterPanel2.add(new AdminButtonPanel());
		
		add(new ReceiptPanel());
		add(quarterPanel1);
		Tools.addBlankSpace(this,1);
		add(quarterPanel2);
	}
}
