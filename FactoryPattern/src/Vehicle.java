/**
 * Zachary Gill
 * COP 3503
 * Factory Pattern
 * 28 March 2016
 */

/**
 * @author Zachary Gill
 *
 */
public abstract class Vehicle implements IVehicle
{
    
    //Fields
    
    protected String name;
    protected int wheels;
    protected int engines;

    
    //Methods
    
    /**
     * Creates a new generic vehicle.
     */
    public Vehicle()
    {
        name = "";
        wheels = 0;
        engines = 0;
    }
    
    /**
     * Returns the name of the vehicle.
     * @return The name of the vehicle.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Returns the number of wheels on the vehicle.
     * @return The number of wheels on the vehicle.
     */
    public int getWheels()
    {
        return wheels;
    }
    
    /**
     * Returns the number of engines on the vehicle.
     * @return The number of engines on the vehicle.
     */
    public int getEngines()
    {
        return engines;
    }

}
