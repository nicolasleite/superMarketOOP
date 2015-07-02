package superMarketOOP;

import java.util.Scanner;

public class Menu {
	static Scanner reader;

	static String clientMenu () {
		reader = new Scanner (System.in);

		System.out.println ("####################### Client Inteface #######################\n");
		System.out.println ("login\t\tenter the server with a account previously created");
		System.out.println ("signup\t\tcreate an account in the server");
		System.out.println ("exit\t\tstop execution");
		System.out.print ("\nOption: ");

		return reader.nextLine ();
	}

	static String logedClientMenu () {
		reader = new Scanner (System.in);

		System.out.println ("list all products");
		System.out.println ("list available products");
		System.out.println ("buy products");
		System.out.println ("exit");
		System.out.print ("\nOption: ");

		return reader.nextLine ();	
	}

	static String serverMenu () {
		reader = new Scanner (System.in);

		System.out.println ("####################### Server Inteface #######################\n");
		System.out.println ("new product\tregister new product in stock");
		System.out.println ("list available\tlist all available products");
		System.out.println ("list sold out\tlist all sold out products");
		System.out.println ("update stock\tupdate products in stock");
		System.out.println ("see all sales\tview all sales");
		System.out.println ("exit\t\tstop execution");
		System.out.print ("\nOption: ");

		return reader.nextLine ();
	}
}