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
	 * TODO: Handle multiple input
	 *  Takes in a string that is the location of the text file to be passed in
	 *  The location is based on src as the current workspace.
	 * @throws IOException 
	 * */
	public static void main(String...args) throws IOException {
		if (args.length == 0){
			System.out.println("Please provide input text file.");
		} else {
			Data classification = new Data();
			for (String f : args){
				BufferedReader file = getInput(f);
				getReceipt(file, classification);
			}
		}
		System.exit(1);
	}
	
	/** Takes a FILENAME and returns a BufferedReader to read the file. */
	private static BufferedReader getInput(String filename) throws FileNotFoundException{
		return new BufferedReader(new FileReader(new File(filename)));
	}
	
	/** Takes a FILE and reads it line by line to add each item to a Receipt.
	 *  Prints completed Receipt. */
	private static void getReceipt(BufferedReader file, Data c) throws IOException{
		String line;
		Receipt r = new Receipt();
		while((line = file.readLine()) != null){
			r.addItem(new Item(line, c));
		}
		r.printReceipt();
	}
}
