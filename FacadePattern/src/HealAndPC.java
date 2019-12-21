/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Facade Pattern
 * 4 April 2016
 */

import java.util.ArrayList;
import java.util.Collections;


/**
 * This file will contain an ArrayList of Pokémon in the trainer’s party and handle their withdrawal, deposition, and healing.
 * 
 * @author Zachary Gill
 */
public class HealAndPC
{
    private static final int NUM_POKEMON = 6;
    
    private ArrayList<Pokemon> pokemon;

    /**
     * Creates a new HealAndPC object.
     */
    public HealAndPC()
    {
        pokemon = new ArrayList<Pokemon>();
    }
    
    /**
     * Withdraws your pokemon from the PC.
     */
    public void withdrawPokemon()
    {
        System.out.println("We will now withdraw your Pokemon from the PC!");
        for (int i = 0; i < NUM_POKEMON; i++) {
            pokemon.add(new Pokemon());
            System.out.println("Withdrawing " + pokemon.get(i).getName() + " from the PC!");
        }
    }
    
    /**
     * Deposits your pokemon in the PC.
     */
    public void depositPokemon()
    {
        System.out.println("We will now deposit your Pokemon in the PC!");
        Collections.reverse(pokemon);
        for (int i = pokemon.size() - 1; i >= 0; i--) {
            System.out.println("Depositing " + pokemon.get(i).getName() + " in the PC!");
            pokemon.remove(i);
        }
    }
    
    /**
     * Heals your pokemon.
     */
    public void healPokemon()
    {
        System.out.println("We will now heal your Pokemon!");
        for (int i = 0; i < pokemon.size(); i++) {
            System.out.println("Healing " + pokemon.get(i).getName() + " from " +
                    pokemon.get(i).getCurrentHp() + " HP" + " to " +
                    pokemon.get(i).getHp() + " HP!");
            pokemon.get(i).setCurrentHp(pokemon.get(i).getHp()); //heal the pokemon
        }
    }

}
