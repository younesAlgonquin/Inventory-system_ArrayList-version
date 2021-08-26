import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * Student Name: Younes Boutaleb
 * Student Number: 041019068 
 * Assignment number: Assignment 2
 * Date: 6/8/2021
 * purpose of class: This class inherits the FoodItem class variables and methods and declares and reads the size of the jar.
 * Section #: 303
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD.
 * @author Younes Boutaleb.
 * @version 1.0
 * 
 */
public class Preserve extends FoodItem {
	
	/**This is the size of the jar*/
	private int jarSize;
	
	/**
	 * This is a no-arg constructor
	 */
	public Preserve() {
			
	}//no-arg constructor
	
	/**
	 * This method prompt the user for the size of the jar
	 * @param scanner This is a scanner object which is declared in the main method
	 * @param fromFile Defines the type of input source (scanner or file) 
	 * @return returns true if the program successfully reads in all fields, otherwise false
	 */
	@Override
	public boolean addItem(Scanner scanner, boolean fromFile){
		
		//Input validation state(true/false)
		boolean valid =super.addItem(scanner, fromFile);
		
		//Reads the line containing the item jarSize and parse it to integer
		if(fromFile) {
			
				jarSize = Integer.parseInt(scanner.nextLine());
			
		}else {
		//reads the size of the jar. it assigns true to valid if the user enters a valid value otherwise it loops
		do {
			//validates integer input
			try {
				System.out.print("Enter the size of the jar in millilitres: ");
				jarSize=scanner.nextInt();
				//Validates positive values
				if(jarSize<0) {
					System.out.println("Invalid entry");
					valid =false;
				}else
					valid =true;
			}catch(InputMismatchException ex){
				System.out.println("Invalid entry");
				valid=false;
				scanner.nextLine();
			}
		}while(!valid || jarSize<0);
		
		}
		
		//returns true if the program successfully reads in all fields, otherwise false
		return valid;
	}//end addItem
	
	/**
	 * This method format the object properties to a string
	 * @return This methods returns the current object's properties in a formated string
	 */
	@Override
	public String toString() {
			
		return String.format(super.toString() + "size: %dmL", jarSize);
	}//end toString

	
	/**
	 * This method outputs items details to a file 
	 * @param writer is a buffer that writes to the file
	 */
	@Override
	public void outputItem (Formatter writer) {
		
		writer.format("%c\n", 'p');
		super.outputItem(writer);
		writer.format("%d\n", jarSize );
				
	}//end outputItem

}//end class
