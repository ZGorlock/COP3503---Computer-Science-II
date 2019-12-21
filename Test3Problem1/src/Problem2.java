import sun.security.krb5.internal.rcache.MemoryCache;

/**
 * 
 */

/**
 * @author ZGorlock
 *
 */
public class Problem2
{
    
    static int[] memo;

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        int steps = 20;
        memo = new int[steps];
        
        int ways = ways(steps);
        
        System.out.println(ways);
        
    }
    
    public static int ways(int dis) {
        if (dis < 0)
            return 0;
        
        if (dis == 0 )
            return 1;
        
        if (memo[dis - 1] > 0)
            return memo[dis - 1];
        
        memo[dis - 1] =  ways(dis - 1) + ways(dis - 2) + ways(dis - 3);
        return  memo[dis - 1];
    }

}
