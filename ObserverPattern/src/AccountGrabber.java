/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Observer Pattern
 * 11 April 2016
 */

import java.util.ArrayList;
import java.util.List;


/**
 * @author Zachary Gill
 *
 */
public class AccountGrabber implements Subject
{
   /**
     * Stores a list of Observers that the Subject manages.
     */
    private List<Observer> observers;

    private int subscriberCount;
    private int videoCount;
    private int viewerCount;

    
    /**
     * Creates a new AccountGrabber.
     */
    public AccountGrabber()
    {
        observers = new ArrayList<Observer>();
        
        subscriberCount = 0;
        videoCount = 0;
        viewerCount = 0;
    }
    

    
    @Override
    public void register(Observer o)
    {
        if (!observers.contains(o))
            observers.add(o);
    }
    
    @Override
    public void unregister(Observer o)
    {
        int oIndex = observers.indexOf(o);
        observers.remove(o);
        
        System.out.println("Account " + (oIndex + 1) + " deleted!\n");
    }
    
    @Override
    public void notifyObserver()
    {
        for (Observer observer : observers) {
            observer.update(subscriberCount, videoCount, viewerCount);
        }
        System.out.println();
        System.out.println("All Users Updates and Notified Accordingly\n");
    }
    
    /**
     * Toggles the sleep mode of an account.
     * 
     * @param index: The index of the account.
     */
    public void toggleSleep(int index)
    {
        if (!(index >= 0 && index < observers.size()))
            return;
        
        observers.get(index).toggleSleep();
    }
    
    /**
     * Deletes an account.
     * 
     * @param index: The index of the account.
     */
    public void deleteAccount(int index)
    {
        if (!(index >= 0 && index < observers.size()))
            return;
        
        unregister(observers.get(index));
    }
    
    /**
     * Sets the youtube stats.
     * 
     * @param subscriberCount: The new subscriber count. 
     * @param videoCount: The new video count.
     * @param viewerCount: The new viewer count.
     */
    public void update(int subscriberCount, int videoCount, int viewerCount)
    {
        this.subscriberCount = subscriberCount;
        this.videoCount = videoCount;
        this.viewerCount = viewerCount;
        notifyObserver();
    }
    
    /**
     * Sets the subscriber count to the passed value.
     * 
     * @param subscriberCount: The new subscriber count.
     */
    public void setSubscriberCount(int subscriberCount)
    {
        this.subscriberCount = subscriberCount;
        notifyObserver();
    }
    
    /**
     * Sets the video count to the passed value.
     * 
     * @param videoCount: The new video count.
     */
    public void setVideoCount(int videoCount)
    {
        this.videoCount = videoCount;
        notifyObserver();
    }
    
    /**
     * Sets the viewer count to the passed value.
     * 
     * @param viewerCount: The new viewer count.
     */
    public void setViewerCount(int viewerCount)
    {
        this.viewerCount = viewerCount;
        notifyObserver();
    }

}
