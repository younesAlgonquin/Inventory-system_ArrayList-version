
import java.util.Formatter;
import java.util.Scanner;

/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * Student Name: Younes Boutaleb
 * Student Number: 041019068 
 * Assignment number: Assignment 2
 * Date: 6/8/2021
 * purpose of class: This class inherits the FoodItem class variables and methods and declares and reads the name of the orchard supplier.
 * Section #: 303
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD.
 * @author Younes Boutaleb.
 * @version 1.0
 * 
 */
public class Fruit extends FoodItem {
	
	/**This is the name of the name of the orchard supplier*/
	private String orchardName;
	
	/**
	 * This is a no-arg constructor
	 */
	public Fruit() {
		
	}//end no-arg constructor
	
	/**
	 * This method prompt the user for the name of the orchard supplier
	 * @param fromFile Defines the type of input source (scanner or file) 
	 * @param scanner This is a scanner object which is declared in the main method
	 * @return returns true if the program successfully reads in all fields, otherwise false
	 */
	@Override
	public boolean addItem(Scanner scanner , boolean fromFile) {
		
		//Input validation state(true/false)
		boolean valid =super.addItem(scanner, fromFile);
		
		//Reads the line containing the item orchardName
		if(fromFile) {
			
				orchardName = scanner.nextLine();
			
		}else {
		System.out.print("Enter the name of the orchard supplier: ");
		//Clears the newline character remaining in the scanner variable after reading the integer itemCost value
		scanner.nextLine();
		orchardName=scanner.nextLine();
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
			
		return String.format(super.toString() + "orchard supplier: %s", orchardName);
	}//end toString
	
		
	/**
	 * This method outputs items details to a file 
	 * @param writer is a buffer that writes to the file
	 */
	@Override
	public void outputItem (Formatter writer) {
		
		writer.format("%c\n", 'f');
		super.outputItem(writer);
		writer.format("%s\n", orchardName );
				
	}//end outputItem

}//end class
