
public class DataStruct implements Comparable<DataStruct>
{
	int key = 0;
	public int getKey()
	{
		return key;
	}

	public void setKey(int key)
	{
		this.key = key;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getWeight()
	{
		return weight;
	}

	public void setWeight(int weight)
	{
		this.weight = weight;
	}

	String name = "";
	int weight = 0;
	
	public DataStruct( int key, String name, int weight )
	{
		this.key = key;
		this.name = new String( name );
		this.weight = weight;
	}

	@Override
	public int compareTo(DataStruct arg0)
	{
		if( key > arg0.getKey() ) return( 1 );
		else if( key < arg0.getKey() ) return( -1 );
		return 0;
	}
}
