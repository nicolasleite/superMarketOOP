package superMarketOOP;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Client implements ProgramInterface {
	String name, password, address, phone, email, id;
	
	//class builder 
	public Client (String name, String address, String phone, String email, String id, String password) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.id = id;
		this.password = password;
	}

	public void options () {
		String opt;

		do {
			opt = Menu.logedClientMenu ();

			switch (opt) {
				case "list all products":
					try {
						Server.listAllProducts();
					} catch (NumberFormatException | IOException e) {
						System.out.println("Sorry, but there aren't registered products!!\n");
					} break;
				case "list available products":
					try {
						Server.listAvailableProducts();
					} catch (FileNotFoundException e) {
						System.out.println("Sorry, but there aren't registered products!!\n");
					} break;
				case "buy products":
					Seller.sellProduct(this);
					/*Product prod = null;
					try {
						prod = Server.findProduct (opt);
					} catch (FileNotFoundException e) {
						System.out.println("Sorry, but there aren't registered products!!\n");
					} if (prod == null) {
						System.out.print ("Sorry, product not found");
					} else if (prod.quantity == 0) {
						System.out.println ("Sorry, product unnavailable right now");
					} else {
						//Seller seller = new Seller ();
						//new Thread(seller).start();
						Seller.sellProduct();
					} */break;
				case "exit":
					break;
				default:
					System.out.println ("command not found\n");
			}
		} while (!(opt.equals("exit")));
		
		System.out.println ("Thanks for the preference!!");
	}

	/*public void forgotPassword () {
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        String msg, msg1, msg2, msg3, msg4;

        msg1 = "############### superMarketOOP ###############\n\n";
        msg2 = "Hello " + name + ",\n\tYou have lost your password and asked to retrieve it. Here are your account details:\n\n";
        msg3 = "ID: " + id + "\nPassword: " + password + "\n\n";
        msg4 = "If you haven't requested your password, please ignore this message";

        msg = msg1 + msg2 + msg3 + msg4;

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("nicolasleite96@gmail.com", "superMarketOOP"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email, name));
            msg.setSubject("Your superMarketOOP account details");
            msg.setText();
            Transport.send(msg);

            System.out.println ("An email has been send to the account email containing your login data");
        } catch (AddressException e) {}
        catch (MessagingException e) {}
	}*/
}