/* Student: Javier Baez
 * 
 * Class: COP 2805C
 * 
 * Instructor: Jeho PArk
 * 
 * Date: 1 /11/18
 * 
 * Assignment 01
 */

public class source {
	
	
	
	public static void main(String[] args) {
		
	Integer[] arrayNumbers = {1,2,3}; //array of type int. Using "Integer" class to have int object since primitive data types do not implement Comparable.
	
	String[] arrayStrings = {"red", "green", "blue"}; //array of string type
	
	Double[] arrayCircles = {3.0, 2.9 ,5.9};    // array of type double. Using "Double" class because "double" is a primitive type and does not pass in the method.
	
		
	    //here im printing out the method call which prints out the maximum value inside the array.
		System.out.println(max(arrayStrings));
		
		System.out.print(max(arrayNumbers)+"\n"); //using \n for cleanliness
		
		System.out.print("Circle radius: " +max(arrayCircles)+"\n");
		
	}
	
	
	
	
	//generic method that finds maximum value within an array of any type
	
	public static <E extends Comparable<E>> E max(E[] list) {
		
	
	E max = list[0]; //creating generic varable max that will hold the highest value of the array passed into the method
	
	for(int i = 0; i <list.length; i++) {      //for loop to sort and use "compareTo" to find the max in the array
		
		E index = list[i];
		
		if (index.compareTo(max)>0){
			
			max = index;
		}
	
	
	}
	return max; //return the value of the maximum in the array of unknown type
	
	}
	
}