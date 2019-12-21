import java.util.Scanner;

/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Facade Pattern
 * 4 April 2016
 */

public class FacadePokemonCenter 
{
    private String name;
    private int id;

    private Welcome welcome;
    private TrainerNameCheck trainerNameCheck;
    private TrainerIDCheck trainerIDCheck;
    private HealAndPC healAndPC;

	//Creates a FacadePokemonCenter object.
	public FacadePokemonCenter(int newID, String newName)
	{
	    name = newName;
	    id = newID;
	    
        welcome = new Welcome();
        trainerNameCheck = new TrainerNameCheck();
        trainerIDCheck = new TrainerIDCheck();
        healAndPC = new HealAndPC();
		
	}
	
	//Returns the trainer id
	public int getTrainerID()
	{
	    return id;
	}
	
	//Returns the trainer name
	public String getTrainerName()
	{
	    return name;
	}
	
	//Heals your pokemon
	public void healPokemon()
	{
	    if (trainerIDCheck.trainerActive(id) &&
	            trainerNameCheck.trainerActive(name) &&
	            !healAndPC.isTeamEmpty()) {
	        healAndPC.healPokemon();
	        System.out.println("Sucessfully healed your Pokemon!");
	    }
	    else {
            System.out.println("We could not heal your Pokemon!");
	    }
	}
	
	//Deposits your pokemon
	public void depositPokemon(int dexNum)
	{
        if (trainerIDCheck.trainerActive(id) &&
                trainerNameCheck.trainerActive(name) &&
                !healAndPC.isTeamEmpty() &&
                dexNum > 0 && dexNum < 722 &&
                healAndPC.containsPokemon(dexNum)) {
            healAndPC.depositPokemon(dexNum);
            System.out.println("Sucessfully deposited Pokemon " + dexNum + "!");
        }
        else {
            System.out.println("Unable to deposit Pokemon " + dexNum + "!");
        }
	}
	
	//Withdraws your pokemon
	public void withdrawPokemon(int dexNum)
	{
        if (trainerIDCheck.trainerActive(id) &&
                trainerNameCheck.trainerActive(name) &&
                !healAndPC.isTeamFull() &&
                dexNum > 0 && dexNum < 722) {
            healAndPC.withdrawPokemon(dexNum);
            System.out.println("Sucessfully withdrew Pokemon " + dexNum + "!");
        }
        else {
            System.out.println("Unable to deposit Pokemon " + dexNum + "!");
        }
	}
	
	//Prints team and health
	public void printTeamAndHealth()
	{
	    healAndPC.printTeamAndHealth();
	}
	

}
