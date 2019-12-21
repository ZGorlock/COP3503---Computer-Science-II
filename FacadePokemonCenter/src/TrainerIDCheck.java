/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Facade Pattern
 * 4 April 2016
 */

public class TrainerIDCheck {
	
	private int trainerID = 01301;
	//Please do not change this value.
	//I will be using this ID to test your code. 
	
	public int getTrainerID()
	{
	    return trainerID;
	}
	
	public boolean trainerActive(int trainerIDToCheck)
	{
	    return (trainerIDToCheck == trainerID);
	}
	
		

}