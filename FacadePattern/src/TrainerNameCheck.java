/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Facade Pattern
 * 4 April 2016
 */

import java.util.Scanner;


/**
 * This file will check if a trainer’s name is valid.
 * 
 * @author Zachary Gill
 */
public class TrainerNameCheck
{
    private static final String acceptableName = "Ash Ketchum";
    
    /**
     * Creates a new TrainerNameCheck object.
     */
    public TrainerNameCheck()
    {
        
    }
    
    /**
     * Checks a trainer's name.
     * 
     * @return Whether their name is acceptable or not.
     */
    public boolean checkName(Scanner scanner)
    {
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();
        
        if (name.equals(acceptableName)) {
            System.out.println("Congratulations, your name is acceptable!");
            return true;
        }
        else {
            System.out.println("We're sorry, your name is unacceptable!");
            return false;
        }
    }

}
