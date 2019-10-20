import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
					String other = otherform(line);
					if (other != null){
						exception.add(other);
					} else {
						exception.addAll(special_case(line));
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Takes WORD and attempts to return the singular form. */
	private String otherform(String word){
		int last_index = word.length() - 1;
		
		// Handling words currently in their plural form
		if (word.endsWith("ies")){
			return word.substring(0, last_index - 2) + "y";
		} else if (word.endsWith("ves")){
			return null;
		} else if (word.endsWith("oes")){
			return word.substring(0, last_index - 1);
		} else if (word.endsWith("es")) {
			return null;
		} else if (word.endsWith("s")) {
			return null;
		// Handling words currently in their singular form
		} else if (word.endsWith("x") || word.endsWith("z") ||
				word.endsWith("ch") || word.endsWith("sh") ||
				(word.endsWith("o") && 
						!vowels.contains(word.charAt(last_index - 1)))){
			return word + "es";
		} else if (word.endsWith("y")){
			return word.substring(0, last_index) + "ies";
		} else if (word.endsWith("f")) {
			return word.substring(0, last_index) + "ves";
		} else if (word.endsWith("fe")){
			return word.substring(0, last_index - 1) + "ves";
		} 
		return word + "s";
	}
	
	/** Handles special cases of changing WORD to plural or singular form.
	 *  Consider words ending in:
	 *  - ves: leaves -> leaf, knives -> knife (could end in f or fe)
	 *  - es: oranges -> orange, buses -> bus (could drop s or es)
	 *  - s: bus -> buses, fruits -> fruit (could be plural or singular)
	 *  Note: some strings we create may not be actual words, but this helps
	 *  to consider if users type in a singular or plural form of a word. */
	private Collection<? extends String> special_case(String word){
		int last_i = word.length() - 1;
		ArrayList<String> special_cases = new ArrayList<>();
		if (word.endsWith("ves")){
			String origin = word.substring(0, last_i - 2);
			special_cases.add(origin + "f");
			special_cases.add(origin + "fe");
		} else if (word.endsWith("es")){
			special_cases.add(word.substring(0, last_i));
			special_cases.add(word.substring(0, last_i - 1));
		} else if (word.endsWith("s")) {
			special_cases.add(word.substring(0, last_i));
			special_cases.add(word + "es");
		}
		return special_cases;
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
	
	/** Determines whether KEYWORD is a book, food, or medical product. 
	 *  Also has additional check to make sure it isn't a non-descriptive word
	 *  that might've been added mistakenly when attempting to create singular
	 *  and plural forms. */
	public boolean isExempt(String keyword){
		boolean notEmpty = !empty_words.contains(keyword);
		boolean isBook = books.contains(keyword);
		boolean isMed = meds.contains(keyword);
		boolean isFood = foods.contains(keyword);
		return notEmpty && (isBook || isMed || isFood);
	}
	
	/** Synonymous words for the word book. */
	private HashSet<String> books = new HashSet<>();
	/** Words indicating medical origination. */
	private HashSet<String> meds = new HashSet<>();
	/** Contains strings of food names. */
	private HashSet<String> foods = new HashSet<>();
	/** Used to help implement finding singular and plural words. */
	private final static HashSet<Character> vowels = 
			new HashSet<>(Arrays.asList('a', 'e', 'i','o','u'));
	/** Represents non-descriptive words.*/
	private final static HashSet<String> empty_words = 
			new HashSet<>(Arrays.asList("a", "an", "the", "of"));
}
