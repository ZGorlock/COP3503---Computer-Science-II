import java.util.ArrayList;

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
public class SeperateChainingHashTable
{
    int m_nTableSize = 10000;
    Object[] m_ObjectArray;
    int n = 0;
    
    public SeperateChainingHashTable()
    {
        m_ObjectArray = new Object[m_nTableSize];
    }

    public SeperateChainingHashTable(int nTableSize)
    {
        m_nTableSize = nTableSize;
        m_ObjectArray = new Object[m_nTableSize];
    }

    @SuppressWarnings("unchecked")
    public void put( String strKey, DataObject objData )
    {
        if (objData == null || strKey == null) //ensure valid data object
            return;
        
        long lHash = Utility.HashFromString(strKey) % m_nTableSize;
        
        if (m_ObjectArray[(int)(lHash%m_nTableSize)] == null) {
            m_ObjectArray[(int)(lHash%m_nTableSize)] = new ArrayList<DataObject>(); //create a new arraylist for that index
            n++;
        }
        
        ((ArrayList<DataObject>) m_ObjectArray[(int)(lHash%m_nTableSize)]).add(objData);
    }
    
    @SuppressWarnings("unchecked")
    public DataObject get( String strKey )
    {
        if (strKey == null) //ensure valid key
            return null;
        
        long lHash = Utility.HashFromString(strKey) % m_nTableSize;
        
        if (m_ObjectArray[(int)(lHash%m_nTableSize)] == null) //if key doesnt exist in table
            return null;
        
        for (int i = 0; i < ((ArrayList<DataObject>) m_ObjectArray[(int)(lHash%m_nTableSize)]).size(); i++) { //look through chained DataObjects
            if ( ((ArrayList<DataObject>) m_ObjectArray[(int)(lHash%m_nTableSize)]).get(i).GetKey().equals(strKey) )
                return ((ArrayList<DataObject>) m_ObjectArray[(int)(lHash%m_nTableSize)]).get(i); //return the DataObject
        }
        
        return null; //key not found
    }
    
}
