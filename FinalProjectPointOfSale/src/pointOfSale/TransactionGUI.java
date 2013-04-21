package pointOfSale;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;

public class TransactionGUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static boolean adminPrivilege;
	
	private ItemPanel restaurantMenu = null;
	private JPanel halfPanel = new JPanel(new GridLayout(1,2));
	private JPanel quarterPanel = new JPanel(new GridLayout(2,1));
	
	TransactionGUI(boolean accessLevel)
	{
		setLayout(new GridLayout(1,2));
		setBackground(DARK_CHAMPAGNE);
		setBorder(BorderFactory.createLoweredBevelBorder());
		
		ReceiptPanel.clearReceipt();
		
		restaurantMenu = new ItemPanel();
		
		adminPrivilege = accessLevel;
		
		Tools.addBlankSpace(quarterPanel,1);
		quarterPanel.add(new CheckOutPanel(adminPrivilege,restaurantMenu));
		
		halfPanel.add(new ReceiptPanel());
		halfPanel.add(quarterPanel);
		
		add(halfPanel);
		add(restaurantMenu);
		
	}
	TransactionGUI()
	{
		this(adminPrivilege);
	}
}
