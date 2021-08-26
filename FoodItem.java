
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
 * purpose of class: This class represents the super class for this project.It declares the commun attribures of all food items.
 * It also declares methods which read , check, update and print states of the food item objects
 * Section #: 303
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD.
 * @author Younes Boutaleb.
 * @version 1.0
 * 
 */
public class FoodItem implements Comparable <FoodItem> {

	/**this is the food item identification code*/
	protected int itemCode;
	/**this is the food item name*/
	protected String itemName;
	/**this is the food item price*/
	protected float itemPrice;
	/**this is the available quantity in stock*/
	protected int itemQuantityInStock;
	/**this is the food item cost*/
	protected float itemCost;


	/**
	 * This is a no-arg constructor
	 */
	public FoodItem() {

	}//end no-arg constructor


	/**
	 * This method prompt the user for the food item information
	 * @param scanner This is a scanner object which is declared in the main method
	 * @param fromFile Defines the type of input source (scanner or file)
	 * @return this method returns true if the method successfully reads in all fields, otherwise returns false 
	 */
	public boolean addItem(Scanner scanner,  boolean fromFile) {


		//Input validation state(true/false)
		boolean valid = false;

		if(fromFile) {

			//Reads the line containing the item name
			itemName = scanner.nextLine();
			//Reads the line containing the item Quantity and parse it to int  
			itemQuantityInStock = Integer.parseInt(scanner.nextLine());
			//Reads the line containing the item cost and parse it to float 
			itemCost = Float.parseFloat(scanner.nextLine());
			//Reads the line containing the item price and parse it to float 
			itemPrice = Float.parseFloat(scanner.nextLine());

			valid=true;

		}else {

			//Clears the newline character remaining in the scanner variable after reading the integer itemCode value 
			scanner.nextLine();
			//Reads the name of the food item
			System.out.print("Enter the name for the item: ");
			itemName=scanner.nextLine();

			//reads the item quantity to be stored. it assigns true to valid if the user enters a valid value otherwise it loops
			do {
				//validates integer input
				try {
					System.out.print("Enter the quantity for the item: ");
					itemQuantityInStock=scanner.nextInt();
					//Validates positive values
					if(itemQuantityInStock<0) {
						System.out.println("Invalid entry");
						valid =false;
					}else
						valid =true;
				}catch(InputMismatchException ex){
					System.out.println("Invalid entry");
					valid=false;
					scanner.nextLine();
				}

			}while(!valid || itemQuantityInStock<0);

			//reads the item cost. it assigns true to valid if the user enters a valid value otherwise it loops
			do {
				//validates integer input
				try {
					System.out.print("Enter the cost of the item: ");
					itemCost=scanner.nextFloat();
					//Validates positive values
					if(itemCost<0) {
						System.out.println("Invalid entry");
						valid =false;
					}else
						valid =true;
				}catch(InputMismatchException ex){
					System.out.println("Invalid entry");
					valid=false;
					scanner.nextLine();
				}
			}while(!valid || itemCost<0);

			//reads the item price. it assigns true to valid if the user enters a valid value otherwise it loops
			do {
				//validates integer input
				try {
					System.out.print("Enter the sales price of the item: ");
					itemPrice=scanner.nextFloat();
					//Validates positive values
					if(itemPrice<0) {
						System.out.println("Invalid entry");
						valid =false;
					}else
						valid =true;
				}catch(InputMismatchException ex){
					System.out.println("Invalid entry");
					valid=false;
					scanner.nextLine();
				}
			}while(!valid || itemPrice<0);
		}//end else 

		//returns true if the method successfully reads in all fields, otherwise false
		return valid;

	}//end addItem



	/**
	 * This method prompt the user for the item code
	 * @param fromFile Defines the type of input source (scanner or file)
	 * @param scanner This is a scanner object which is declared in the main method
	 * @return this method returns true if the method successfully reads  item code, otherwise returns false
	 */
	public boolean inputCode(Scanner scanner,  boolean fromFile) {

		//Input validation state(true/false)
		boolean valid = false;

		if (fromFile) {

			itemCode = Integer.parseInt(scanner.nextLine());
			valid = true;

		}else {

			//reads the item code. it assigns true to valid if the user enters a valid value otherwise false
			try {
				System.out.print("Enter the code for the item: ");
				itemCode=scanner.nextInt();
				valid =true;
			}catch(InputMismatchException ex){
				valid=false;
				scanner.nextLine();
			}
		}//end else

		//returns true if the method successfully reads the item code, otherwise false
		return valid;

	}//end inputCode



	/**
	 * This method updates the item quantity in stock after each buying/selling operation
	 * @param amount This is the quantity to buy or sell 
	 * @return returns true if the method successfully updates the quantity, otherwise false
	 */
	public boolean updateItem(int amount) {

		//checks if the stock quantity remains positive before updating it
		if(itemQuantityInStock+amount>=0) {
			itemQuantityInStock += amount;
			return true;
		}else {	
			return false;
		}//end else 

	}//end updateItem



	/**
	 * This method format the object properties to a string
	 * @return This methods returns the current object's properties in a formated string
	 */
	public String toString() {

		return String.format("\nItem: %d %s %d price: $%.2f cost: $%.2f ", itemCode, itemName, itemQuantityInStock, itemPrice, itemCost);
	}//end to String


	
	/**
	 * This method compares two objects using their itemcodes.
	 * it overrides the method declared in the comparable interface
	 * @param item is the object to be compared with the calling object  
	 * @return Returns 1 if item.itemCode is lower, -1 if item.itemCode is lower and 0 they are same 
	 */
	@Override
	public int compareTo (FoodItem item) {

		//Returns -1 if item is lower than the calling object
		if (itemCode < item.itemCode) {

			return -1;

			//Returns 0 if both objects have the same itemCode
		}else if (itemCode==item.itemCode) {

			return 0;
			//Returns 1 if item is higher than the calling object	
		}else{

			return 1;

		}//end else 			

	}//end compareTo


	/**
	 * This method outputs items details to a file 
	 * @param writer is a buffer that writes to the file
	 */
	public void outputItem (Formatter writer) {

		writer.format("%d\n%s\n%d\n%.2f\n%.2f\n", itemCode, itemName, itemQuantityInStock, itemCost, itemPrice );

	}//end outputItem

}//end class
