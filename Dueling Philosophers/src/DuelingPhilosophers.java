/**
 * Zachary Gill
 * COP 3503
 * Section number 0001
 * Recitation 6
 * 25 February 2016
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * @author Zachary Gill
 *
 */
public class DuelingPhilosophers
{
    /**
     * Stores the relationships between essays in a graph
     */
    boolean[][] graph;
    
    /**
     * Stores which nodes have already been visited
     */
    boolean[] visited;
    
    /**
     * Stores which nodes are on the stack
     */
    boolean[] onStack;
    
    /**
     * Stores the topological sort of the graph
     */
    int[] sort;
    int sortIndex;
    
    File file;
    Scanner scanner;
    
    
    /**
     * Constructor for class, intializes graph and reads input
     */
    public DuelingPhilosophers()
    {
        file = new File("input.txt");
        
        try {
            scanner = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            System.out.println("input.txt could not be found");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * Reads graph data from the file
     */
    public void readGraph()
    {
        int nodeCount = scanner.nextInt(); //get number of nodes
        int edgeCount = scanner.nextInt(); //get number of edges
        
        if (nodeCount == 0 && edgeCount == 0) { //if no more input
            scanner.close();
            System.exit(0);
        }
        
        graph = new boolean[nodeCount][nodeCount]; //initialize graph
        
        visited = new boolean[nodeCount];
        onStack = new boolean[nodeCount];
        
        sort = new int[nodeCount];
        sortIndex = nodeCount - 1;
        
        //fill graph
        for (int i = 0; i < edgeCount; i++) {
            int source = scanner.nextInt();
            int target = scanner.nextInt();
            
            graph[source - 1][target - 1] = true; //save dependency in graph
        }
    }
    
    /**
     * Calculates the graph value
     * @return 0 if there is no way to order the essays
     *         1 if there is exxactly one way to order the essays
     *         2 if there are multiple ways to order the essays
     */
    public void calculateGraphValue()
    {
        int rv = 0;
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                rv = topologicalSort(i); //perform topological sort
                
                if (rv == 0) //if a cycle is found, stop working
                    break;
            }
        }

        if (rv == 0) //there was a  cycle
            System.out.println(0);
        else if (isUnique()) //the sort was unique
            System.out.println(1);
        else //there are multiple ways to sort
            System.out.println(2);
        
        //for (int i = 0; i < graph.length; i++)
        //    System.out.print(sort[i] + 1);
        //System.out.println();
    }
    
    /**
     * Topologically sort the graph
     * @param node: The node you are looking at
     * @return 0 if there is a cycle
     *         1 if sorted successfully
     */
    public int topologicalSort(int node)
    {
        visited[node] = true;
        onStack[node] = true;
        
        for (int i = 0; i < graph.length; i++) {
            if (graph[node][i] && visited[i] && onStack[i]) //there is a cycle
                return 0;
            
            if (graph[node][i] && !visited[i]) { //can move to new node
                int rv = topologicalSort(i);
                
                if (rv == 0) //there was a different cycle
                    return 0;
            }
        }
        
        onStack[node] = false;
        sort[sortIndex--] = node; //write to topological sort array
        
        return 1;
    }
    
    /**
     * Tests if the topological sort is unique
     * @return Whether the sort is unique or not
     */
    public boolean isUnique()
    {
        for (int i = 0; i < sort.length - 1; i++) {
            if (!graph[sort[i]][sort[i + 1]]) //if the node doesnt directly point to the next node
                return false; //there are other ways to sort it
        }
        
        return true; //there is only one way to sort it
    }

    /**
     * @param args: unused
     */
    public static void main(String[] args)
    {
        //Create dependency graph
        DuelingPhilosophers dp = new DuelingPhilosophers();
        
        while (true) {
            //Read the new graph data from file
            dp.readGraph();
            
            //Perform topological sort and calculate graph value
            dp.calculateGraphValue();
        }
    }

}
