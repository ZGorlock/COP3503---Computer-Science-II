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
public interface Subject
{

    /**
     * Registers a new Observer to the Subject.
     * 
     * @param o: The new observer.
     */
    public void register(Observer o);
    
    /**
     * Unregisters an Observer from the Subject.
     * 
     * @param o: The Observer to unregister.
     */
    public void unregister(Observer o);

    /**
     * Notifies the Observers this Subject manages.
     */
    public void notifyObserver();

}
