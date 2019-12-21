/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Factory Pattern
 * 28 March 2016
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * @author Zachary Gill
 *
 */
public class InventoryTesting
{
    
    //Fields
    
    public static List<Vehicle> inventory = new ArrayList<Vehicle>();
    private static int airplanes;
    private static int boats;
    private static int cars;
    
    
    //Methods
    
    public static void main(String[] args)
    {
        getUserInput();
        
        createNewVehicles();
        
        generateReport();
    }
    
    /**
     * Gets user input.
     */
    private static void getUserInput()
    {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("How many Airplanes?: "); //get number of airplanes
        airplanes = scanner.nextInt();

        System.out.println("How many Boats?: "); //get number of boats
        boats = scanner.nextInt();

        System.out.println("How many Cars?: "); //get number of cars
        cars = scanner.nextInt();
        
        scanner.close();
    }
    
    /**
     * Fills the inventory with vehicles.
     */
    private static void createNewVehicles()
    {
        for (int i = 0; i < airplanes; i++)
            inventory.add(VehicleFactory.makeVehicle("A")); //put airplanes in inventory
        
        for (int i = 0; i < boats; i++)
            inventory.add(VehicleFactory.makeVehicle("B")); //put boats in inventory
        
        for (int i = 0; i < cars; i++)
            inventory.add(VehicleFactory.makeVehicle("C")); //put cars in inventory
    }
    
    /**
     * Creates a report of the inventory.
     */
    private static void generateReport()
    {
        System.out.println();
        System.out.println("---Inventory Report---");
        
        System.out.println("Number of Airplanes: " + airplanes);
        System.out.println("Number of Boats: " + boats);
        System.out.println("Number of Cars: " + cars);

        System.out.println();

        System.out.println("Required number of wheels: " + countWheels());
        System.out.println("Required number of engines: " + countEngines());
    }
    
    /**
     * Counts the number of wheels in the inventory.
     * @return The total number of wheels.
     */
    public static int countWheels()
    {
        int wheels = 0;
        
        for (Vehicle vehicle : inventory) {
            wheels += vehicle.getWheels();
        }
        
        return wheels;
    }
    
    /**
     * Counts the number of engines in the inventory.
     * @return The total number of engines.
     */
    public static int countEngines()
    {
        int engines = 0;
        
        for (Vehicle vehicle : inventory) {
            engines += vehicle.getEngines();
        }
        
        return engines;
    }
    
}
