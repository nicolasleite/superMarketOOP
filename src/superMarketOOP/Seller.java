package superMarketOOP;

import java.util.List;
import java.util.Scanner;

public class Seller {
	static private Scanner s;
	static private String name;
	static private int quant;
	static private List<Product> products;
	static private Product prod;

	public static void sellProduct (Client c) {
		s = new Scanner (System.in);
		int i;

		System.out.print ("Desired product: ");
		name = s.nextLine();
		System.out.print ("Quantity wanted: ");
		quant = s.nextInt();

		products = Server.turnFileIntoList();

		prod = null;
		
		for (i=0; i<products.size(); i++) {
			if (products.get(i).name.equals(name)) {
				prod = products.get(i);
				products.remove(i);
				break;
			}
		}

		if (prod == null){
			System.out.println ("Product not found");
		} else if (quant > prod.quantity) {
			if (prod.getQuantity() > 1){
				System.out.println ("Error: there are just " + prod.quantity + prod.name + "s");
			} else
				System.out.println ("Error: there is just " + prod.quantity + prod.name);
		} else {
			prod.sellSamples(quant);
			products.add (prod);
			Server.printListToFile (products);
			Server.addDeliverData (prod, c, quant);
			
			System.out.println("Success!! The product will be delivered to your address");
			System.out.println("Total to pay: R$ " + quant*prod.price + "\n");
		} prod = null;
	}
}