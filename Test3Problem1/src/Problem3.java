import javax.swing.text.Highlighter.Highlight;

import sun.security.krb5.internal.rcache.MemoryCache;

/**
 * 
 */

/**
 * @author ZGorlock
 *
 */
public class Problem3
{
    static int[] arr = {2, 3, 4, 5, 6, 5};
    static int k = 1;
    
    static int[][] memo;
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        memo = new int[arr.length][arr.length];
        
        int result = minSize(0, arr.length - 1);
        
        System.out.println(result);
        
    }
    
    public static int minSize(int low, int high)
    {
        if (high-low+ 1 < 3)
            return high-low + 1;
        
        if (memo[low][high] > 0)
            return memo[low][high];
        
        int result = 1 + minSize(low + 1, high);
        
        for (int i = low + 1; i <= high; i++) {
            for (int j = i + 1; j <= high; j++) {
                
                if ((arr[i] - arr[low] == k) &&
                        (arr[j]- arr[i]==k  ) &&
                        (minSize(low + 1, i - 1) == 0) &&
                        (minSize(i+1,j-1) == 0)) {
                    result = Math.min(result, minSize(j + 1, high));
                }
                
            }
        }
        
        memo[low][high] = result; 
        return result;
    }

}
