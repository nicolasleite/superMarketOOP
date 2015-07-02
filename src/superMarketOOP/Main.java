package superMarketOOP;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		ProgramInterface pInterface = null;
					
		if (args[0].equals ("client")){
			ClientAccounts ca = new ClientAccounts();
			pInterface = ca.accountManagement();
		} else if (args[0].equals ("server")){
			pInterface = new Server ();
		} else 
			System.out.println ("Incorrect argument\nUsage: \"java -jar superMarket.jar client\" or \"java -jar superMarket.jar client\"");
	
		if (pInterface != null)
			pInterface.options();
	}
}