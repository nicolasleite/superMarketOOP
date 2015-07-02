package superMarketOOP;

public class Product {
	String name, provider, dueDate;
	float price;
	int quantity;

	public Product (String name, float price, String dueDate, String provider, int quantity) {
		this.name = name;
		this.price = price;
		this.dueDate = dueDate;
		this.provider = provider;
		this.quantity = quantity;
	}

	public Product (String csv) {
		String[] fields = csv.split(",");
		name = fields[0];
		price = Float.parseFloat(fields[1]);
		dueDate = fields[2];
		provider = fields[3];
		quantity = Integer.parseInt(fields[4]);
	}

	public void printInformation () {
		System.out.println ("Product: " + name);
		System.out.println ("Price: R$" + price);
		System.out.println ("Due date: " + dueDate);
		System.out.println ("Provider: " + provider);
		System.out.println ("Quantity: " + quantity + "\n");
	}

	public String printCSV () {
		return name + "," + price + "," + dueDate + "," + provider + "," + quantity + "\n";
	}
	
	public int getQuantity () {
		return quantity;
	}

	public void sellSamples (int n) {
		quantity -= n;
	}

	public void addSamples (int n) {
		quantity += n;
	}
}