
import java.util.*;

public class Nut {
	
	final private static int SIZE = 7;
	
	private int[] notches;
    public int v; /////////////////////////////////////////
	
	// A Nut object is basically a seven digit number.
	public Nut(Random r, int v) {/////////////////////////////////////////
        this.v = v;/////////////////////////////////////////
		notches = new int[SIZE];
		for (int i=0; i<notches.length; i++)
			notches[i] = r.nextInt(10);
	}
	
	// A standard constructor given the information stored in a Nut.
	public Nut(int[] digits) {
		notches = new int[SIZE];
		for (int i=0; i<SIZE; i++)
			notches[i] = digits[i];
	}
	
	// Need this to access a single digit.
	public int getDigit(int i) {
		return notches[i];
	}
	
	// Here we define a compareTo.
	public int compareTo(Bolt n) {
		int[] tmp = new int[SIZE];
		int carry = 0;
		boolean low = false;
		
		// Add up the numbers stored in this and n.
		for (int i=0; i<SIZE; i++) {
			tmp[i] = (carry + notches[i] + n.getDigit(i))%10;
			carry = (carry + notches[i] + n.getDigit(i))/10;
			if (tmp[i] < 9)
				low = true;
		}
		
		// Our sum is a bunch of 9s, so we've got a match!
		if (tmp[SIZE-1] == 9 && !low)
			return 0;
		
		// Our sum is too big, so make this nut too big.
		else if (carry > 0)
			return 1;
			
		// Opposite case.
		else 
			return -1;	
	}
	
	// Works just like the mirror method in the Bolt class.
	public Bolt getMatchingBolt() {
		int[] tmp = new int[SIZE];
		for (int i=0; i<7; i++)
			tmp[i] = 9 - notches[i];
		return new Bolt(tmp);
	}
	
	// For debugging purposes.
	public void print() {
		for (int i=0; i<SIZE; i++) 
			System.out.print(notches[SIZE-1-i]);
	}
	
}