import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/** Receipt represents a complete receipt with total sales tax and total price.
 * @author AmandaTe
 * */

public class Receipt {
	
	/** Adds item I into ITEMS */
	public void addItem(Item i){
		items.add(i);
	}

	/** Adds all of this receipts contents to an output file and prints all 
	 *  items to standout followed by total sales tax added and total spent.
	 *   */
	public void printReceipt(String inputFile){
		double itemprice;
		double itemtax;
		double newprice;
		String line, st, t;
		String outputFile = inputFile.replaceAll(".txt", "Output.txt");
		DecimalFormat df = new DecimalFormat("#0.00");
		try {
			BufferedWriter file = new BufferedWriter(new FileWriter(outputFile));
			for (Item i : items){
				itemprice = i.getPrice();
				itemtax = i.getTax();
				newprice = i.getQuantity()*(itemprice + itemtax);
				salestax += i.getQuantity()*itemtax;
				total += newprice;
				line = i.getQuantity() + " " + i.getName() + ": " + 
						df.format(newprice) + "\n";
				file.write(line);
				System.out.print(line);
			}
			st = "Sales Taxes: " + df.format(salestax) + "\n";
			t = "Total: " + df.format(total);
			file.write(st);
			file.write(t);
			file.close();
			System.out.print(st);
			System.out.println(t + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/** All items on this receipt*/
	ArrayList<Item> items = new ArrayList<>();
	/** Total sales tax added. */
	double salestax = 0;
	/** Total amount spent. */
	double total = 0;
}
