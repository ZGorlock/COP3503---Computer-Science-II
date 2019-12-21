/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Facade Pattern
 * 4 April 2016
 */

/**
 * This file will welcome newcomers into the building, and remind them that the Pok�mon Center�s job is to restore Pok�mon to perfect health � just in case they forgot.
 * 
 * @author Zachary Gill
 */
public class Welcome
{
    
    /**
     * Creates a new Welcome object.
     */
    public Welcome()
    {
        
    }
    
    /**
     * Welcomes a user to the PokeCenter.
     */
    public void welcome()
    {
        System.out.println("Welcome to the PokeCenter! We will restore your Pokemon to perfect health!");
    }
    
    /**
     * Thanks the user for using the PokeCenter.
     */
    public void thank()
    {
        System.out.println("Thank you for visiting the PokeCenter, we hope you come back soon!");
    }
}
