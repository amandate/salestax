import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/** Data represents all data used in this project to classify an object as a
 *  book, food, or medical product.
 * @author AmandaTe
 * */

public class Data {
	Data(){
		parse_file("../raw_data/book_synonyms.txt", books, false);
		parse_file("../raw_data/med_synonyms.txt", meds, false);
		parse_file("../raw_data/food.txt", foods, true);
		add_dbpedialinks();
	}
	
	/** Reads FILE line by line and adds each word to specified EXCEPTION. 
	 *  If we are handling food.txt, we parse each line for the specific food.*/
	private void parse_file(String file, HashSet<String> exception, boolean food){
		BufferedReader f;
		String line;
		try {
			f = new BufferedReader(new FileReader(new File(file)));
			while((line = f.readLine()) != null){
				if (food){
					line = parse_line(line);
				}
				if (line != null){
					exception.add(line);
					exception.add(other_form(line));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Takes WORD and returns the singular or plural form of WORD. */
	private String other_form(String word){
		int last_index = word.length() - 1;
		Character last_char = word.charAt(last_index);
		if (last_char.equals('s')){
			return word.substring(0, last_index);
		} else {
			return word + "s";
		}
	}
	
	/** Specifically used for food.txt. Parses LINE using regex to obtain a
	 *  substring of the food. */
	private String parse_line(String line){
		Pattern pattern = Pattern.compile("[A-Z]+[a-z]+");
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()){
			String word = matcher.group().toString().toLowerCase();
			if (!foods.contains(word)){
				return word;
			}
		}
		return null;
	}
	
	private void add_dbpedialinks(){
	}
	
	/** Determines whether KEYWORD is a book, food, or medical product. */
	public boolean isExempt(String keyword){
		boolean isBook = books.contains(keyword);
		boolean isMed = meds.contains(keyword);
		boolean isFood = foods.contains(keyword);
		return isBook || isMed || isFood;
	}
	
	/** Synonymous words for the word book. */
	private HashSet<String> books = new HashSet<>();
	/** Words indicating medical origination. */
	private HashSet<String> meds = new HashSet<>();
	/** Contains strings of food names. */
	private HashSet<String> foods = new HashSet<>();
}
