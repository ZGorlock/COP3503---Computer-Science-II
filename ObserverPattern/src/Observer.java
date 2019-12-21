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
public interface Observer
{
    
    public void update(int subscriberCount, int videoCount, int viewerCount);
    public void toggleSleep();
    
}
