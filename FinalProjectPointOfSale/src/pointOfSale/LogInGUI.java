package pointOfSale;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInGUI extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static final Color DARK_GREEN = new Color(0,100,0);

	private JPanel halfPanel = new JPanel(new GridLayout(1,2));
	private JPanel quarterPanel = new JPanel(new GridLayout(2,1));
	private JPanel exitPanel = new JPanel(new GridLayout(4,2));
	private KeyPad numberPad = new KeyPad();
	private MenuButton exitButton = new MenuButton("Exit","Exit",this);
	
	LogInGUI()
	{	
		setLayout(new GridLayout(1,2));
		setBackground(DARK_CHAMPAGNE);
		setBorder(BorderFactory.createMatteBorder(20, 20, 20, 20, DARK_GREEN));
		
		Tools.addBlankSpace(exitPanel,7);
		exitPanel.add(exitButton);
		exitPanel.setBackground(DARK_CHAMPAGNE);
		exitPanel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, DARK_CHAMPAGNE));
		exitButton.setFont(new Font(Font.SERIF,Font.PLAIN,36));
		
		quarterPanel.add(numberPad);
		quarterPanel.add(exitPanel);
		
		Tools.addBlankSpace(halfPanel,1);
		halfPanel.add(quarterPanel);
		halfPanel.setBackground(DARK_CHAMPAGNE);
		
		Tools.addBlankSpace(this,1);
		add(halfPanel);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if (event.getActionCommand().equals("Exit"))
			System.exit(0);
	}
}
