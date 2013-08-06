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
	private JPanel quarterPanel2 = new JPanel(new GridLayout(1,1));
	
	public ProcessGUI()
	{
		setLayout(new GridLayout(1,2));
		setBackground(DARK_CHAMPAGNE);
		setBorder(BorderFactory.createLoweredBevelBorder());
		
		ReceiptPanel.clearReceipt();
		
		quarterPanel2.add(new ReceiptPanel());
		quarterPanel1.add(new ProcessPanel());
		quarterPanel1.add(new CardPanel());
		add(quarterPanel2);
		add(quarterPanel1);
		
	}
}
