/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Facade Pattern
 * 4 April 2016
 */

import java.util.*;


public class HealAndPC 
{
	//This ArrayList will represent the Pokemon the trainer is currently carrying.
	//For the ease of coding, we will assume that the trainer has, indeed, caught them all.
	//We will also assume that the trainer has an infinite number of Pokemon in the PC.
	//This makes coding easier by assuming Pokemon are a commodity.
	//This also foregoes the process of keeping track of Pokemon stored on the PC.
	private ArrayList<Pokemon> pokemonTeam;
	
	//Sets up the trainer's team.
	public HealAndPC()
	{
		pokemonTeam = new ArrayList<Pokemon>();
		pokemonTeam.add(new Pokemon(25));
		pokemonTeam.add(new Pokemon(3));
		pokemonTeam.add(new Pokemon(6));
		pokemonTeam.add(new Pokemon(9));
		pokemonTeam.add(new Pokemon(131));
		pokemonTeam.add(new Pokemon(143));
	}
	
	//Returns the pokemon team
	public ArrayList<Pokemon> getPokemonTeam()
	{
        return pokemonTeam;
	}
	
	//heals the pokemon on your team
	public void healPokemon()
	{
	    for (int i = 0; i < pokemonTeam.size(); i++)
	        pokemonTeam.get(i).heal();
	}

	//returns a pokemon to the pc if it is in the team
	public void depositPokemon(int dexNum)
	{
        for (int i = 0; i < pokemonTeam.size(); i++) {
            if (pokemonTeam.get(i).getPokedexNumber() == dexNum) {
                pokemonTeam.remove(i);
                break;
            }
        }
	}
	
	//adds a pokemon from the pc to the team
	public void withdrawPokemon(int dexNum)
	{
	    if (!isTeamFull())
	        pokemonTeam.add(new Pokemon(dexNum));
	}
	
	//Tests if a given pokemon is in the team
	public boolean containsPokemon(int dexNum)
	{
	    for (int i = 0; i < pokemonTeam.size(); i++) {
	        if (pokemonTeam.get(i).getPokedexNumber() == dexNum)
	            return true;
	    }
	    return false;
	}
	
	//Tests if the team is empty
	public boolean isTeamEmpty()
	{
	    return (pokemonTeam.size() == 0);
	}
	
	//Tests if the team is full
	public boolean isTeamFull()
	{
	    return (pokemonTeam.size() == 6);
	}
	
	public void printTeamAndHealth()
	{
		//Another freebie.
		//Please don't change this!
		
		System.out.println("The current team is as follows: ");
		
		for(int i = 0; i < pokemonTeam.size(); i++)
		{
			Pokemon cur = pokemonTeam.get(i);
			System.out.println("Pokedex number: " + cur.getPokedexNumber() + "\n\tName: " + cur.getPokemonName() + "\n\tHealth: " + cur.getPercentHealth());
		}
	}

}
