
class Vertex
{
	// The name of the Vertex
    String m_strName;
    // The index of the Vertex.
    int m_nIndex;
    // List of Edge objects leaving
    //   this Vertex.
    Edge[] m_objAdjacencies;
    // Parent of this Vertex.
    Vertex m_objParent;
    // Visited flag.
    boolean m_bVisited = false;

    // Constructor that takes the name
    //   and index of the Vertex.
    public Vertex( String strName, int nIndex ) 
    { 
    	m_strName = strName; 
    	m_nIndex = nIndex;
    }

    // Set the Edge array to an Edge array.
    public void SetAdjacencies( Edge[] objAdjacencies )
    {
    	m_objAdjacencies = objAdjacencies;
    }

    // Get parent vertex.
    public Vertex GetParent()
    {
    	return( m_objParent );
    }

    // Set the parent Vertex.
    public void SetParent( Vertex objParent )
    {
    	m_objParent = objParent;
    }

    // Get the adjacency list.
    public Edge[] GetAdjacencies()
    {
    	return( m_objAdjacencies );
    }

    // Get the name of the Vertex.
    public String GetName() 
    { 
    	return( m_strName ); 
    }
    
    // Get the visited flag.
    public boolean GetVisited()
    {
    	return( m_bVisited );
    }

    // Set the visited flag.
    public void SetVisited( boolean bStatus )
    {
    	m_bVisited = bStatus;
    }

    // Get the Vertex index value.
    public int GetIndex()
    {
    	return( m_nIndex );
    }
    
}
