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

	/** Prints all items contained on this receipt with their respective price
	 *  and sales tax, followed by total sales tax added and total spent.
	 *   */
	public void printReceipt(){
		double itemprice;
		double itemtax;
		double newprice;
		DecimalFormat df = new DecimalFormat("#0.00");
		for (Item i : items){
			itemprice = i.getPrice();
			itemtax = i.getTax();
			newprice = i.getQuantity()*(itemprice + itemtax);
			salestax += itemtax;
			total += newprice;
			System.out.println(i.getQuantity() + " " + i.getName() + ": " + 
								df.format(newprice));
		}
		System.out.println("Sales Taxes: " + df.format(salestax));
		System.out.println("Total: " + df.format(total) + "\n");
	}
	
	/** All items on this receipt*/
	ArrayList<Item> items = new ArrayList<>();
	/** Total sales tax added. */
	double salestax = 0;
	/** Total amount spent. */
	double total = 0;
}
