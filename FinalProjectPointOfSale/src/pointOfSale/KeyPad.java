package pointOfSale;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KeyPad extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private static final Color DARK_CHAMPAGNE = new Color(194, 178, 128);
	private final String SECURITY_FILE = "Files/System/SecurityCodes";
	private JTextField numberField = new JTextField("",10);
	private String numberCode = "";
	private JLabel errorLabel = new JLabel("Invalid Code");
	private int count=0;
	
	KeyPad()
	{
		setBorder(BorderFactory.createMatteBorder(10,10,10,10,DARK_CHAMPAGNE));
		setLayout(new GridLayout(5,1));
		setBackground(DARK_CHAMPAGNE);
		numberField.setEditable(false);
		numberField.setBackground(Color.WHITE);
		numberField.setFont(new Font(Font.SERIF,Font.PLAIN,18));
		add(numberField);
		
		errorLabel.setFont(new Font(Font.SERIF,Font.BOLD,18));
		errorLabel.setForeground(Color.RED);
		errorLabel.setVisible(false);
		
		add(errorLabel);
		addButtonRow(this);
		addButtonRow(this);
		addButtonRow(this);
	}
	private void addButtonRow(JPanel panel)
	{
		JPanel subPanel = new JPanel(new GridLayout(1,4));
		
		MenuButton button1 = new MenuButton(String.valueOf(count),String.valueOf(count));
		button1.addActionListener(this);
		count++;
		
		MenuButton button2 = new MenuButton(String.valueOf(count),String.valueOf(count));
		button2.addActionListener(this);
		count++;
		
		MenuButton button3 = null;
		if(count == 10)
			button3 = new MenuButton("Clear","10");
		else
			button3 = new MenuButton(String.valueOf(count),String.valueOf(count));
		button3.addActionListener(this);
		count++;
		
		MenuButton button4 = null;
		if(count == 11)
			button4 = new MenuButton("Enter","11");
		else
			button4 = new MenuButton(String.valueOf(count),String.valueOf(count));
		button4.addActionListener(this);
		count++;
		
		subPanel.add(button1);
		subPanel.add(button2);
		subPanel.add(button3);
		subPanel.add(button4);
		panel.add(subPanel);
	}
	public void actionPerformed(ActionEvent event)
	{
		if(Integer.parseInt(event.getActionCommand()) < 10)
		{
			if(numberCode.length() < 6)
			{
				numberCode = numberCode + event.getActionCommand();
				numberField.setText(numberField.getText() + "*");
			}
			errorLabel.setVisible(false);
		}
		else if (event.getActionCommand().equals("10"))
		{
			numberCode = "";
			numberField.setText("");
			errorLabel.setVisible(false);
		}
		else if (event.getActionCommand().equals("11"))
		{
			checkCode();
		}
	}
	private void checkCode()
	{
		Scanner inputStream = null;
		
		try
		{
			inputStream = new Scanner(new File(SECURITY_FILE));
			
			while(inputStream.hasNextLine())
			{
				String line = inputStream.nextLine();
				
				if(line.equals(""))
					;
				else
				{
					String accessLevel = line.substring(0,1);
					String code = line.substring(2);
					if(numberCode.equals(code))
						SystemInit.setTransactionScreen(accessLevel.equals("A"));
					else
						errorLabel.setVisible(true);
				}
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
		}
	}
}
