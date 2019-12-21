import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Queue;

public class AStar extends Applet implements MouseListener
{
	
	private static final long serialVersionUID = 1L;
	int[][] grid = new int[40][40];
	
	public void init()
	{
		addMouseListener(this);
        setSize(1020,700);
        
        grid[20][20] = 100;
        
        for( int i=0; i<20; i++ ) grid[5][i+10] = 2;
        for( int i=0; i<20; i++ ) grid[15][i+5] = 2;
        for( int i=0; i<20; i++ ) grid[25][i] = 2;
        for( int i=0; i<20; i++ ) grid[35][i+15] = 2;

        for( int i=0; i<20; i++ ) grid[i+2][5] = 2;
        for( int i=0; i<20; i++ ) grid[i+18][35] = 2;
	}
	
	public void paint( Graphics canvas )
	{
		int w = getWidth();
		int h = getHeight();
		int bw = w / 40 - 2;
		int bh = h / 40 - 2;
		
		for( int row=0; row<40; row++ )
		{
			for( int col=0; col<40; col++ )
			{
				switch( grid[row][col] )
				{
					case 0:
						canvas.setColor(Color.lightGray);
						break;
					case 1:
						canvas.setColor(Color.green);
						break;
					case 2:
						canvas.setColor(Color.blue);
						break;
					case 100:
						canvas.setColor(Color.red);
						break;
				}
				
				int x = ( w * row ) / 40;
				int y = ( h * col ) / 40;
				canvas.fillRect(x, y, bw, bh);
			}
		}
	}	

	public void mouseClicked(MouseEvent me) 
	{
	}

	public void mouseEntered(MouseEvent me) 
	{
	}

	public void mouseExited(MouseEvent me) 
	{
	}
	
	int[] dirs = { -1, 0, 0, -1, 1, 0, 0, 1 };
	
	boolean inFrontier( Queue<_Point> frontier, _Point pt )
	{
		for( _Point p : frontier )
		{
			if( p.getCol() == pt.getCol() &&
				p.getRow() == pt.getRow() )
			{
				return( true );
			}
		}
		return( false );
	}
	
	Queue<_Point> getNeighbors( Queue<_Point> frontier, _Point pt )
	{
		Queue<_Point> neighbors = new LinkedList<_Point>();
		
		for( int i=0; i<dirs.length/2; i++ )
		{
			int nr = pt.getRow() + dirs[i*2]; 
			int nc = pt.getCol() + dirs[i*2+1];
			if( nr < 0 || nc < 0 || nr >= 40 || nc >= 40 ) continue;
			if( grid[nr][nc] != 0 ) continue;
			
			_Point newPoint = new _Point( nr, nc );
			
			if( inFrontier( frontier, newPoint ) ) continue;
			
			neighbors.add(newPoint);
		}
		
		return( neighbors );
	}

	public void mousePressed(MouseEvent me) 
	{
		int w = getWidth();
		int h = getHeight();
		
		int row = ( me.getX() * 40 ) / w;
		int col = ( me.getY() * 40 ) / h;
		
		if( row < 0 || row >= 40 && col < 0 || col >= 40 ) return;
		
		int type = grid[row][col];
		
		if( type == 2 || type == 100 ) return;
		
		for( int r=0; r<40; r++ )
		{
			for( int c=0; c<40; c++ )
			{
				if( grid[r][c] == 1 ) grid[r][c] = 0;
			}
		}
		
		// Now do A*
		Queue<_Point> frontier = new LinkedList<_Point>();
		frontier.add( new _Point( 20, 20 ) ); // start point
		_Point[][] came_from = new _Point[40][40];
		
		while( frontier.size() > 0 )
		{
			_Point current = frontier.poll();
			Queue<_Point> neighbors = getNeighbors( frontier, current );
			
			for( _Point next : neighbors )
			{
				if( came_from[next.getRow()][next.getCol()] == null )
				{
					frontier.add( next );
					came_from[next.getRow()][next.getCol()] = current;
				}
			}
		}
		
		_Point current = new _Point( row, col ); // goal
		grid[row][col] = 1;
		
		while( current != null &&
			!( current.getRow() == 20 && 
			  current.getCol() == 20 ) )
		{
			current = came_from[current.getRow()][current.getCol()];
			if( current != null && 
				!( current.getRow() == 20 && 
				  current.getCol() == 20 ) )
			{
				grid[current.getRow()][current.getCol()] = 1;
			}
		}
		
		repaint();
	}

	public void mouseReleased(MouseEvent me) 
	{
	}

}
