import java.util.Formatter;
import java.util.Scanner;

/**
 * CET - CS Academic Level 3
 * Assignment number: Assignment 2
 * Date: 6/8/202
 * purpose of class: This class inherits the FoodItem class variables and methods and declares and reads the name of the farm supplier .
 * Section #: 303
 * Course: CST8130 - Data Structures
 * @version 1.0
 * 
 */
public class Vegetable extends FoodItem{
	
	/**This is the name of the farm supplier*/
	private String farmName;
	
	/**
	 * This is a no-arg constructor
	 */
	public Vegetable() {	
		
	}//end no-arg constructor
	
	/**
	 * This method prompt the user for the name of the farm supplier 
	 * @param scanner This is a scanner object which is declared in the main method
	 * @param fromFile Defines the type of input source (scanner or file)
	 * @return returns true if the program successfully reads in all fields, otherwise false
	 */
	@Override
	public boolean addItem(Scanner scanner, boolean fromFile) {
		
		//Input validation state(true/false)
		boolean valid =super.addItem(scanner, fromFile);
		
		//Reads the line containing the item farmName
		if(fromFile) {
			
				farmName = scanner.nextLine();
			
		}else {
		//Reads the name of the farm supplier
		System.out.print("Enter the name of the farm supplier: ");
		//Clears the newline character remaining in the scanner variable after reading the integer itemCost value 
		scanner.nextLine();
		farmName=scanner.nextLine();
		
		}//end else
		
		//returns true if the program successfully reads in all fields, otherwise false
		return valid;
	}//end addItem
	

	/**
	 * This method format the object properties to a string
	 * @return This methods returns the current object's properties in a formated string
	 */
	@Override
	public String toString() {
			
		return String.format(super.toString() + "farm supplier: %s", farmName);
	}//end toString
	
	
	@Override
	public void outputItem (Formatter writer) {
		
		writer.format("%c\n", 'v');
		super.outputItem(writer);
		writer.format("%s\n", farmName );
				
	}//end outputItem

}//end class
