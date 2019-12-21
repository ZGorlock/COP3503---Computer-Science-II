/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Facade Pattern
 * 4 April 2016
 */

import java.util.*;


public class TestPokemonCenter 
{
	public static void main(String[] args) throws Exception
	{	
	    System.out.println(GetNameAndPID());

        Scanner scanner = new Scanner(System.in);
	    System.out.print("What is your name?: ");
	    String name = scanner.nextLine();
	    System.out.print("What is your trainer id?: ");
	    int id = Integer.decode(scanner.nextLine());
	    scanner.close();
	    
	    FacadePokemonCenter pokeCenter = new FacadePokemonCenter(id, name);
	    
	    pokeCenter.printTeamAndHealth();

	    pokeCenter.healPokemon();
	    
	    pokeCenter.printTeamAndHealth();
		
	}
	
	static String GetNameAndPID()
	{
		return( "Gill, Zachary, 3487163");
		//Please replace Last, First, and PID with your relevant details.
		//This function will be called in main.
	}
}
