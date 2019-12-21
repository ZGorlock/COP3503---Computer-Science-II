public class _Point implements Comparable<_Point>
{

	int row, col;
	
	public _Point( int row, int col )
	{
		this.row = row;
		this.col = col;
	}
	
	public int getCol()
	{
		return( col );
	}
	
	public int getRow()
	{
		return( row );
	}
	
	public int compareTo( _Point pt ) 
	{
		int val = pt.row * 40 + pt.col;
		int val2 = pt.row * 40 + pt.col;
		
		if(  val2 < val )
		{
			return( 1 );
		}
		else if( val2 > val )
		{
			return( -1 );
		}
		
		return 0;
	}
	
}
