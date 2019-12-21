/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Observer Pattern
 * 11 April 2016
 */

/**
 * @author Zachary Gill
 *
 */
public class AccountObserver implements Observer
{

    private boolean sleep;
    
    private int subscriberCount;
    private int videoCount;
    private int viewerCount;

    private static int observerIDTracker = 0;
    
    /**
     * Unique observer id.
     */
    private int observerID;
    
    private Subject grabAccount;
    
    /**
     * Creates a new AccountObserver.
     */
    public AccountObserver(Subject grabAccount)
    {
        this.grabAccount = grabAccount;
        
        this.observerID = ++observerIDTracker;
        
        System.out.println("New Account " + this.observerID + "\n");
    
        grabAccount.register(this);
    }

    /* (non-Javadoc)
     * @see Observer#update(int, int, int)
     */
    @Override
    public void update(int subscriberCount, int videoCount, int viewerCount)
    {
        this.subscriberCount = subscriberCount;
        this.videoCount = videoCount;
        this.viewerCount = viewerCount;
     
        if (!sleep)
            printStats();
    }
    
    /**
     * Prints out the Youtube stats.
     */
    public void printStats()
    {
        System.out.print("\nAccount: " + observerID + "\n" +
                "Subscriber Count: " + subscriberCount + "\n" + 
                "Video Count: " + videoCount + "\n" + 
                "Viewer Count: " + viewerCount + "\n");
    }
    
    /**
     * Toggles if the account is sleeping or not.
     */
    public void toggleSleep()
    {
        sleep = !sleep;
    }

}
