import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Main Class that handles input and runs entire program.
 * @author AmandaTe
 */

public final class Main {
	/** Handles input 
	 *  Takes in a string that is the location of the text file to be passed in
	 *  The location is based on src as the current workspace.
	 * @throws IOException 
	 * */
	public static void main(String...args) throws IOException {
		if (args.length == 0){
			throw new Exception("Must provide at least one input.");
		} else {
			for (String f : args){
				getReceipt(f);
			}
		}
		System.exit(1);
	}
	
	/** Takes a FILENAME and opens the file to read it line by line to add 
	 *  each item to a Receipt. Prints completed Receipt to standout. */
	private static void getReceipt(String filename) throws IOException{
		BufferedReader file;
		try {
			file = new BufferedReader(new FileReader(new File(filename)));
			String line;
			Receipt r = new Receipt();
			while((line = file.readLine()) != null){
				r.addItem(new Item(line));
			}
			r.printReceipt(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
