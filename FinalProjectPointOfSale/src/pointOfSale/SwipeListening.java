package pointOfSale;

import java.util.Scanner;

public class SwipeListening implements Runnable {
	
	String input;

	public SwipeListening() {
		input = "";
	}
	
	@Override
	public void run() {
		Scanner listener = new Scanner(System.in);
		input = listener.nextLine();
		listener.close();
	}
	
	public String getInput() {
		return this.input;
	}

}
