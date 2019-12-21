
public class SortPalooza 
{

	public static void main(String[] args )
	{
		int[] a = Utils.GenerateRandomArray( 10 );
		Utils.ShowDataArray( a );
		MergeSort is = new MergeSort();
		is.DoMergeSort( a );
		Utils.ShowDataArray( a );
	}
}
