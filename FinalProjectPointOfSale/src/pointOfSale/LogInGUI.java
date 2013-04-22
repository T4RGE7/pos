package pointOfSale;
import javax.swing.*;
import java.awt.*;
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
	private JPanel logoPanel = new JPanel(new GridLayout(1,1));
	private KeyPad numberPad = new KeyPad();
	private MenuButton exitButton = new MenuButton("Exit","Exit",this);
	private ImageIcon logo = new ImageIcon("Files/Icons/logoBig.jpg");
	private JLabel logoLabel = new JLabel(logo,SwingConstants.CENTER);
	
	LogInGUI()
	{	
		setLayout(new GridLayout(1,2));
		setBackground(DARK_CHAMPAGNE);
		setBorder(BorderFactory.createLoweredBevelBorder());
		
		logoLabel.setVerticalAlignment(SwingConstants.CENTER);
		logoPanel.setBackground(DARK_CHAMPAGNE);
		logoPanel.setBorder(BorderFactory.createEtchedBorder());
		//logoPanel.setBorder(BorderFactory.createMatteBorder(50,50,50,50, DARK_CHAMPAGNE));
		logoPanel.add(logoLabel); 
		
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
		
		add(logoPanel);
		add(halfPanel);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if (event.getActionCommand().equals("Exit"))
			System.exit(0);
	}
}
