import java.io.File;
import java.util.Scanner;

public class DFSWithDataStructures 
{

	// Constructor which takes a file name string
	//   to the data.
	public DFSWithDataStructures( String strFileName )
	{
		// Call the method that loads the data.
		LoadGraph( strFileName );
	}

	// Vertex array.
    Vertex v[];

    // Return a vertex based on an index.
    public Vertex GetVertex( int nIndex )
    {
    	return( v[nIndex] );
    }
    
    void LoadGraph( String strFileName )
    {
    	// Scanner for reading.
   		Scanner in = null;
   		try
   		{
   			// Open the data file for reading.
   			in = new Scanner(new File(strFileName));
   		}
   		catch(Exception ex)
   		{
   		}

   		// Get the number of vertices and allocated
   		//   the Vertex array.
   		int nNumVertices = in.nextInt();
   		v = new Vertex[nNumVertices];

   		// Create the vertex objects from the
   		//   data file.
   		for( int i=0; i<nNumVertices; i++ )
   		{
   			v[i] = new Vertex( in.next(), i );
   		}

   		// Loop through the vertices.
   		for( int i=0; i<nNumVertices; i++ )
   		{
   			// Read in the number of edges that this
   			//   Vertex is connected to.
   			int nNumEdges = in.nextInt();
   			
   			// Set the adjacencies so that the Vertex
   			//   class has an Edge array large enough
   			//   to accommodate all Edge objects.
   			v[i].SetAdjacencies( new Edge[nNumEdges] );
   			
   	   		// Loop through the edges.
   			for( int j=0; j<nNumEdges; j++ )
   			{
   				// Get the vertex index.
   				int nVertexIndex = in.nextInt();
   				// Create the new Edge object.
   				v[i].GetAdjacencies()[j] = new Edge( v[nVertexIndex] );
   			}
   		}

   		// Close the Scanner object.
   		in.close();
    }

    // Do a depth first search from the given Vertex.
	void DFS(Vertex objNode)
	{
		// Set this Vertex to visited.
		objNode.SetVisited(true);
		// Show this node: name and index.
		System.out.println(objNode.GetIndex() + ", " + objNode.GetName());

		// Loop through the edges coming
		//   out of this Vertex.
		for (int i = 0; i < objNode.GetAdjacencies().length; i++)
		{
			// If target Vertex of this Edge has not been
			//   visted then we want to call DFS on it.
			if (!objNode.GetAdjacencies()[i].GetTarget().GetVisited())
			{
				// Recursively call DFS.
				DFS(objNode.GetAdjacencies()[i].GetTarget());
			}
		}
	}

	// Reset all visited flags to false
	//   for all Vertices.
	public void ResetVisited()
	{
		for( int i=0; i<v.length; i++ )
		{
			v[i].SetVisited(false);
		}
	}
	
	public static void main(String[] args)
	{
		DFSWithDataStructures g = new DFSWithDataStructures("input.txt");
		
		// Reset all visited flags and call
		//   DFS on Vertex with index 0.
		g.ResetVisited();
		System.out.println("DFS(0):"); g.DFS(g.GetVertex(0));

		// Reset all visited flags and call
		//   DFS on Vertex with index 3.
		g.ResetVisited();
		System.out.println("DFS(3):"); g.DFS(g.GetVertex(3));
	}
	
}
