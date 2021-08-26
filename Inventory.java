import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
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
 * purpose of class: This class declares an ArrayList of FoodItem Object then declares methods that reads the object's properties,
 * update stock quantity and display the food item properties in a string format
 * Section #: 303
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD.
 * @author Younes Boutaleb.
 * @version 1.0
 * 
 */
public class Inventory {

	/**This is an ArrayList of FoodItem objects*/
	private ArrayList<FoodItem> inventory;
	/**This is the number of items entered by the user*/
	private int numItems;

	/**
	 * This is a no-arg constructor that initialize the inventory ArrayList
	 */
	public Inventory() {

		inventory= new ArrayList<>();

	}//end parameterized constructor 


	/**
	 * This method checks if the item object exists already in the arrayList using iterative binary search algorithm
	 * @param item this is the food item that we are looking for
	 * @return Returns the index of item if it exists in the arrayList else returns -1 
	 */
	public int alreadyExists (FoodItem item) {

		//This is the index of item in the ArrayList
		int location=-1;
		//this is the index of the middle element of the remaining elements
		int index;
		//This is the result of the comparison of item to element at index
		int result;
		//The arrayList boundaries
		int maxIndex=numItems-1;
		int minIndex=0;
		//Loops until it finds the item or when no remaining values to check
		while(maxIndex>=minIndex && location==-1) {

			//this is the index of the middle element of the remaining elements
			index = (maxIndex + minIndex + 1)/2;
			result = inventory.get(index).compareTo(item);
			//Compares the key integer to the middle element. if it matches it assigns index to location
			if(result==0) {
				location=index;
				//if not it identifies the remaining possible matches
			}else if(result==-1) {
				minIndex=index+1;
			}else {
				maxIndex=index-1;
			}
		}//end while

		return location;
	}//end alreadyExists


	/**
	 * This method displays object having a specific itemCode if it exists otherwise it prints an error message
	 * @param scanner This is a scanner object which is declared in the main method
	 */
	public void searchForItem(Scanner scanner) {

		//FoodItem object that will call the needed methods
		FoodItem item = new FoodItem();
		//Prompt user for item code
		boolean valid = item.inputCode(scanner, false);
		//print error message in case of an invalid code
		if (!valid) {
			System.out.printf("Code not found in inventory...");

		}else {
			//check if the valid item code exists in arrayList
			int location = alreadyExists(item);

			//print error message in case of an invalid code
			if(location == -1) {

				System.out.printf("Code not found in inventory...");
			}else {

				//print the corresponding object details 
				System.out.printf(inventory.get(location).toString());
			}//end else
		}//end else

	}//end searchForItem


	/**
	 * This method save the arrayList content to a file
	 * @param scanner Scanner object defined in main method
	 */
	public void saveToFile(Scanner scanner)  {

		//Prompt for the destination file name
		System.out.print("Enter the filename to save to: ");
		scanner.nextLine();

		String file = scanner.nextLine();

		try {
			//declare a formatter that opens the file
			Formatter writer = new Formatter (file);

			//write each object to the file
			for(int i=0; i<numItems; i++) {

				inventory.get(i).outputItem(writer);
			}//end for

			//close the Formatter
			if(writer !=null)
				writer.close();

		}  catch (SecurityException e) {
			System.out.println("Read permission denide");
		} catch (FileNotFoundException e) { System.out.println("File not found"); 

		}//end catch

	}//end saveToFile


	/**
	 * This method save the a file content to the arrayList
	 * @param scanner Scanner object defined in main method
	 */
	public void readFromFile(Scanner scanner) {

		scanner.nextLine();
		//Reads the file name
		System.out.print("Enter the filename to read from: ");
		String file = scanner.nextLine();

		//FoodItem object which will call the needed methods
		FoodItem item =null;

		//check if the file name, the reading permissions and if the data is formatted properly
		try {

			//Scanner object which opens and reads from the file
			Scanner reader = new Scanner (Paths.get(file));
			//this is the Food object type in string format
			String type;
			//this is the Food object type
			char foodType = ' ';

			//iterates through the file
			while (reader.hasNext() && foodType != 0) {

				//reads the first object characteristic which  as a string
				type = reader.nextLine();
				//takes the first character of type
				foodType = type.charAt(0);

				//checks if foodtype corresponds to a valid type 
				switch(foodType) {

				//Declares a fruit item
				case 'f':
					item =new Fruit(); 
					break;

					//Declares a Vegetable item
				case 'v':
					item = new Vegetable();
					break;

					//Declares a Preserve item
				case 'p':
					item = new Preserve();
					break;

					//Displays an error message if the entered character doesn't match any food item type
				default:
					System.out.println("Invalid entry. The file is corrupted");
					System.out.println("Error Encountered while reading the file, aborting...");
					foodType = 0;
					break;
				}//end switch

				//in case of a valid type
				if(foodType != 0) {

					//read the itemCode
					item.inputCode(reader, true);

					//check if the object exists in the arrayList
					if (alreadyExists(item) == -1) {
						//if not read the object's data and then add item to the arrayList
						item.addItem(reader, true);
						inventory.add(item);
						//Sort the ArrayList using itemCode
						Collections.sort(inventory);
						numItems++;
						//print an error object if the object exists
					}else {

						System.out.println("Item code already exists");
						System.out.println("Error Encountered while reading the file, aborting...");
						break;
					}//end else
				}//end if
			}//end while

		}catch (IndexOutOfBoundsException e) {
			System.out.println("File corrupted");
		}catch (NumberFormatException e) {
			System.out.println("File corrupted");
		}catch (SecurityException e) {
			System.out.println("Read permission denide");
		}catch (NoSuchFileException e) { 
			System.out.println("File Not Found, ignoring..."); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//end readFromFile


	/**
	 * This method adds the food item chosen by the user to the inventory array 
	 * then, then calls the addItem method to read the object properties
	 * @param scanner this is a scanner object which is declared in the main method
	 * @return returns true if the program successfully reads in all fields, otherwise false
	 */
	public boolean addItem(Scanner scanner) {

		//This is the user input for the food item type
		String key="";
		//this is the first character of the user input
		char type = ' ';

		FoodItem item =null;


		//clear the scanner as it has the integer option
		scanner.nextLine();

		//The while loop continues until the user enters a valid food type
		try {
			while(type != 'f' && type != 'v' && type != 'p') {

				//Prompts the user for the food type
				System.out.println("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)?");
				key=scanner.nextLine();

				//The if statement checks whether the user entered a valid input or not
				if(key.length()!=1) {
					System.out.println("Invalid entry");
				}else {
					//if the user enters a character it assigns it to the type variable
					type=key.charAt(0);

					//Switch structure validates the user's choice then adds the a new food item to the array
					switch(type) {

					//Declares a fruit item
					case 'f':
						item =new Fruit(); 

						break;

						//Declares a Vegetable item
					case 'v':
						item = new Vegetable();

						break;

						//Declares a Preserve item
					case 'p':
						item = new Preserve();

						break;
						//Displays an error message if the entered character doesn't match any food item type
					default:
						System.out.println("Invalid entry");
						break;
					}//end switch
				}//end else
			}//end while
		}catch (IndexOutOfBoundsException e) {
			System.out.println("Invalid entry");
		}


		//Loops until it reads a valid item code
		while(!item.inputCode(scanner, false)) {

			System.out.println("Invalid code");	
		}//end while

		//The addItem returns false if the entered code correspond to an existing item otherwise it continues
		if(alreadyExists(item)!=-1) {
			System.out.print("Item code already exists");
			return false;			
		}//end if

		//Reads the properties of the new food item and add it to the ArrayList
		item.addItem(scanner, false);
		inventory.add(item);

		//Sort the ArrayList using itemCode
		Collections.sort(inventory);

		//increments the number of items in the array
		numItems++;
		//if the method executes all the above steps successfully it returns true
		return true;

	}//end addItem


	/**
	 * This class prompts for the item code and the buying/selling quantity then validates the inputs and updates the stock quantity
	 * In case of a non valid condition the method returns false and breaks immediately
	 * @param scanner This is the scanner object declared in the main method
	 * @param buyOrSell This variable defines the type of operation demanded by the user
	 * @return if the method executes successfully it returns true, otherwise false
	 */
	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {

		//This is the update quantity
		int quantity = 0;
		//This is the index of the item having entered code
		int index;
		//This is the operation demanded by the user
		String choice;
		//Declare a FoodItem object
		FoodItem item = new FoodItem();

		//defines the type of operation selected by the user then assign it to choice
		if(buyOrSell) {
			choice="buy";
		}else {
			choice="sell";
		}//end if

		//The method outputs an error message and returns false if the array is empty 
		if(numItems==0) {
			System.out.printf("Error...could not %s item", choice);	
			return false;
		}//end if

		//The method outputs an error message and returns false if the array is empty
		if(!item.inputCode(scanner, false)){
			System.out.printf("Code not found in inventory...\nError...could not %s item", choice);
			return false;
		}//end if

		/*The method outputs an error message and returns false if the entered code doesn't correspond to any of the array elements
		 * This part has to execute only if the earlier steps are successfully executed 
		 * */
		index =alreadyExists(item);
		if(index==-1) {
			System.out.printf("Code not found in inventory...\nError...could not %s item", choice);
			return false;
		}

		//reads and checks whether the update quantity input is a positive integer or not
		try {
			System.out.printf("Enter valid quantity to %s: ", choice);
			quantity=scanner.nextInt();
		}catch(InputMismatchException ex){
			System.out.printf("Invalid quantity...\nError...could not %s item\n", choice);
			scanner.nextLine();
			return false;
		}
		//Validates positive values
		if(quantity<0) {
			System.out.printf("Invalid quantity...\nError...could not %s item\n", choice);
			return false;
		}

		//if the user chose to sell the item then quantity becomes negative
		if(!buyOrSell)
			quantity *= -1;

		//
		boolean quantityUpdateResult = inventory.get(index).updateItem(quantity);

		//checks if the stock quantity will remain positive before updating it then returns true or false
		if(!quantityUpdateResult)
			System.out.println("Insufficient stock in inventory...\nError...could not sell item\n");

		return quantityUpdateResult;
	}//end updateQuantity


	/**
	 * This method format the objects' properties to a string
	 * @return This methods returns all the existing objects' properties in formated strings
	 */
	public String toString() {

		//This is the item's properties formated to a string, which override its content after each loop
		String formatString="Inventory:";

		for(int i=0; i<numItems; i++) {

			formatString +=inventory.get(i).toString();
		}
		return String.format(formatString);
	}//end toString

}//end class
