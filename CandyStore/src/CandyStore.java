/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Candy Store
 * 28 March 2016
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * @author Zachary Gill
 *
 */
public class CandyStore
{
    private static String inputFile = "input.txt";
    private static int n = 0;
    private static int money = 0;
    private static int calories[];
    private static int price[];

    /**
     * Main method.
     * @param args : Can be used to indicate the input file, input.txt by default.
     */
    public static void main(String[] args)
    {
        if (args.length > 0)
            inputFile = args[0];
        
        Scanner file = null;
        try {
            file = new Scanner(new File("input.txt"));
        }
        catch (FileNotFoundException e) {
            System.out.println("Could not find " + inputFile);
            e.printStackTrace();
        }
        
        while (readInput(file)) //while there is more input cases
            System.out.println(calculateBest()); //find the maximum calorie count
        
        file.close();
    }
    
    /**
     * Reads an input block from the input file.
     * @param file : The scanner for the input file.
     * @return Whether the file has new input or not. 
     */
    public static boolean readInput(Scanner file)
    {
        n = file.nextInt();
        money = (int)(file.nextDouble() * 100);
        
        if (n == 0 && money == 0.0)
            return false;
        
        clearData();
        
        for (int i = 0; i < n; i++) {
            calories[i] = file.nextInt();
            price[i] = (int)(file.nextDouble() * 100);
        }
                
        return true;
    }
    
    /**
     * Clears the lists of input data.
     */
    public static void clearData()
    {
        calories = new int[n];
        price = new int[n];
    }
    
    /**
     * Calculates the maximum calories you are able to purchase.
     * @return The number of calories.
     */
    public static int calculateBest()
    {
        int sack[] = new int[money + 1]; //stores values (DP)
        
        for (int i = 0; i < n; i++) { //for each type of candy
            for (int j = price[i]; j < sack.length; j++) { //update max values in array
                sack[j] = Math.max(
                        sack[j], //old stored value
                        sack[j - price[i]] + calories[i]); //potential new value
            }
        }
        
        return sack[money];
    }

}
