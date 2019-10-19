import java.util.StringJoiner;
import java.util.Arrays;

/** Item represents an item in the passed in text file.
 * @author AmandaTe
 * */

public class Item {	
	Item(String receiptLine, Data c){
		classifier = c;
		setValues(receiptLine);
	}
	
	/** Sets all Item attributes */
	private void setValues(String receiptLine){
		parseLine(receiptLine);
		setSpecialTypes();
		setTax();
	}
	
	/** Parses line passed in when creating an Item and sets QUANTITY, NAME, 
	 *  NAME_STRINGS, and PRICE.*/
	private void parseLine(String line){
		// Split line by spaces
		String[] split_line = line.split(" ");
		
		// Set QUANTITY
		quantity = Integer.parseInt(split_line[0]);
		
		// Set NAME and NAME_STRINGS
		int nameend = 1;
		while(nameend < split_line.length && !split_line[nameend].equals("at")){
			++nameend;
		}
		
		StringJoiner item_name = new StringJoiner(" ");
		for(int i = 1; i < nameend; ++i){
			item_name.add(split_line[i]);
		}
		
		name = item_name.toString();
		name_strings = Arrays.copyOfRange(split_line, 1, nameend);
		
		// Set PRICE
		price = Double.parseDouble(split_line[nameend + 1]);
	}
	
	/** Set IMPORTED and EXEMPT if this Item is imported or a book, food, or 
	 *  medical product. */
	private void setSpecialTypes(){
		for(String str : name_strings){
			if(str.equals("imported")){
				imported = true;
				continue;
			} else if (classifier.isExempt(str)){
				exempt = true;
				continue;
			}
			if (imported && exempt){
				break;
			}
		}
	}
	
	/** Returns the tax to be added to this item. */
	public double getTax(){
		return tax;
	}
	
	/** Sets the tax to be added to this item, based on what type of item it is. */
	private void setTax(){
		double t = 0;
		if(!exempt){
			t += .1;
		}
		if(imported){
			t += .05;
		}
		tax = Math.ceil(t*price*20.00)/20.00;
	}
	
	/** Returns the quantity of this item */
	public int getQuantity(){
		return quantity;
	}
	
	/** Returns the name of this item */
	public String getName(){
		return name;
	}
	
	/** Returns the original price of this item */
	public double getPrice(){
		return price;
	}
	
	/** How many of these items are being bought. */
	private int quantity;
	/** Item name */
	private String name; 
	/** Item name split by spaces */
	private String[] name_strings; 
	/** Determines if this item is imported. False by default. */
	private boolean imported = false;
	/** Determines if this item is exempt from sales tax 
	* (Either a book, food, or medical product). False by default. */
	private boolean exempt = false; 
	/** Item price */
	private double price;
	/** Item tax */
	private double tax; 
	/** The data we'll use to classify an item. */
	private Data classifier;
}
