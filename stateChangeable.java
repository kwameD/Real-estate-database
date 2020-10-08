/*File Name: stateChangeable.java
 * @author kwame Duodu
 * Date: July 10, 2020
 * Purpose:  a program to manage a real estate database.
 */ 
//bounded generic type parameter
interface StateChangeable<T extends Enum<T>> {
	
	 public abstract void changeState(T t);

}

