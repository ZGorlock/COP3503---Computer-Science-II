/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Recitation 2
 * 27 January 2016
 */

import java.util.Random;


/**
 * @author Zachary Gill
 *
 */
public class MatchNutsBolts
{
    //Pre-condition: nuts and bolts are of the same size and
    //  contain Nut and Bolt objects that all
    //  match, but not necessarily in the given
    //  order.
    //Post-condition: nuts and bolts will store matching Nut
    //   and Bolt objects in each index. Thus,
    //   the Nut in index 0 of nuts will match
    //   the Bolt in index 0 of bolts, etc.
    
    /**
     * 
     * @param nuts  : an array of nuts
     * @param bolts : an array of bolts
     */
    public static void match(Nut[] nuts, Bolt[] bolts)
    {
        match(nuts, bolts, 0, bolts.length - 1);
    }
    
    /**
     * Helper function for match
     * @param nuts : an array of nuts
     * @param bolts : an array of bolts
     * @param lo : the low index to use in the bolt array
     * @param hi : The high index to use in the bolt array
     */
    public static void match(Nut[] nuts, Bolt[] bolts, int lo, int hi)
    {
        if (lo >= hi) //base case
            return;
        
        int p = partition(nuts, bolts, lo, hi);
        
        match(nuts, bolts, lo, p - 1); //match the smaller bolts
        match(nuts, bolts, p + 1, hi); //match the larger bolts
    }
    
    /**
     * Partition function for match quicksort.
     * @param nuts : an array of nuts
     * @param bolts : an array of bolts
     * @param lo : the low index to use in the bolt array
     * @param hi : The high index to use in the bolt array
     * @return The final location of the pivot.
     */
    public static int partition(Nut[] nuts, Bolt[] bolts, int lo, int hi)
    {
        //partition nuts
        Bolt pivot = bolts[hi];
        int current = lo;
        boolean hit = false;
        for (int i = lo; i < hi; i++) {
            if (nuts[i].compareTo(pivot) < 0) { //if this nut is smaller than the pivot bolt
                swapNuts(nuts, i, current);
                current++;
            }
            else if (nuts[i].compareTo(pivot) == 0) { //since the nut pivot is not at hi, we need to move it there when we find it
                if (!hit) {
                    swapNuts(nuts, i, hi);
                    i--; //decrement i so that the nut from hi now located at i isnt looked over
                    hit = true;
                }
            }
        }
        swapNuts(nuts, hi, current); //move the pivot nut into the correct position
        
        //partition bolts
        Nut pivotNut = nuts[current]; //reference to the pivot nut
        current = lo; //reset current for the bolts partition
        for (int i = lo; i < hi; i++) {
            if (bolts[i].compareTo(pivotNut) < 0) { //if this bolt is smaller than or equal to the pivot bolt
                swapBolts(bolts, i, current); //swap the 2 bolts
                current++;
            } //we dont need to test if we find the pivot because we already know the bolt pivot is located at hi
        }
        swapBolts(bolts, hi, current); //move pivot bolt into correct position
        
        return current;
    }

    /**
     * Swaps two Bolts in a Bolt array.
     * @param nuts : The Bolt array.
     * @param a : The index of the first Bolt being swapped.
     * @param b : The index of the second Bolt being swapped.
     */
    private static void swapBolts(Bolt[] bolts, int a, int b)
    {
        Bolt tmp = bolts[a];
        bolts[a] = bolts[b];
        bolts[b] = tmp;
    }
    
    /**
     * Swaps two Nuts in a Nut array.
     * @param nuts : The Nut array.
     * @param a : The index of the first Nut being swapped.
     * @param b : The index of the second Nut being swapped.
     */
    private static void swapNuts(Nut[] nuts, int a, int b)
    {
        Nut tmp = nuts[a];
        nuts[a] = nuts[b];
        nuts[b] = tmp;
    }


    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Bolt[] bolts = NutsBolts.makeRandomBolts(new Random(System.currentTimeMillis()), 1000000);
        Nut[] nuts = NutsBolts.makeMatchingNuts(new Random(System.currentTimeMillis()), bolts);
        
        match(nuts, bolts);
        
        System.out.println(NutsBolts.correctFit(nuts, bolts));
    }

}
