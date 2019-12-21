import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.imageio.ImageIO;


public class Sudoku extends Applet implements Runnable
{
    private static final long serialVersionUID = 1L;

    private int delay = 10; //delay in milliseconds
    
    final static int N = 9;
    static final int SQUAREWIDTH = 20;
    static final int SQUAREHEIGHT = 20;
    static final int BOARDLEFT = 50;
    static final int BOARDTOP = 50;
	static int Grid[][] = new int[N][N];

    static final int SCREENX = 300;
    static final int SCREENY = 300;

    private Image offScreen;
    
    private boolean m_bClash = false;
    private String m_strStatus = "";
    private boolean solving = true;
    
	
    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run()
    {
        try {
            while (true) {
                
                if (solving) {
                    m_strStatus = "Performing backtracking calculations!";
                    repaint();
                    delayLoop(delay);
        
                    if (SolveSudoku( Grid ))
                          m_strStatus = "Successfully solved grid!";
                    else
                        m_strStatus = "Could not solve grid!";
                    
                    solving = false;
                    
                    repaint();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init()
    {
        try {
            LoadData();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        setSize(SCREENX, SCREENY);
        offScreen = new BufferedImage(SCREENX, SCREENY, BufferedImage.TYPE_INT_ARGB);
        this.setVisible(true);
        
        run();
    }
    
	
	
	static void LoadData() throws FileNotFoundException
	{
		// Open the file for reading. Will almost always be input.txt
		Scanner objScanner = new Scanner(new File("input.txt"));
		
		// Iterate through the rows.
		for( int nRow=0; nRow<N; nRow++ )
		{
			// Iterate through the columns.
			for( int nColumn=0; nColumn<N; nColumn++ )
			{
				// Read each value. Note that this is [row][col] but could be [col][row] in
				//   other contexts.
				Grid[nRow][nColumn] = objScanner.nextInt();
			}
		}
		// Closing is good practice and avoids an eclipse warning.
		objScanner.close();
	}
	
	/* Searches the grid to find an entry that is still unassigned. If
	   found, the reference parameters row, col will be set the location
	   that is unassigned, and true is returned. If no unassigned entries
	   remain, false is returned. */
	static int FindUnassignedLocation( int grid[][] )
	{
		  for( int row=0; row<N; row++ )
		  {
		    for( int col=0; col<N; col++ )
		    {
		      if( grid[row][col] == 0 )
		      {
		        return( col | ( row << 8 ) );
		      }
		    }
		  }

		return( -1 );
	}
	
	/* Returns a boolean which indicates whether any assigned entry
	   in the specified row matches the given number. */
	static boolean UsedInRow(int grid[][], int row, int num)
	{
		for( int col=0; col<9; col++ )
		{
			if( grid[row][col] == num ) return true;
		}
		
		return false;
	}
	 
	/* Returns a boolean which indicates whether any assigned entry
	   in the specified column matches the given number. */
	static boolean UsedInCol(int grid[][], int col, int num)
	{
		for( int row=0; row<9; row++ )
		{
			if( grid[row][col] == num ) return true;
		}

		return false;
	}
	 
	/* Returns a boolean which indicates whether any assigned entry
	   within the specified 3x3 box matches the given number. */
	static boolean UsedInBox( int grid[][], int boxStartRow, int boxStartCol, int num )
	{
		for( int row=boxStartRow; row<boxStartRow+3; row++ )
		{
			for( int col=boxStartCol; col<boxStartCol+3; col++ )
			{
				if( grid[row][col] == num ) return true;
			}
		}
		
		return false;
	}
	 
	/* Returns a boolean which indicates whether it will be legal to assign
	   num to the given row,col location. */
	static boolean IsPromising( int grid[][], int row, int col, int num )
	{
		if( !UsedInRow( grid, row, num ) &&
			!UsedInCol( grid, col, num ) &&
			!UsedInBox( grid, row-(row%3), col-(col%3), num ) )
		{
			return( true );
		}
		
		return false;
	}
	
	/* Takes a partially filled-in grid and attempts to assign values to
	  all unassigned locations in such a way to meet the requirements
	  for Sudoku solution (non-duplication across rows, columns, and boxes) */
	boolean SolveSudoku(int grid[][])
	{
		int result = FindUnassignedLocation(grid);
		if( result == -1 ) return true;
		int row = result >> 8;
		int col = result & 0xff;
		
		for( int num=1; num<=9; num++ )
		{
			if( IsPromising( grid, row, col, num ) )
			{
				grid[row][col] = num;
				
				m_strStatus = "Placed " + num + " at " + row + ", " + col; 
				repaint();
				delayLoop(delay);
				
				if( SolveSudoku( grid ) )
				{
					return true;
				}
				grid[row][col] = 0;
				
                m_strStatus = "Clash, removing " + num + " from " + row + ", " + col;
                repaint();
                delayLoop(delay);
                
			}
		}

		return false; // this triggers backtracking
	}
	
	/**
	 * Paints board components
	 * @param canvas
	 */
	public void paint(Graphics canvas)
	{
	    m_bClash = false;
	    DrawBoard(canvas);
        
	    canvas.setColor(Color.blue);
        canvas.drawString(m_strStatus, BOARDLEFT, BOARDTOP + SQUAREHEIGHT * N + 20);
	}

    /**
     * Repaints the screen.
     */
    public void repaint()
    {
        Graphics graphics = offScreen.getGraphics(); //get double buffer
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, SCREENX, SCREENY);
        paint(graphics);
        getGraphics().drawImage(offScreen, 0, 0, null); //draw actual image to screen
    }
    
    /**
     * Delays the loop for a specified time.
     * @param milli : The number of milliseconds to delay.
     */
    public void delayLoop(int milli)
    {
        try {
            Thread.sleep(milli); //delay
        }
        catch (InterruptedException e) {
            System.exit(0);
        }
    }
	
    /**
     * Draws the board
     * @param canvas
     */
	void DrawBoard(Graphics canvas)
	{
	    canvas.setColor(Color.black);
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	            canvas.drawRect(BOARDLEFT + j * SQUAREWIDTH,
	                    BOARDTOP + i * SQUAREHEIGHT, SQUAREWIDTH, SQUAREHEIGHT);
	            
	            if (Grid[i][j] != 0) {
	                canvas.drawString(Integer.toString(Grid[i][j]), BOARDLEFT + j * SQUAREWIDTH + 6, BOARDTOP + (i + 1) * SQUAREHEIGHT - 4);
	            }
	        }
	    }
	}
	
	/* A utility function to print grid  */
	static void printGrid( int grid[][] )
	{
	    for (int row = 0; row < N; row++)
	    {
	       for (int col = 0; col < N; col++)
	             System.out.print(""+grid[row][col]+" ");
	        System.out.println("");;
	    }
	}	
}