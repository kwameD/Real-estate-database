/*File Name: Property.java
 * @author kwame Duodu
 * Date: July 10, 2020
 * Purpose:  a program to manage a real estate database.
 */
public class Property<T extends Enum<T>> implements StateChangeable<T>{
	 
	 //variable instance
	 String property_address;
	 int numberOfBedrooms, square_footage, price;
	 Status status;
	 
	 //constructor accepts four parameters
	 public Property(String property_address, int numberOfBedrooms, int square_footage, int price) {
		 this.property_address = property_address;
		 this.numberOfBedrooms = numberOfBedrooms;
		 this.square_footage = square_footage;
		 this.price = price;
		 status = Status.FOR_SALE;
	 }
	 
	 //allows the status of the property to be changed
	 public void changeState(T inputstatus) {
		this.status= (Status)inputstatus;
	 }	 
 
 
 //overridden that returns a string 
	 @Override
 public String toString() {	 
		 return new String("Property Address: " + this.property_address + "\nNumber of Bedrooms: " + this.numberOfBedrooms + "\nSquare Feet: " + this.square_footage+ "\nPrice: $" + this.price + "\nCurrent Status: " + this.status);
 	}
 }
