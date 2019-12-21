/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Recitation 3.1
 * 3 February 2016
 */

/**
 * @author Zachary Gill
 *
 */
public class QuadraticProbeHashTable
{
    int m_nTableSize = 10007; //prime number
    DataObject[] m_ObjectArray;
    int n = 0;
    
    public QuadraticProbeHashTable()
    {
        m_ObjectArray = new DataObject[m_nTableSize];
    }
    
    public QuadraticProbeHashTable(int nTableSize)
    {
        nTableSize = Utility.getNextPrime(nTableSize - 1); //ensure that it is a prime number
        m_nTableSize = nTableSize;
        m_ObjectArray = new DataObject[m_nTableSize];
    }
    
    public void put( String strKey, DataObject objData )
    {
        if (objData == null || strKey == null) //ensure valid data object
            return;
        
        long lHash = Utility.HashFromString(strKey) % m_nTableSize;
        int q = 0;
    
        if (n >= m_nTableSize / 2) //test if more space is needed
            expand(); //resize
        
        while( m_ObjectArray[(int)((lHash + q*q)%m_nTableSize)] != null &&
                !m_ObjectArray[(int)((lHash + q*q)%m_nTableSize)].GetKey().equals(strKey))
        {
            q++;
        }
        
        m_ObjectArray[(int)((lHash + q*q)%m_nTableSize)] = objData; //add the new data to the table
        n++; //total elements
    }
    
    public DataObject get( String strKey )
    {
        long lHash = Utility.HashFromString(strKey) % m_nTableSize;
        int q = 0;
        
        while( m_ObjectArray[(int)((lHash + q*q)%m_nTableSize)] != null && //now if it reaches an index that is null it exit the loop and return that element which is null
                m_ObjectArray[(int)((lHash + q*q)%m_nTableSize)].GetKey() != strKey)
        {
            q++;
        }
    
        return( m_ObjectArray[(int)((lHash + q*q)%m_nTableSize)] );
    }
    
    public void expand()
    {
        if (n < m_nTableSize / 2) //ensure an expansion is needed
            return;
        
        DataObject[] old = m_ObjectArray; //store old array
        m_nTableSize = Utility.getNextPrime(m_nTableSize * 2 - 1); //get next prime number
        m_ObjectArray = new DataObject[m_nTableSize]; //create new array
        n = 0;
        
        for (int i = 0; i < old.length; i++) { //fill the new array with old values
            if (old[i] != null) //make sure object is not null
                put(old[i].GetKey(), old[i]);
        }
        
    }

}
