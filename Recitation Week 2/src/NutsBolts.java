
import java.util.*;

public class NutsBolts {
	
	// Returns true iff each nut and each corresponding bolt in the two
	// respective arrays match eacy other.
	public static boolean correctFit(Nut[] nuts, Bolt[] bolts) {
		
		// Try out each corresponding nut and bolt.
		for (int i=0; i<nuts.length; i++)	
		
			// See if these don't match.
			if (nuts[i].compareTo(bolts[i]) != 0)
				return  false;
				
		// If we get here, they all matched.
		return true;
	}
	
	public static void printB(Bolt[] bolts)
	{
        for (int i = 0; i < bolts.length; i++) {
            System.out.print(bolts[i].v + " ");
        }
        System.out.println();
	}
    public static void printN(Nut[] nuts)
    {
        for (int i = 0; i < nuts.length; i++) {
            System.out.print(nuts[i].v + " ");
        }
        System.out.println();
    }
	
	// Creates an array of Bolt objects with size number of elements utilizing
	// r.
	public static Bolt[] makeRandomBolts(Random r, int size) {
		
		// Allocate space for our bolts,
		Bolt[] tmp = new Bolt[size];
		
		// Just create each object here and return the array.
		for (int i=0; i<size; i++)
			tmp[i] = new Bolt(r, i); /////////////////////////////////////////////////////
		return tmp;
	}
	
	// Creates an array of matching nuts to the array of bolts passed in.
	// It scrambles the nuts though, so that they are no longer in the correct
	// locations.
	public static Nut[] makeMatchingNuts(Random r, Bolt[] bolts) {
		
		// Create the array, and add in each matching nut.
		Nut[] tmp = new Nut[bolts.length];
		for (int i=0; i<bolts.length; i++) {
			tmp[i] = bolts[i].getMatchingNut(i);
		}	
		
		// Here we mix the array of nuts up.
		for (int i=0; i<3*bolts.length; i++) {
			
			// Get two random indexes.
			int index1 = r.nextInt(bolts.length);
			int index2 = r.nextInt(bolts.length);
			
			// Swap them!
			Nut store = tmp[index1];
			tmp[index1] = tmp[index2];
			tmp[index2] = store;
		}
		
		// Return our array of mixed up, but matching nuts.
		return tmp;
	}
}