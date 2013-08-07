package testing;
import pointOfSale.Response;

public class TestXML {

	public static void main(String[] args) {
		
		String one[] = {"10", "10", "POS BRAVO v1.0", "5499990123456781", "0513", "1.05", "1.05"};
		String two[] = {};
		
		Response test = new Response(1, one);
		
		System.out.println(test.getXML());
		
	}
	
}
