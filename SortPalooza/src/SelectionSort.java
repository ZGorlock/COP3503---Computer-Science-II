
public class SelectionSort 
{
	public void DoSelectionSort( int[] array )
	{
		for( int i=0; i<array.length; i++ )
		{
			int keyindex = i;
			for( int j=i+1; j<array.length; j++ )
			{
				if( array[j] < array[keyindex] )
				{
					keyindex = j;
				}
			}
			
			int swp = array[i];
			array[i] = array[keyindex];
			array[keyindex] = swp;
			
		}
	}
}
