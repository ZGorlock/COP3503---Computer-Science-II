/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Facade Pattern
 * 4 April 2016
 */

import java.util.Scanner;


/**
 * This file will check if a trainer’s ID is valid.
 * 
 * @author Zachary Gill
 */
public class TrainerIDCheck
{    
    private static final int acceptableID = 01301;
    
    /**
     * Creates a new TrainerIDCheck object.
     */
    public TrainerIDCheck()
    {

    }
    
    /**
     * Checks a trainer's id.
     * 
     * @return Whether their id is acceptable or not.
     */
    public boolean checkID(Scanner scanner)
    {
        System.out.print("Please enter your id: ");
        int id = Integer.decode(scanner.nextLine());
        
        if (id == acceptableID) {
            System.out.println("Congratualations, your id is acceptable!");
            return true;
        }
        else {
            System.out.println("We're sorry, your id is unacceptable!");
            return false;
        }
    }
}
