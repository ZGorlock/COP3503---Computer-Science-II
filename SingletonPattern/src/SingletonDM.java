import java.util.*;

public class SingletonDM 
{
	//Here, put the specific private static variable that makes this class follow the singleton pattern.
    private static SingletonDM instance;
	
	//The array of randomized character sheets.
	//Feel free to hardcode a few of these for your testing.
	private PlayerCharacter sheets[] = 
	{
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(), 
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(), 
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(),
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(), 
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(), 
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter(), 
		new PlayerCharacter(), new PlayerCharacter(), new PlayerCharacter()
	};
	
	//The sheets array remade as a list for convenience.
	private LinkedList<PlayerCharacter> sheetList = new LinkedList<PlayerCharacter>(Arrays.asList(sheets));
	
	static boolean firstThread = true;
	
	//Here, put the specific kind of constructor that makes this class follow the singleton pattern.
	private SingletonDM() {}
	
	public static SingletonDM getInstance()
	{
        //This will act as the "true" constructor for this class.
        //Its details should include but not be limited to the following:
        //      - Check if this is the first thread.
        //      - Check the private static variable at the top of the class.
        //      - Have the "synchronized" key word in there somewhere.
        //      - Return some kind of SingletonDM
	    
	    if (instance == null) {
	        if (firstThread) {
	            firstThread = false;
	            
	            Thread.currentThread();
	            try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
	        }
	        
	        synchronized(SingletonDM.class) {
	            if (instance == null)
	                instance = new SingletonDM();
	        }
	        
	    }
	    
	    return instance;
	}
	
	public String GetNameAndPID()
	{
		return( "Gill, Zachary, 3487163");
		//Please replace Last, First, and PID with your relevant details.
		//This function will be called in main.
	}
	
	public LinkedList<PlayerCharacter> getSheetList()
	{
	    return sheetList;
	}
	
	public LinkedList<PlayerCharacter> getSheetsOfLevel(int level)
	{
		//This should find all characters of a certain level in the list, and return them in a separate list.
		//Note: do not remove these characters from the list itself!
		//Just find them and put them in their own list, then return that list.
	    LinkedList<PlayerCharacter> levelPlayers = new LinkedList<PlayerCharacter>();
	    
	    for (PlayerCharacter pc : sheetList) {
	        if (pc.getLevel() == level)
	            levelPlayers.add(pc);
	    }
	    
	    return levelPlayers;
	}
	
	public LinkedList<PlayerCharacter> getSheetsOfType(String type)
	{
		//This should find all characters of a certain type in the list, and return them in a separate list.
		//Note: do not remove these characters from the list itself!
		//Just find them and put them in their own list, then return that list.
        LinkedList<PlayerCharacter> typePlayers = new LinkedList<PlayerCharacter>();
        
        for (PlayerCharacter pc : sheetList) {
            if (pc.gettype() == type)
                typePlayers.add(pc);
        }
        
        return typePlayers;
	}
}
