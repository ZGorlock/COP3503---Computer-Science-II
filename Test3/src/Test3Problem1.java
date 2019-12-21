/**
 * Zachary Gill
 * Graham Lupton
 * 
 * COP 3503
 * section number 0001
 * Test 3 Problem 1
 * 7 April 2016
 */

/**
 */
public class Test3Problem1
{
    //size of array
    static final int sizeX = 6;
    static final int sizeY = 6;
    
    //the array holding the squares, with blocked squares set to true
    static boolean squares[][] = new boolean[sizeX][sizeY];
    
    //the location of the bonus square
    static int bonusX, bonusY;
    
    //memoization
    static double memo[][] = new double[sizeX][sizeY]; //number of paths from x, y to bottom right
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        initializeSquares();
        
        //preset memo
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++)
                memo[i][j] = -1;
        }
        
        double pathCount = calculatePaths(0, 0);
        
        int paths = (int)pathCount;
        int bonusPaths = (int)(100000 * (pathCount - paths + .00000000001)); //.00001
        
        System.out.println("There are " + paths + " paths from one corner to another. There are " + bonusPaths + " paths from one corner to another which go through the bonus square.");
    }
    
    
    public static double calculatePaths(int x, int y)
    {
        double paths = 0;
        
        if (x < 0 || y < 0 || x > sizeX - 1 || y > sizeY - 1)
            return 0;
        
        if (x == bonusX && y == bonusY)
            paths += .00001;
        
        if (x == sizeX - 1 && y == sizeY - 1) //finished the path
            return 1 + paths;
        
        if (squares[x][y]) //if blocked
            return 0;
        
        if (memo[x][y] != -1)
            return memo[x][y];
        
        paths += calculatePaths(x + 1, y) + //move right
                calculatePaths(x, y + 1);  //move down
        
        memo[x][y] = paths;
        return memo[x][y];
    }
    
    public static void initializeSquares()
    {
        //initialize to problem on test
        squares[1][2] = true;
        squares[3][3] = true;
        
        //set bonus square
        bonusX = 3;
        bonusY = 2;
    }

}
