
import java.util.*;

public class Bolt {
	
	final private static int SIZE = 7;
	
	private int[] notches;
	
	public int v; /////////////////////////////////////////
	
	// A Bolt object is basically a seven digit number.
	public Bolt(Random r, int v) {//////////////////////////////////////////////////
	    this.v = v;////////////////////////
		notches = new int[SIZE];
		for (int i=0; i<notches.length; i++)
			notches[i] = r.nextInt(10);
	}
	
	// A standard constructor given the information stored in a Bolt.
	public Bolt(int[] digits) {
		notches = new int[digits.length];
		for (int i=0; i<SIZE; i++)
			notches[i] = digits[i];
	}
	
	// Needed to access each digit of a Bolt.
	public int getDigit(int i) {
		return notches[i];
	}
	
	// Made this work the opposite of the corresponding comparison. Surprisingly
	// it's not a circular definition, just a consistent one.
	public int compareTo(Nut n) {
		return -n.compareTo(this);
	}
	
	// Return a matching Nut. A matching nut is one who's number has each digit
	// be its complement. A complement to a digit is its difference from 9.
	public Nut getMatchingNut(int v) {/////////////////////////
		int[] tmp = new int[SIZE];
		
		// We set each digit for the match.
		for (int i=0; i<SIZE; i++)
			tmp[i] = 9 - notches[i];
			
		// Now create the object and return it.
		Nut match = new Nut(tmp);
		match.v = v;
		return match;
	}
	
	// For debugging purposes.
	public void print() {
		for (int i=0; i<SIZE; i++) 
			System.out.print(notches[SIZE-1-i]);
	}
}