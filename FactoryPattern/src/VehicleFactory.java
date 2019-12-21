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
public class VehicleFactory
{

    /**
     * Creates a new vehicle.
     */
    public static Vehicle makeVehicle(String s)
    {
        Vehicle newVehicle = null;
        
        if (s.equals("A")) {
            return new Airplane();
        }
        else if (s.equals("B")) {
            return new Boat();
        }
        else if (s.equals("C")) {
            return new Car();
        }
        
        return newVehicle;
    }

}
