/**
 * Zachary Gill
 * Graham Lupton
 * 
 * COP 3503
 * section number 0001
 * Test 3 Problem 2
 * 7 April 2016
 */

public class Question2 {
    
    static int memo[][];

	private static int minimizeTotal(int[] arr, int total, int mod, int idx) {
	    if (idx >= arr.length)
	       return total % mod;
	    
	    
        int val1;
	    if (memo[idx][0] != -1)
	        val1 = memo[idx][0];
	    else {
	        val1 = minimizeTotal(arr, total + arr[idx] + arr[idx+1] + 2, mod, idx + 2); //++
	        memo[idx][0] = val1;
	    }
	    
        int val2;
        if (memo[idx][1] != -1)
            val2 = memo[idx][1];
        else {
            val2 = minimizeTotal(arr, total + arr[idx] + arr[idx+1], mod, idx + 2); // +-
            memo[idx][1] = val2;
        }
        
        int val3;
        if (memo[idx][2] != -1)
            val3 = memo[idx][2];
        else {
            val3 = minimizeTotal(arr, total + arr[idx] + arr[idx+1] - 2, mod, idx + 2); //--
            memo[idx][2] = val3;
        }
		
        return Math.min(val1, Math.min(val2, val3));
	}
	
	public static void main(String[] args) {
		int n = 12;
		int mod = 5;
		
		int[] arr = new int[n];
		
		memo = new int[n][3];
		for (int i = 0; i < n; i++)
		    for (int j = 0; j < 3; j++)
		        memo[i][j] = -1;
		
		for (int i = 0; i < n; i++) {
			arr[i] = i + 1;
		}
		
		System.out.println("" + minimizeTotal(arr, 0, mod, 0));
	}

}
