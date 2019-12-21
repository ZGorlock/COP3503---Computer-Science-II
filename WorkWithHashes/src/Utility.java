
public class Utility 
{
	
	public static long HashFromString( String strString)
	{
		long lHashValue = 0;
		
		for( int i=0; i<strString.length(); i++ )
		{
			lHashValue = (long)strString.charAt(i) + (lHashValue << 6) + (lHashValue << 16) - lHashValue;
		}
		
		return( lHashValue & 0x7fffffff );
	}
	
	/**
	 * Gets the next prime number.
	 * @param n : The number to start looking at
	 * @return The next prime number
	 */
	public static int getNextPrime(int n)
	{
	    if (n < 2)
	        return 2;
	    
	    for (int i = n; i < 2 * n; ++i)
	    {
	        if (isPrime(i))
	            return i;
	    }
	    
	    return n;
	}
	
	/**
	 * Tests if a number is prime.
	 * @param n : The number to test.
	 * @return Whether the number is prime or not
	 */
	public static boolean isPrime(int n) {
        if (n % 2 == 0)
            return false;
        
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return false;
        }
        
        return true;
    }
	
	
}
