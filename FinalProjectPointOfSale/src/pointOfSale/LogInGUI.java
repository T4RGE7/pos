package pointOfSale;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LogInGUI extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private static final Color DARK_GREEN = new Color(0,100,0);
	private static final  Color PALE_GOLDENROD = new Color(238,232,170);

	private JPanel halfPanel = new JPanel(new GridLayout(1,2));
	private JPanel quarterPanel = new JPanel(new GridLayout(2,1));
	private JPanel exitPanel = new JPanel(new GridLayout(4,2));
	private KeyPad numberPad = new KeyPad();
	private MenuButton exitButton = new MenuButton("Exit","Exit");
	private JPanel tempPanel = new JPanel(new GridLayout(1,1));
	
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
		exitButton.addActionListener(this);
		
		quarterPanel.add(numberPad);
		quarterPanel.add(exitPanel);
		
		Tools.addBlankSpace(halfPanel,1);
		halfPanel.add(quarterPanel);
		halfPanel.setBackground(DARK_CHAMPAGNE);
		
		File imageFile = new File("Logo.jpg");
		Image loadImage = null;
		try
		{
			loadImage = ImageIO.read(imageFile);
		}
		catch(IOException e)
		{
			
		}
		Image resizedImage = loadImage.getScaledInstance(tempPanel.getWidth(),
				tempPanel.getHeight(), UNDEFINED_CONDITION);
		JLabel logoLabel = new JLabel(new ImageIcon(resizedImage));
		
		tempPanel.setBackground(PALE_GOLDENROD);
		tempPanel.setBorder(BorderFactory.createMatteBorder(10,10,10,10,DARK_CHAMPAGNE));
		
		
		add(tempPanel);
		add(halfPanel);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if (event.getActionCommand().equals("Exit"))
			System.exit(0);
	}
}
