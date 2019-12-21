import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TileGame extends Applet implements MouseListener
{

	private static final long serialVersionUID = 1L;

	int NumRows;
	int[] NumCols;
	Vertex[] VertexList;
	int TotalVertices;
	
	void LoadGraph() throws FileNotFoundException
	{
    	// Set up and open the input.txt file.
   		Scanner in = new Scanner(new File("input.txt"));

   		NumRows = in.nextInt();
   		NumCols = new int[NumRows];
   		TotalVertices = in.nextInt();
   		VertexList = new Vertex[TotalVertices];

   		int VertexIndex = 0;
   		for( int Row=0; Row<NumRows; Row++ )
   		{
   			NumCols[Row] = in.nextInt();
   			for( int Col=0; Col<NumCols[Row]; Col++ )
   			{
   				VertexList[VertexIndex] = new Vertex();
   				int x = in.nextInt();
   				int y = in.nextInt();
   				int ci = in.nextInt();
   				VertexList[VertexIndex].setX( 40 + x * 30 );
   				VertexList[VertexIndex].setY( 40 + y * 50 );
   				VertexList[VertexIndex].setColorIndex( ci );
   				VertexList[VertexIndex].setVertexIndex( VertexIndex );
   				VertexIndex++;
   			}
   		}
   		
   		// Close the scanner object.
   		in.close();
	}
	
	public void init() 
	{
        setSize(1020,700);
        addMouseListener( this );
        try{ LoadGraph(); } catch(Exception ex){}
	}

	public void paint (Graphics g)
	{
		if( VertexList == null ) return;
		
		for( int v=0; v<VertexList.length; v++ )
		{
			VertexList[v].paint( g );
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent ms)
	{
		for( int v=0; v<VertexList.length; v++ )
		{
			if( VertexList[v].ClickedMe( ms.getX(), ms.getY()) )
			{
				VertexList[v].setVisible( false );
				repaint();
				break;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
}
