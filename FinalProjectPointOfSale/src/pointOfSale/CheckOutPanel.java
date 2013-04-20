package pointOfSale;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckOutPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	
	private MenuButton checkoutButton[] = new MenuButton[6];
	private JPanel top = new JPanel(new GridLayout(3,3));
	private JPanel bottom = new JPanel(new GridLayout(2,1));
	private ItemPanel restaurantMenu = null;
	private JLabel adminLabel = new JLabel("ADMIN: ");
	private JTextField deleteField = new JTextField("",11);
	
	
	CheckOutPanel(boolean adminPrivilege, ItemPanel newMenu)
	{
		setBackground(DARK_CHAMPAGNE);
		setLayout(new GridLayout(2,1));
		
		restaurantMenu = newMenu;
		
		checkoutButton[0] = new MenuButton("System","System");
		checkoutButton[1] = new MenuButton("Clear","Clear");
		checkoutButton[2] = new MenuButton("Log Off","Log Off");
		checkoutButton[3] = new MenuButton("Categories","Categories");
		checkoutButton[4] = new MenuButton("Delete Item","Delete Item");
		checkoutButton[5] = new MenuButton("Checkout","Checkout");
		for(int count = 0; count<=5; count++)
			checkoutButton[count].addActionListener(this);
		
		adminLabel.setFont(new Font(Font.SERIF,Font.BOLD,18));
		adminLabel.setForeground(Color.RED);
		
		deleteField.setBorder(BorderFactory.createMatteBorder(20,0,20,0, DARK_CHAMPAGNE));
		
		top.setBackground(DARK_CHAMPAGNE);
		Tools.addBlankSpace(top,1);
		top.add(adminLabel);
		top.add(checkoutButton[0]);
		top.add(checkoutButton[1]);
		top.add(checkoutButton[2]);
		top.add(checkoutButton[3]);
		top.add(checkoutButton[4]);
		top.add(deleteField);
		Tools.addBlankSpace(top, 1);
		
		bottom.setBackground(DARK_CHAMPAGNE);
		Tools.addBlankSpace(bottom,1);
		bottom.add(checkoutButton[5]);
		
		add(top);
		add(bottom);
		
		if(adminPrivilege)
		{
			adminLabel.setVisible(true);
			checkoutButton[0].setVisible(true);
		}
		else
		{
			adminLabel.setVisible(false);
			checkoutButton[0].setVisible(false);
		}
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if(event.getActionCommand().equals("Log Off"))
			SystemInit.setLogInScreen();
		else if(event.getActionCommand().equals("Categories"))
			restaurantMenu.displayCategories();
		else if(event.getActionCommand().equals("Clear"))
			ReceiptPanel.clearReceipt();
		else if(event.getActionCommand().equals("Delete Item"))
			ReceiptPanel.deleteItem();
		else if(event.getActionCommand().equals("Checkout"))
			ReceiptPanel.saveReceipt();
		else if(event.getActionCommand().equals("System"))
			SystemInit.setAdminScreen();
	}
}
