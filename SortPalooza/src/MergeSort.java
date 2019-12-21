
public class MergeSort 
{
	
	public void DoMergeSort( int[] a )
	{
		DoMergeSort( a, 0, a.length-1);
	}

	public void DoMergeSort( int[] a, int left, int right )
	{
		if( left >= right ) return;
		
		int center = left + ( right - left ) / 2;
		DoMergeSort( a, left, center );
		DoMergeSort( a, center + 1, right );
		Merge( a, left, center, right );
	}
	
	void Merge( int[] a, int left, int center, int right )
	{
		int[] tmp = new int[a.length];
		for( int i=0; i<tmp.length; i++ ) tmp[i] = a[i];

		int ai = left; int bi = center + 1; int ci = left;
		
		while( ai <= center && bi <= right )
		{
			if( tmp[ai] < tmp[bi] ) a[ci++] = tmp[ai++];
			else a[ci++] = tmp[bi++];
		}
		
		while( ai <= center )
		{
			a[ci++] = tmp[ai++];
		}
		
		while( bi <= right )
		{
			a[ci++] = tmp[bi++];
		}
		
	}
	
}
