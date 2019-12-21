
class Edge
{
	// The target Vertex for this Edge.
    Vertex m_objTarget;
    
    // Constructor which takes the target Vertex.
    public Edge( Vertex objTarget )
    { 
    	m_objTarget = objTarget; 
    }

    // Get the target Vertex.
    public Vertex GetTarget()
    {
    	return( m_objTarget );
    }
    
}
