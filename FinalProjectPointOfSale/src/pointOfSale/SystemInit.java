package pointOfSale;
import javax.swing.*;

import java.awt.*;


public class SystemInit extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static final Color DARK_GREEN = new Color(0,100,0);
	private static JPanel systemFrame = new JPanel(new GridLayout(1,1));
	
	public static void main(String[] args)
	{
		SystemInit gui = new SystemInit();
		gui.setVisible(true);
	}
	
	SystemInit()
	{
		setUndecorated(false);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(1,1));
		
		systemFrame.setBorder(BorderFactory.createMatteBorder(20, 20, 20, 20, DARK_GREEN));
		
		getContentPane().add(systemFrame);
		
		setLogInScreen();
	}
	
	public static void setLogInScreen()
	{
		systemFrame.removeAll();
		systemFrame.add(new LogInGUI());
		Tools.update(systemFrame);
	}
	
	public static void setTransactionScreen()
	{
		systemFrame.removeAll();
		systemFrame.add(new TransactionGUI());
		Tools.update(systemFrame);
	}
	public static void setAdminScreen()
	{
		systemFrame.removeAll();
		systemFrame.add(new AdministratorGUI());
		Tools.update(systemFrame);
	}
}
