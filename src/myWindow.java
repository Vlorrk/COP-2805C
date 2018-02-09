/* Student: Javier Baez
 * 
 * Class: COP 2805C
 * 
 * Instructor: Jeho Park
 * 
 * Date: 02/02/18
 * 
 * Assignment 04
 */


// required API's for making the Applet
import java.awt.Graphics;
import javax.swing.JApplet;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")  //Eclipse was giving a warning about the class not containing a serial so I suppressed it <JB>.
public class myWindow extends JApplet {
	String inputOne;
	String inputTwo;        //JOptionPane and "draw" method work with strings, so making the data members String type
	String sum;
	String product;
	String difference;
	String quotient;
	
	
	double dInputOne;
	double dInputTwo;  //using these double for the calculations
	
	public myWindow() {
		//constructor
	}
	
	public void init() {
		//when Applet is init() , optionPane will grab the user input into the two strings
	
		inputOne = JOptionPane.showInputDialog("Enter the first floating point value: ");
		
		inputTwo = JOptionPane.showInputDialog("Enter the second floating point value:  ");
		
		parse();
		
	}
	
	public void parse() {
		
		//this method parses the strings into doubles and does the basic arithmetic to get the sum, product, difference and quotient
		dInputOne = Double.parseDouble(inputOne);
		dInputTwo = Double.parseDouble(inputTwo);
		
		//uses toString to make the calculations back into strings
		sum =Double.toString(dInputOne + dInputTwo);
		product = Double.toString(dInputOne * dInputTwo);
		difference = Double.toString(dInputOne - dInputTwo);
		quotient = Double.toString(dInputOne / dInputTwo);
	}
	
	public void paint(Graphics g) {
		
		//draws the results
		g.drawString("The Sum is: " + sum, 10, 20);
		
		g.drawString("The Product is: " + product, 10, 40);
		
		g.drawString("The Difference is: " + difference, 10, 60);
		
		g.drawString("The Quotient is: " + quotient, 10, 80);
	
		
		
	}
}