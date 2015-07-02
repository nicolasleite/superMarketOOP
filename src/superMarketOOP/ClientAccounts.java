package superMarketOOP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ClientAccounts {
	private String userFile = "users.csv";
	private static Scanner s;
	private BufferedReader in;
	private BufferedWriter out;
	private Client client;

	Client signUp () throws IOException {
		String name, password, address, phone, email, id, csv;
		String[] fields;

		s = new Scanner (System.in);
		out = new BufferedWriter(new FileWriter(userFile, true));
		in = new BufferedReader (new FileReader(userFile));

		System.out.print ("Name: ");
		name = s.nextLine();
		System.out.print ("Password: ");
		password = s.nextLine();
		System.out.print ("Address: ");
		address = s.nextLine();
		System.out.print ("Phone: ");
		phone = s.nextLine();
		System.out.print ("E-mail: ");
		email = s.nextLine();
		System.out.print ("ID: ");
		id = s.nextLine();


		while ((csv = in.readLine()) != null) {
			fields = csv.split(",");
			if (fields[4].equals(id)) {
				in.close();
				System.out.println ("This ID has already been taken, try another");
				return this.signUp();
			}
		}
		in.close();

		out.write(name + "," + address + "," + phone + "," + email + "," + id + "," + password);
		out.close();
		client = new Client (name, address, phone, email, id, password);
		System.out.println ("Account created succesfuly!!\nWelcome " + name + "!!\n");
		return client;
	}

	Client login () throws FileNotFoundException, IOException {
		String password, id, csv, aux;
		String[] fields;

		s = new Scanner (System.in);
		in = new BufferedReader (new FileReader(userFile));

		System.out.print ("ID: ");
		id = s.nextLine();
		System.out.print ("Password: ");
		password = s.nextLine();

		while ((csv = in.readLine()) != null) {
			fields = csv.split(",");
			if (fields[4].equals(id)) {
				if (fields[5].equals (password)){
					System.out.println ("\nWelcome back, " + fields[0] + "!!\n");
					client = new Client (fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]);
					in.close();
					return client;
				} else {
					System.out.println ("\nWrong password!!\n");
					in.close();
					return this.login();
				}
			}
		}
		in.close();

		System.out.println ("ID not found, \"try again\" or, \"cancel\"?");
		System.out.print ("Option: ");
		aux = s.nextLine();
		if (aux.equals("try again"))
			return this.login();
		else return null;
	}

	public Client accountManagement () throws FileNotFoundException, IOException {
		String cmd;
		s = new Scanner (System.in);
		Client c;

		cmd = Menu.clientMenu();

		switch (cmd) {
			case "login":
				c = login ();
				break;
			case "signup":
				c = signUp();
				break;
			case "exit":
				return null;
		default:
				c = null;
				System.out.println ("Command not found");
		}

		if (c == null) 
			return this.accountManagement();
		else return c;
	}
}