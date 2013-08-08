package testing;
import pointOfSale.Response;

public class TestXML {

	public static void main(String[] args) {
		
		String one[] = {"10", "10", "POS BRAVO v1.0", "4003000123456781", "1215", "1.05", "1.05"};
		String two[] = {"10", "10", "ADl1bYRUI2Pi5H6WO1fOHANtuxLlwlnUIhESEAAjEAeDBA==", "1.05", "1.05", "1.00", "VI0105", "KfJ"};
		String three[] = {"10", "10", "POS BRAVO v1.0", "KqaoIkv1k9wUbUmJ9wmtTKFWZhyPMas0IhESEAAjEAeDBw==", "1.05", "KfJ", "|15|410100700000", "VI0105"};
		
		Response test = new Response(1, one);
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		test = new Response(2, two);
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		System.out.println("____________________________________________");
		test = new Response(3, three);
		System.out.println(test.getXML());
		System.out.println(test.getResponse());
		
	}
	
}
