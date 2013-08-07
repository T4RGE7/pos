package pointOfSale;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ProcessGUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	
	private JPanel quarterPanel1 = new JPanel(new GridLayout(2,1));
	//private JPanel quarterPanel2 = new JPanel(new GridLayout(1,1));
	
	public ProcessGUI(boolean isAdmin)
	{
		quarterPanel1.removeAll();
		quarterPanel1 = null;
		quarterPanel1 = new JPanel(new GridLayout(2,1));
		setLayout(new GridLayout(1,2));
		setBackground(DARK_CHAMPAGNE);
		setBorder(BorderFactory.createLoweredBevelBorder());
		
		ReceiptPanel.clearReceipt();
		
		//quarterPanel2.add(new ReceiptPanel());
		quarterPanel1.add(new ProcessPanel(isAdmin));
		quarterPanel1.add(new CardPanel());
		add(new ReceiptPanel());
		add(quarterPanel1);
		
	}
}
