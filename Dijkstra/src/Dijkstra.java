import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Dijkstra
{
	
    public static void computePaths(Vertex source)
    {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
      	vertexQueue.add(source);

      	while (!vertexQueue.isEmpty()) 
      	{
      		Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies)
            {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                
                if (distanceThroughU < v.minDistance) 
                {
                	vertexQueue.remove(v);
                	v.minDistance = distanceThroughU ;
                	v.previous = u;
                	vertexQueue.add(v);
                }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
        {
            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    }
    
    public static void main(String[] args) throws FileNotFoundException
    {
    	String filename = "input.txt";
   		Scanner in = new Scanner(new File(filename));

   		int nNumVertices = in.nextInt();
   		Vertex v[] = new Vertex[nNumVertices];
   		
   		for( int i=0; i<nNumVertices; i++ )
   		{
   			v[i] = new Vertex( in.next() );
   		}

   		for( int i=0; i<nNumVertices; i++ )
   		{
   			int nNumEdges = in.nextInt();
   			v[i].adjacencies = new Edge[nNumEdges];
   			for( int j=0; j<nNumEdges; j++ )
   			{
   				int nVertexIndex = in.nextInt();
   				int nEdgeWeight = in.nextInt();
   				v[i].adjacencies[j] = new Edge( v[nVertexIndex], nEdgeWeight );
   			}
   		}
   		
   		in.close();
   		
        computePaths(v[0]);
        for (int i=0; i<v.length; i++)
        {
        	System.out.println("Distance to " + v[i] + ": " + v[i].minDistance);
        	List<Vertex> path = getShortestPathTo(v[i]);
        	System.out.println("Path: " + path);
        }
    }
}
