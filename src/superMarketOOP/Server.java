package superMarketOOP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server implements ProgramInterface {
	private static final String productFile = "products.csv";
	private static BufferedReader file;
	private static BufferedWriter out;
	private Scanner s;
	private static final String deliverFile = "deliver.csv";
	
	public void newProduct (Product p) {
		try {
			out = new BufferedWriter(new FileWriter(productFile, true));
			out.write(p.printCSV());
			out.close();

			System.out.println ("Product registered with success");
		} catch (IOException e) {
			System.out.println ("Error to write file");
		}
	}
	
	public static void updateStock (Product p, int quant) {
		List<Product> products;
		Product aux = null;
		int i;

		products = turnFileIntoList();

		for (i=0; i<products.size(); i++) {
			aux = products.get(i);
			if (aux.name.equals(p.name)) {
				products.remove(i);
				break;
			}
		}

		aux.addSamples (quant);
		products.add (aux);
		printListToFile (products);		
	}

	public static void listAllProducts () throws NumberFormatException, IOException {
		file = new BufferedReader (new FileReader(productFile));
		Product prod;
		String csv;
		
		while ((csv = file.readLine()) != null) {
			prod = new Product (csv);	
			prod.printInformation ();
		}
	}

	public static void listAvailableProducts () throws FileNotFoundException {
		file = new BufferedReader (new FileReader(productFile));
		String[] fields;
		Product prod;
		String csv;
		int counter = 0;
		
		try {
			while ((csv = file.readLine()) != null) {
				fields = csv.split(",");
				if (Integer.parseInt(fields[4]) > 0) {
					prod = new Product (fields[0], Float.parseFloat(fields[1]), fields[2], fields[3], Integer.parseInt(fields[4]));	
					prod.printInformation ();
					counter++;
				}
			}
		} catch (NumberFormatException | IOException e) {}
		
		if (counter == 0) 
			System.out.println ("No available products found");
		System.out.println();
	}

	public static void listSoldOutProducts () throws FileNotFoundException {
		file = new BufferedReader (new FileReader(productFile));
		String[] fields;
		Product prod;
		String csv;
		int counter = 0;
		
		try {
			while ((csv = file.readLine()) != null) {
				fields = csv.split(",");
				if (Integer.parseInt(fields[4]) < 1) {
					prod = new Product (fields[0], Float.parseFloat(fields[1]), fields[2], fields[3], Integer.parseInt(fields[4]));	
					prod.printInformation ();
					counter ++;
				}
			}
		} catch (NumberFormatException | IOException e) {}
		
		if (counter == 0) 
			System.out.println ("No sold out products found");
		System.out.println();
	}

	public static Product findProduct (String name) throws FileNotFoundException {
		file = new BufferedReader (new FileReader(productFile));
		String[] fields;
		String csv;
		
		try {
			while ((csv = file.readLine()) != null) {
				fields = csv.split(",");
				if (name.equals(fields[0])) 
					return new Product (fields[0], Float.parseFloat(fields[1]), fields[2], fields[3], Integer.parseInt(fields[4]));	
			}
		} catch (NumberFormatException | IOException e) {
			System.out.println("NumberFormatException | IOException");
		}

		return null;
	}
	
	public static List<Product> turnFileIntoList () {
		List<Product> products = null;
		String csv = null;

		try {
			products = new ArrayList<Product>();
			file = new BufferedReader (new FileReader(productFile));
			while ((csv = file.readLine()) != null) {
				if (csv != null) products.add(new Product (csv));
			} file.close();
		} catch (FileNotFoundException e) {
		} catch(IOException e) {}

		return products;
	}

	public static void printListToFile (List<Product> p) {
		try {
			out = new BufferedWriter(new FileWriter(productFile, false));
			Product aux = null;

			for (int i = 0; i<p.size(); i++) {
				aux = p.get(i);
				out.write(aux.printCSV());
			} out.close();
			} catch (IOException e) {}
	}

	public static void addDeliverData(Product prod, Client c, int quant) {
		try {
			out = new BufferedWriter(new FileWriter(deliverFile, true));
			out.write(prod.name + "," + quant + "," + c.name + "," + c.address + "\n");
			out.close();
		} catch (IOException e) {}
	}
	
	public static void viewAllDelivers () {
		try {
			file = new BufferedReader (new FileReader(deliverFile));
			String[] fields;
			String csv;
			
			while ((csv = file.readLine()) != null) {
				fields = csv.split(",");
				
				System.out.println ("Product: " + fields[0]);
				System.out.println ("Quantity: " + fields [1]);
				System.out.println ("Client: " + fields [2]);
				System.out.println ("Address: " + fields [3] + "\n");
			}
		} catch (FileNotFoundException e) {
			System.out.println ("No delivers found");
		} catch (IOException e) {}
	}
	
	public void options () {
		String opt;
		Product p = null;
		s = new Scanner (System.in);
		String name, dueDate, provider;
		float price;
		int quant;

		do {
			opt = Menu.serverMenu ();

			switch (opt) {
				case "new product":
					System.out.print ("Name: ");
					name = s.nextLine();
					System.out.print ("Price: R$ ");
					price = s.nextFloat();
					System.out.print ("Due date: ");
					dueDate = s.nextLine();
					dueDate = s.nextLine();
					System.out.print ("Provider: ");
					provider = s.nextLine();
					System.out.print ("Quantity: ");
					quant = s.nextInt();

					p = new Product (name, price, dueDate, provider, quant);
					this.newProduct (p);
					p = null;

					break;

				case "list available":
					try {
						Server.listAvailableProducts ();
					} catch (FileNotFoundException e) {
					} break;

				case "list sold out":
					try {
						Server.listSoldOutProducts ();
					} catch (FileNotFoundException e) {
					} break;

				case "update stock":
					System.out.print ("Name of product: ");
					name = s.nextLine ();
					try {
						p = findProduct (name);
					} catch (FileNotFoundException e) {
					} if (p == null) {
						System.out.println ("Product not found");
					} else {
						System.out.print ("Number of new samples: ");
						quant = s.nextInt();

						Server.updateStock (p, quant);
						System.out.println ("Stock updated successfuly!\n");
					}

					break;

				case "see all sales":
					viewAllDelivers();
					break;
					
				case "exit":
					break;

				default:
					System.out.println ("Command not found!!");
			}

		} while (!(opt.equals("exit")));
	}
}