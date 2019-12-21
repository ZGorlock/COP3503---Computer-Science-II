/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Facade Pattern
 * 4 April 2016
 */

public class TrainerNameCheck {
	
	private String trainerName = "Ash Ketchum";
	//Please do not change this value.
	//I will be using this name to test your code. 
	
	public String getTrainerName()
	{
	    return trainerName;
	}
	
	public boolean trainerActive(String trainerNameToCheck)
	{
	    return (trainerNameToCheck.equals(trainerName));
	}
	
		

}
