/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Facade Pattern
 * 4 April 2016
 */

/**
 * @author Zachary Gill
 *
 * This file will contain the main class used to test the program.
 */
public class TestPokemonCenter
{

    /**
     * The main class for the program.
     * 
     * @param args : Not used.
     */
    public static void main(String[] args)
    {
        FacadePokemonCenter pokeCenter = new FacadePokemonCenter();
        
        pokeCenter.welcomeUser(); //prints out a welcome message
        
        pokeCenter.checkName(); //checks the user's name
        pokeCenter.checkID(); //checks the user's id
        
        pokeCenter.healPokemon();
        
        pokeCenter.thankUser();
    }
}
