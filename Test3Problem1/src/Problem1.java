import java.util.Arrays;

/**
 * 
 */

/**
 * @author ZGorlock
 *
 */
public class Problem1
{
    
    int mat[][] = {{1, 2, 9},
                   {5, 3, 8},
                   {4, 6, 7}};
    
    int dp[][] = new int[3][3];

    
    /**
     * 
     */
    public Problem1()
    {
        //Arrays.fill(dp, 0);
        int max = 0;
        
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                int t = problem(i, j);
                if (t > max)
                    max = t;
            }
        }
        
        System.out.println(max);
    }
    
    public int problem(int x, int y) {
        if (x < 0 || y < 0)
            return 0;
        
        if (dp[x][y] > 0)
            return dp[x][y];
        
        int left = 0, up = 0, right = 0, down = 0;
        
        if (x > 0) {
        if (mat[x - 1][y] == 1 + mat[x][y])
            left = problem(x - 1, y);
        }
        
        if (y > 0) {
        if (mat[x][y - 1] == 1 + mat[x][y])
            up = problem(x, y - 1);
        }
        
        if (x < mat.length - 1) {
        if (mat[x + 1][y] == 1 + mat[x][y])
            right = problem(x + 1, y);
        }
        
        if (y < mat[0].length - 1) {
        if (mat[x][y + 1] == 1 + mat[x][y])
            down = problem(x, y + 1);
        }
        
        dp[x][y] = 1 + Math.max(left, Math.max(up, Math.max(right, down)));
        return dp[x][y];
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Problem1 aProblem1 = new Problem1();
    }

}
