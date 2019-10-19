# Sales Tax Technical Challenge

## Prompt
Basic sales tax is applicable at a rate of 10% on all goods, except books, 
food, and medical products that are exempt. Import duty is an additional 
sales tax applicable on all imported goods at a rate of 5%, with no exemptions.

When I purchase items, I receive a receipt which lists the name of all the 
items and their price (including tax), finishing with the total cost of the 
items, and the total amounts of sales taxes paid. The rounding rules for sales 
tax are that for a tax rate of n%, a shelf price of p contains (np/100 rounded 
up to the nearest 0.05) amount of sales tax.

![alt text](https://github.com/amandate/salestax/blob/master/imgs/input.png)![alt text](https://github.com/amandate/salestax/blob/master/imgs/output.png)

## Instructions to Run Program
In the `src` folder, run `javac *.java`

Afterwards, when you run `java Main`, you can input multiple text files. 
Be sure the text files contain are of the same format as originally provided input.
(As displayed in the images above).
* Example 1: `java Main "...testing/input1.txt"`
* Example 2: `java Main "...testing/input1.txt" "...testing/input2.txt" 
"...testing/input3.txt"`

## Current Limitations
* Data to classify an item as a book is based on whether its input line contains 
a word that is synonymous to "book". Aka doesn't handle specific book titles.
* Data to classify an item as a medical product is based on whether its input 
line contains a synonym of "medicine", "medical", "pill", "medication", or "drug". 
Aka it doesn't handle specific brands.
* Data to classify an item as a food is based on the list of USDA foods. This 
currently does not include name brands, but could be tweaked to incorporate this. 
* An item is classified as an imported good if it contains the word "imported" its 
line in the input text file.

## Thought Process
My biggest objective was figuring out how to classify items as a book, food, 
medical product, or an imported good. This led me to question what type of
input I should be expecting: generic items or brand name items? 

I figured it was pretty open ended, so depending on how I set up my data, I 
could easily tweak my program to encompass more specific items such as brands. 
For scalability purposes, I decided to consider specific branded items, which 
led me to consider several different approaches, such as using open source, 
APIs, or [DBpedia](https://wiki.dbpedia.org/about). However, after further 
research, I couldn't quite find something that worked well enough.

Since the input I was given had things like "book" and "chocolate bar", I felt it
was safe to assume (for now) that I would not receive more specific input like
a book title or a brand of chocolate bar. 

## Important Folders and Files
* src - Folder containing program files
	* Main.java - Handles input and runs entire program.
	* Data.java - Contains all data used in this project to classify an object 
	as a book, food, or medical product.
	* Item.java - Represents an item from the passed in text file.
	* Receipt.java - Represents a complete receipt with all items, total sales 
	tax, and total price.
* testing - Folder containing all test input files.

## Resources
* Obtained "book" and "medical product" classification data from [thesaurus.com](https://www.thesaurus.com/)
* Obtained "food" classification data from [USDA open source data](https://www.ars.usda.gov/ARSUserFiles/80400525/Data/SR27/asc/FOOD_DES.txt)
