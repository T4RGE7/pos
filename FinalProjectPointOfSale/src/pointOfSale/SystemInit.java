package pointOfSale;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.GridLayout;


public class SystemInit extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static Container contentPane = new Container();
	
	public static void main(String[] args)
	{
		SystemInit gui = new SystemInit();
		gui.setVisible(true);
	}
	
	SystemInit()
	{
		setUndecorated(true);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(1,1));
		
		contentPane = getContentPane();
		
		setLogInScreen();
	}
	
	public static void setLogInScreen()
	{
		contentPane.removeAll();
		contentPane.add(new LogInGUI());
		Tools.update(contentPane);
	}
	
	public static void setTransactionScreen()
	{
		contentPane.removeAll();
		contentPane.add(new TransactionGUI());
		Tools.update(contentPane);
	}
	public static void setTransactionScreen(Boolean accessLevel)
	{
		contentPane.removeAll();
		contentPane.add(new TransactionGUI(accessLevel));
		Tools.update(contentPane);
	}
}
