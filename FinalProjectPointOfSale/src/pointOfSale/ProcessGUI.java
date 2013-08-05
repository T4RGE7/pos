package pointOfSale;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ProcessGUI extends JPanel
{
	private static final long serialVersionUID = 1L;
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	
	private JPanel quarterPanel1 = new JPanel(new GridLayout(1,2));
	
	public ProcessGUI()
	{
		setLayout(new GridLayout(1,2));
		setBackground(DARK_CHAMPAGNE);
		setBorder(BorderFactory.createLoweredBevelBorder());
		
		ReceiptPanel.clearReceipt();
		
		quarterPanel1.add(new ReceiptPanel());
		quarterPanel1.add(new ProcessPanel());
		add(quarterPanel1);
	}
}
