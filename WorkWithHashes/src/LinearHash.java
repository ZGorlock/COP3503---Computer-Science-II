/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Recitation 3.1
 * 3 February 2016
 */

public class LinearHash 
{
	int m_nTableSize = 10000;
	DataObject[] m_ObjectArray;
	int n = 0;
	
	public LinearHash()
	{
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	public LinearHash(int nTableSize)
	{
		m_nTableSize = nTableSize;
		m_ObjectArray = new DataObject[m_nTableSize];
	}
	
	public void put( String strKey, DataObject objData )
	{
	    if (objData == null || strKey == null) //ensure valid data object
	        return;
	    
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;

		if (n >= m_nTableSize / 2) //test if more space is needed
		    expand(); //resize
		
		while( m_ObjectArray[(int)(lHash%m_nTableSize)] != null &&
		        !m_ObjectArray[(int)(lHash%m_nTableSize)].GetKey().equals(strKey))
		{
			lHash++;
		}
		
		m_ObjectArray[(int)(lHash%m_nTableSize)] = objData; //add the new data to the table
		n++; //total elements
	}

	public DataObject get( String strKey )
	{
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;

		while( m_ObjectArray[(int)(lHash%m_nTableSize)] != null && //now if it reaches an index that is null it exit the loop and return that element which is null
		        m_ObjectArray[(int)(lHash%m_nTableSize)].GetKey() != strKey)
		{
			lHash++;
		}

		return( m_ObjectArray[(int)(lHash%m_nTableSize)] );
	}
	
	public void expand()
	{
	    if (n < m_nTableSize / 2) //ensure an expansion is needed
	        return;
	    
	    DataObject[] old = m_ObjectArray; //store old array
	    m_nTableSize = m_nTableSize * 2;
	    m_ObjectArray = new DataObject[m_nTableSize]; //create new array
	    n = 0;
	    
	    for (int i = 0; i < old.length; i++) { //fill the new array with old values
	        if (old[i] != null) //make sure object is not null
	            put(old[i].GetKey(), old[i]);
	    }
	    
	}
	
}
