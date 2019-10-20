import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class Unit_Test {
	Item i;
	File output;
	File expected;
	
	@Test 
	public void testGoodItem() {
		i = new Item("1 box of strawberries at 1.00");
		assertEquals("box of strawberries", i.getName());
		assertEquals(1.00, i.getPrice(), .001);
		assertEquals(1, i.getQuantity());
		assertEquals(0, i.getTax(), .001);
	}
	
	@Test (expected = Exception.class)
	public void testbadItemInput1(){
		i = new Item("1 box of strawberries");
	}
	
	@Test (expected = Exception.class)
	public void testbadItemInput2(){
		i = new Item("1 box of strawberries for 1.00");
	}
	
	@Test (expected = Exception.class)
	public void testbadItemInput3(){
		i = new Item("Box of strawberries at 1.00");
	}
	
	@Test (expected = Exception.class)
	public void testbadItemInput4(){
		i = new Item("1 box of strawberries at");
	}
	
	@Test (expected = Exception.class)
	public void testbadItemInput5(){
		i = new Item("1 box of strawberries at five");
	}

	@Test
	public void testInput1() throws IOException {
		output = new File("Input1Output.txt");
		expected = new File("ExpectedInput1.txt");
		assertTrue("Input1.txt failed", 
				FileUtils.contentEquals(output, expected));
	}
	
	@Test
	public void testInput2() throws IOException {
		output = new File("Input2Output.txt");
		expected = new File("ExpectedInput2.txt");
		assertTrue("Input2.txt failed", 
				FileUtils.contentEquals(output, expected));
	}
	
	@Test
	public void testInput3() throws IOException {
		output = new File("Input3Output.txt");
		expected = new File("ExpectedInput3.txt");
		assertTrue("Input3.txt failed", 
				FileUtils.contentEquals(output, expected));
	}
	
	@Test
	public void testMultQuantity() throws IOException {
		output = new File("MultQuantityOutput.txt");
		expected = new File("ExpectedMultQuantity.txt");
		assertTrue("MultQuantity.txt failed", 
				FileUtils.contentEquals(output, expected));
	}

	@Test
	public void testNormalItem() throws IOException {
		output = new File("NormalItemsOutput.txt");
		expected = new File("ExpectedNormalItems.txt");
		assertTrue("NormalItem.txt failed", 
				FileUtils.contentEquals(output, expected));
	}
	
	@Test
	public void testImported() throws IOException {
		output = new File("ImportedOutput.txt");
		expected = new File("ExpectedImported.txt");
		assertTrue("Imported.txt failed", 
				FileUtils.contentEquals(output, expected));
	}
	
	@Test
	public void testBook() throws IOException {
		output = new File("BookOutput.txt");
		expected = new File("ExpectedBook.txt");
		assertTrue("Book.txt failed", 
				FileUtils.contentEquals(output, expected));
	}
	
	@Test
	public void testFood() throws IOException {
		output = new File("FoodOutput.txt");
		expected = new File("ExpectedFood.txt");
		assertTrue("Food.txt failed", 
				FileUtils.contentEquals(output, expected));
	}
	
	@Test
	public void testMed() throws IOException {
		output = new File("MedOutput.txt");
		expected = new File("ExpectedMed.txt");
		assertTrue("Med.txt failed", 
				FileUtils.contentEquals(output, expected));
	}
	
	@Test
	public void testImportedBook() throws IOException {
		output = new File("ImportedBookOutput.txt");
		expected = new File("ExpectedImportedBook.txt");
		assertTrue("ImportedBook.txt failed", 
				FileUtils.contentEquals(output, expected));
	}
	
	@Test
	public void testImportedFood() throws IOException {
		output = new File("ImportedFoodOutput.txt");
		expected = new File("ExpectedImportedFood.txt");
		assertTrue("ImportedFood.txt failed", 
				FileUtils.contentEquals(output, expected));
	}
	
	@Test
	public void testImportedMed() throws IOException {
		output = new File("ImportedMedOutput.txt");
		expected = new File("ExpectedImportedMed.txt");
		assertTrue("ImportedMed.txt failed", 
				FileUtils.contentEquals(output, expected));
	}
}
