package pointOfSale;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;

public class AdminButtonPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;  //Added to satisfy compiler
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private MenuButton exitButton = new MenuButton("Back","Back");
	
	AdminButtonPanel()
	{
		setLayout(new GridLayout(4,2));
		setBackground(DARK_CHAMPAGNE);
		setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, DARK_CHAMPAGNE));
		
		Tools.addBlankSpace(this, 7);
		add(exitButton);
		exitButton.addActionListener(this);
		exitButton.setFont(new Font(Font.SERIF,Font.PLAIN,36));
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if(event.getActionCommand().equals("Exit"))
			SystemInit.setTransactionScreen();
	}
}
