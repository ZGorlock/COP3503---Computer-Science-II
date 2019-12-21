
public class BubbleSort2
{
	public void DoBubbleSort( DataStruct[] array )
	{
		for( int i=0; i<array.length; i++ )
		{
			for( int j=0; j<array.length-1-i; j++ )
			{
				if( array[j].compareTo( array[j+1] ) == 1 )
				{
					int swp = array[j].getKey();
					array[j].setKey( array[j+1].getKey() );
					array[j+1].setKey( swp );
				}
			}
		}
	}

}
