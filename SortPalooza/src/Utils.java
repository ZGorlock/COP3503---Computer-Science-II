import java.util.Random;


public class Utils 
{
	static int[] GenerateRandomArray( int nCount )
	{
		int[] Ret = new int[nCount];
		Random rnd = new Random();
		
		for( int i=0; i<nCount; i++ )
		{
			Ret[i] = rnd.nextInt() & 0xffff;
		}

		return( Ret );
	}
	
	static void ShowDataArray(int Data[])
	{
		for (int i = 0; i < Data.length; i++)
		{
			System.out.print(""+Data[i]);			
			if (i < Data.length - 1)
			{
				System.out.print(", ");
			}
		}
		
		System.out.println("");
	}
	
}
