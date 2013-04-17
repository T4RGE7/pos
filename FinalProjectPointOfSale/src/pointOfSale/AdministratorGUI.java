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
	
	private JPanel quarterPanel1 = new JPanel(new GridLayout(2,1));
	private JPanel quarterPanel2 = new JPanel(new GridLayout(2,1));
	private JPanel quarterPanel3 = new JPanel(new GridLayout(2,1));
	
	AdministratorGUI()
	{
		setLayout(new GridLayout(1,4));
		setBackground(DARK_CHAMPAGNE);
		setBorder(BorderFactory.createMatteBorder(20, 20, 20, 20, DARK_GREEN));
		
		ReceiptPanel.clearReceipt();
		
		quarterPanel1.setBackground(DARK_CHAMPAGNE);
		quarterPanel1.add(new ReceiptLoader());
		Tools.addBlankSpace(quarterPanel1, 1);
		
		quarterPanel2.add(new PinEditor());
		quarterPanel2.add(new AdminButtonPanel());
		
		add(new ReceiptPanel());
		add(quarterPanel1);
		Tools.addBlankSpace(this,1);
		add(quarterPanel2);
	}
}
