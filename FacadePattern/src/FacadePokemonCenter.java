/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Facade Pattern
 * 4 April 2016
 */

import java.util.Scanner;


/**
 * @author Zachary Gill
 *
 * This file will have instances of every other class, except for TestPokemonCenter.java and Pokemon.java. It will run the operations of the center by calling the necessary methods on those instances. This is the central concept of the Façade Design Pattern.
 */
public class FacadePokemonCenter
{
    private Welcome welcome;
    private TrainerNameCheck trainerNameCheck;
    private TrainerIDCheck trainerIDCheck;
    private HealAndPC healAndPC;
    
    private Scanner scanner;

    /**
     * Creates a new FacadePokemonCenter object.
     */
    public FacadePokemonCenter()
    {
        welcome = new Welcome();
        trainerNameCheck = new TrainerNameCheck();
        trainerIDCheck = new TrainerIDCheck();
        healAndPC = new HealAndPC();
        
        scanner = new Scanner(System.in);
    }
    
    /**
     * Prints out a welcome message.
     */
    public void welcomeUser()
    {
        welcome.welcome();
        System.out.println();
    }

    /**
     * Checks the user's name.
     */
    public void checkName()
    {
        do {} while (!trainerNameCheck.checkName(scanner));
        System.out.println();
    }
    
    /**
     * Checks the user's id.
     */
    public void checkID()
    {
        do {} while (!trainerIDCheck.checkID(scanner));
        System.out.println();
    }
    
    /**
     * Heals the pokemon on your team.
     */
    public void healPokemon()
    {
        healAndPC.withdrawPokemon();
        System.out.println();
        healAndPC.healPokemon();
        System.out.println();
        healAndPC.depositPokemon();
        System.out.println();
    }
    
    /**
     * Prints out a thank you message.
     */
    public void thankUser()
    {
        welcome.thank();
        scanner.close();
    }

}
