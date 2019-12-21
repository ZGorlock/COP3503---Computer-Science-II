import java.applet.Applet;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


/**
 * 
 */

/**
 * @author Zachary Gill
 *
 */
public class EightQueens extends Applet implements MouseListener, Runnable, ActionListener, AdjustmentListener
{
    private static final long serialVersionUID = 1L;
    
    
    //Constants
    
    static final int NUMROWS = 8;
    static final int NUMCOLS = 8;
    static final int SQUAREWIDTH = 50;
    static final int SQUAREHEIGHT = 50;
    static final int BOARDLEFT = 50;
    static final int BOARDTOP = 50;
    int m_nBoard[][] = new int[NUMROWS][NUMCOLS];
    
    static final int SCREENX = 1020;
    static final int SCREENY = 700;
    
    
    //Component fields
    
    private Image m_imgQueen;
    private MediaTracker tracker = new MediaTracker(this);
    private Image offScreen;
    Button button;
    Scrollbar slider;
    
    
    //Fields
    
    private boolean m_bClash = false;
    private String m_strStatus = "";
    private boolean solving = false;
    private int delay = 100;
    

    //Methods
    
    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run()
    {
        try {
            while (true) {
                delayLoop(delay);
                
                if (solving) {
                    m_strStatus = "Performing backtracking calculations!";

                    
                    if (SolveIt(0))
                        m_strStatus = "Found valid positioning!";
                    else
                        m_strStatus = "Could not find valid positioning!";
                    
                    solving = false;
                    button.setEnabled(true);
                
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
        addMouseListener(this);
        setSize(SCREENX, SCREENY);
        offScreen = new BufferedImage(SCREENX, SCREENY, BufferedImage.TYPE_INT_ARGB);
        this.setVisible(true);
        
        //Button
        button = new Button("Solve");
        button.setLocation(BOARDLEFT * 2 + SQUAREWIDTH * NUMCOLS, BOARDTOP * 2);
        button.setVisible(true);
        button.setEnabled(true);
        button.addActionListener(this);
        button.setSize(75, 50);
        button.setPreferredSize(new Dimension(75, 50));
        this.add(button);
        
        //Slider
        slider = new Scrollbar(Scrollbar.VERTICAL, 100, 50, 0, 300);
        slider.setLocation(BOARDLEFT * 2 + SQUAREWIDTH * NUMCOLS + button.getWidth(), 0);
        slider.setVisible(true);
        slider.setEnabled(true);
        slider.addAdjustmentListener(this);
        slider.setSize(20, 200);
        slider.setPreferredSize(new Dimension(20, 200));
        this.add(slider);
        
        try {
            m_imgQueen = ImageIO.read(EightQueens.class.getResourceAsStream("queen.png"));
            tracker.addImage(m_imgQueen, 1);
            tracker.waitForAll();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        run();
    }
    
    /**
     * Backtracking method to solve the 8 Queens poblem.
     * @param col : The starting column.
     * @return Whether the position is valid or not.
     */
    private boolean SolveIt(int col)
    {
        if (col >= NUMCOLS) return true; //base case
        
        //test each row
        for (int row = 0; row < NUMROWS; row++) {
            m_nBoard[row][col] = 1; //place queen

            repaint();
            delayLoop(delay);
            
            if (!m_bClash) //test for clash
                if (SolveIt(col + 1)) return true; //move to next column
            
            m_nBoard[row][col] = 0; //unplace queen
        }
        
        return false;
    }
    
    /**
     * Paints the board components.
     */
    public void paint (Graphics canvas)
    {
        m_bClash = false;
        DrawSquares(canvas);
        DrawButtons(canvas);
        canvas.setColor(Color.RED);
        CheckColumns(canvas);
        CheckRows(canvas);
        CheckDiagonal1(canvas);
        CheckDiagonal2(canvas);
        canvas.setColor(Color.BLUE);
        
        if (solving) {
            if (m_bClash)
                m_strStatus = "There was a clash!";
            else
                m_strStatus = "Successfully placed queen!";
        }
        
        canvas.drawString(m_strStatus, BOARDLEFT, BOARDTOP + SQUAREHEIGHT * 8 + 20);
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
     * Resets the game board.
     */
    public void clearBoard()
    {
        m_nBoard = new int[NUMROWS][NUMCOLS];
    }

    /**
     * Draws the square grid and places queens at appropriate locations.
     * @param canvas : The screen
     */
    void DrawSquares( Graphics canvas )
    {
        canvas.setColor(Color.BLACK);
        for( int nRow=0; nRow<NUMROWS; nRow++ )
        {
            for( int nCol=0; nCol<NUMCOLS; nCol++ )
            {
                canvas.drawRect( BOARDLEFT + nCol * SQUAREWIDTH,
                    BOARDTOP + nRow * SQUAREHEIGHT, SQUAREWIDTH, SQUAREHEIGHT );
                    
                if( m_nBoard[nRow][nCol] != 0 )
                {
                    canvas.drawImage( m_imgQueen,
                        BOARDLEFT + nCol * SQUAREWIDTH + 8, BOARDTOP + nRow * SQUAREHEIGHT + 6, null );
                }
            }
        }
    }
    
    /**
     * Draws component buttons to canvas.
     * @param canvas : Where to draw.
     */
    void DrawButtons( Graphics canvas )
    {
        button.paint(canvas);
        slider.paint(canvas);
    }
    
    /**
     * Checks for column clashes and sets m_bClash if there is a clash.
     * @param canvas : The screen
     */
    void CheckColumns( Graphics canvas )
    {
        // Check all columns
        for(  int nCol=0; nCol<NUMCOLS; nCol++ )
        {
            int nColCount = 0;
            for( int nRow=0; nRow<NUMROWS; nRow++ )
            {
                if( m_nBoard[nRow][nCol] != 0 )
                {
                    nColCount++;
                }
            }
            if( nColCount > 1 )
            {
                canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                    BOARDTOP + ( SQUAREHEIGHT / 2 ),    
                    BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                    BOARDTOP + SQUAREHEIGHT * 7 + ( SQUAREHEIGHT / 2 ) );
                    
                m_bClash = true;
            }
        }
    }

    /**
     * Checks for row clashes and sets m_bClash if there is a clash.
     * @param canvas : The screen
     */
    void CheckRows( Graphics canvas )
    {
        for(  int nRow=0; nRow<NUMROWS; nRow++ )
        {
            int nRowCount = 0;
            for( int nCol=0; nCol<NUMCOLS; nCol++ )
            {
                if( m_nBoard[nRow][nCol] != 0 )
                {
                    nRowCount++;
                }
            }
            if( nRowCount > 1 )
            {
                canvas.drawLine( BOARDLEFT + ( SQUAREWIDTH / 2 ),
                    BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),  
                    BOARDLEFT + 7 * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                    BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
                    
                m_bClash = true;
            }
        }
    }

    /**
     * Checks for right diagonal clashes and sets m_bClash if there is a clash.
     * @param canvas : The screen
     */
    void CheckDiagonal1( Graphics canvas )
    {
        // Check diagonal 1
        
        for( int nRow=NUMROWS-1; nRow>=0; nRow-- )
        {
            int nCol = 0;
                
            int nThisRow = nRow;
            int nThisCol = nCol;

            int nColCount = 0;
                
            while( nThisCol < NUMCOLS &&
                nThisRow < NUMROWS )
            {
                if( m_nBoard[nThisRow][nThisCol] != 0 )
                {
                    nColCount++;
                }
                nThisCol++;
                nThisRow++;
            }
                
            if( nColCount > 1 )
            {
                canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                        BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),  
                        BOARDLEFT + ( nThisCol - 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                        BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
                    
                m_bClash = true;
            }
        }

        for( int nCol=1; nCol<NUMCOLS; nCol++)
        {
            int nRow = 0;
            
            int nThisRow = nRow;
            int nThisCol = nCol;

            int nColCount = 0;
                
            while( nThisCol < NUMCOLS &&
                nThisRow < NUMROWS )
            {
                if( m_nBoard[nThisRow][nThisCol] != 0 )
                {
                    nColCount++;
                }
                nThisCol++;
                nThisRow++;
            }
                
            if( nColCount > 1 )
            {
                canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                        BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),  
                        BOARDLEFT + ( nThisCol - 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                        BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
                    
                m_bClash = true;
            }
        }
    }

    /**
     * Checks for left diagonal clashes and sets m_bClash if there is a clash.
     * @param canvas : The screen
     */
    void CheckDiagonal2( Graphics canvas )
    {
        // Check diagonal 2
            
        for( int nRow=NUMROWS-1; nRow>=0; nRow-- )
        {
            int nCol = NUMCOLS - 1;
                
            int nThisRow = nRow;
            int nThisCol = nCol;

            int nColCount = 0;
                
            while( nThisCol >= 0 &&
                nThisRow < NUMROWS )
            {
                if( m_nBoard[nThisRow][nThisCol] != 0 )
                {
                    nColCount++;
                }
                nThisCol--;
                nThisRow++;
            }
                
            if( nColCount > 1 )
            {
                canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                        BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),  
                        BOARDLEFT + ( nThisCol + 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                        BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
                    
                m_bClash = true;
            }
        }

        for( int nCol=NUMCOLS-1; nCol>=0; nCol--)
        {
            int nRow = 0;
            
            int nThisRow = nRow;
            int nThisCol = nCol;

            int nColCount = 0;
                
            while( nThisCol >= 0 &&
                nThisRow < NUMROWS )
            {
                if( m_nBoard[nThisRow][nThisCol] != 0 )
                {
                    nColCount++;
                }
                nThisCol--;
                nThisRow++;
            }
                
            if( nColCount > 1 )
            {
                canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                        BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),  
                        BOARDLEFT + ( nThisCol + 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
                        BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
                    
                m_bClash = true;
            }
                
        }
    }

    
    //Imported Methods

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        button.setEnabled(false);
        clearBoard();
        m_bClash = false;
        repaint();
        solving = true; //set SolveIt to run
    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e)
    {
        if (solving)
            return;
        
        int col = (e.getX() - BOARDLEFT) / SQUAREWIDTH;
        int row = (e.getY() - BOARDTOP) / SQUAREHEIGHT;
        
        if (col >= 0 && col < NUMCOLS && row >= 0 && row < NUMROWS) {
            m_nBoard[row][col] ^= 1;
            repaint();
        }
        
    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(MouseEvent e)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e)
    {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * @see java.awt.event.AdjustmentListener#adjustmentValueChanged(java.awt.event.AdjustmentEvent)
     */
    @Override
    public void adjustmentValueChanged(AdjustmentEvent e)
    {
        delay = e.getValue();
    }
}
