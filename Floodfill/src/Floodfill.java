import java.applet.Applet;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Floodfill extends Applet implements MouseListener
{
	Color m_objSelectedColor = Color.blue;
	int m_nSelectedColor = 0xff0000ff;
	int nColorIndex = 0;
	BufferedImage m_objShape;
	MediaTracker tracker = new MediaTracker(this);
	
	int m_nTestShapeX = 100;
	int m_nTestShapeY = 100;
	
	static Color[] m_Colors =
	{
		Color.blue, Color.red, Color.green, Color.yellow,
		Color.gray, Color.magenta, Color.orange, Color.cyan
	};
	
	int m_nUpperLeftX = 10;
	int m_nUpperLeftY = 10;
	int m_nColorWidth = 50;
	int m_nColorHeight = 50;
	int m_nLowerRightX;
	int m_nLowerRightY;
	
    CheckboxGroup lngGrp = new CheckboxGroup();
    Checkbox full = new Checkbox("Full Recursion", lngGrp, true);
    Checkbox partial = new Checkbox("Partial Recursion", lngGrp, true);
    
	public void init()
	{
		addMouseListener(this);
        setSize(1020,700);

        add(partial);
        add(full);
        
        try 
        {
			m_objShape = ImageIO.read(Floodfill.class.getResourceAsStream("Untitled.png"));
			tracker.addImage(m_objShape, 100);
			tracker.waitForAll();
		} 
        catch (Exception e1) 
        {
		}
		
	}

	void DrawColors( Graphics canvas )
	{
		for( int i=0; i<m_Colors.length; i++ )
		{
			canvas.setColor( m_Colors[i] );
			canvas.fillRect(m_nUpperLeftX, m_nUpperLeftY + i * m_nColorHeight, m_nColorWidth, m_nColorHeight );
			if (nColorIndex == i)
			    canvas.setColor( Color.black );
			else
			    canvas.setColor( Color.white );
			canvas.drawRect(m_nUpperLeftX, m_nUpperLeftY + i * m_nColorHeight, m_nColorWidth, m_nColorHeight );
			
			m_nLowerRightX = m_nUpperLeftX + m_nColorWidth;
			m_nLowerRightY = ( i + 1 ) * m_nColorHeight;
		}
		
	}
	
	void DrawTestShape( Graphics canvas )
	{
		canvas.drawImage(m_objShape, m_nTestShapeX, m_nTestShapeY, null);
	}
	
	void SetPixel( int x, int y, Graphics canvas )
	{
		canvas.drawLine(x, y, x, y);
	}
	
	void SetPixel( int x, int y, int nColor )
	{
		m_objShape.setRGB(x, y, nColor);
	}
	
	public int GetPixel( int x, int y )
	{
		return( m_objShape.getRGB(x, y) );
	}
	
	public void paint( Graphics canvas )
	{
		DrawColors( canvas );
		DrawTestShape( canvas );
	}
	
	void DoRecursiveFill( int x, int y )
	{
		x -= m_nTestShapeX;
		y -= m_nTestShapeY;
		m_nStartColor = GetPixel(x,y) | 0xff000000;
		Graphics canvas = getGraphics();
		canvas.setColor( m_objSelectedColor );
		
		int w = m_objShape.getWidth();
		int h = m_objShape.getHeight();

		if( m_nStartColor == m_nSelectedColor)
		{
			return;
		}
		
		RecursiveFill( x, y, w, h, canvas);
	}
	
	void RecursiveFill( int x, int y, int w, int h, Graphics canvas )
	{
	    if (x < 0 || x >= w || y < 0 || y >= h) //pixel out of bounds
	        return;
	    
	    if (GetPixel(x, y) != m_nStartColor) //hit picture border
	        return;
	    
	    SetPixel(x + m_nTestShapeX, y + m_nTestShapeY, canvas); //set pixel on canvas
	    SetPixel(x, y, m_nSelectedColor); //set pixel on actual picture memory
	    
	    //move around recursively
	    RecursiveFill(x - 1, y, w, h, canvas);
        RecursiveFill(x + 1, y, w, h, canvas);
        RecursiveFill(x, y - 1, w, h, canvas);
        RecursiveFill(x, y + 1, w, h, canvas);
	}
	
	int m_nStartX, m_nStartY, m_nStartColor;
	void DoFloodFill( int x, int y )
	{
		x -= m_nTestShapeX;
		y -= m_nTestShapeY;
		m_nStartColor = GetPixel(x,y) | 0xff000000;
		Graphics canvas = getGraphics();
		canvas.setColor( m_objSelectedColor );
		
		int w = m_objShape.getWidth();
		int h = m_objShape.getHeight();

		if( m_nStartColor == m_nSelectedColor)
		{
			return;
		}
		
		FloodFill( x, y, w, h, canvas);
	}
	
	void FloodFill( int x, int y, int w, int h, Graphics canvas )
	{
        if (x < 0 || x >= w || y < 0 || y >= h) //pixel out of bounds
            return;
        
	    if (GetPixel(x, y) != m_nStartColor) //hit picture border
	        return;
	    
	    int left = x;
	    int right = x;
	    int xx = x;
	    
	    //move right
	    while (xx < m_objShape.getWidth() && GetPixel(xx, y) == m_nStartColor) {
	        SetPixel(xx + m_nTestShapeX, y + m_nTestShapeY, canvas); //set pixel on canvas
	        SetPixel(xx, y, m_nSelectedColor); //set pixel on actual picture memory
	        right = xx;
	        xx++;
	    }
	    
	    xx= x - 1; //reset xx to move left
	    
	    //move left
        while (xx >= 0 && GetPixel(xx, y) == m_nStartColor) {
             SetPixel(xx + m_nTestShapeX, y + m_nTestShapeY, canvas); //set pixel on canvas
             SetPixel(xx, y, m_nSelectedColor);//set pixel on actual picture memory
             left = xx;
             xx--;
         }
       
        //move up and down along the path we travelled before (partial recursion part)
        for (xx = left; xx <= right; xx++) {
            FloodFill(xx, y - 1, w, h, canvas);
            FloodFill(xx, y + 1, w, h, canvas);
        }
	}

	@Override
	public void mouseClicked(MouseEvent ms) 
	{
	}

	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
	}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{
	}

	@Override
	public void mousePressed(MouseEvent ms) 
	{
		if( ms.getX() >= m_nUpperLeftX &&
				ms.getY() >= m_nUpperLeftY &&
				ms.getX() < m_nLowerRightX &&
				ms.getY() < m_nLowerRightY )
			{
				nColorIndex = ( ms.getY() - m_nUpperLeftY ) / m_nColorHeight;
				if( nColorIndex >= 0 && nColorIndex <= 7 )
				{
					m_objSelectedColor = m_Colors[nColorIndex];
					m_nSelectedColor = m_Colors[nColorIndex].getRGB();
				}
				DrawColors(getGraphics());
			}
			
			else if( ms.getX() >= m_nTestShapeX &&
				ms.getY()>=m_nTestShapeY &&
				ms.getX() < m_nTestShapeX + m_objShape.getWidth() &&
				ms.getY() < m_nTestShapeY + m_objShape.getHeight())
			{
				if( full.getState() )
				{
					DoRecursiveFill( ms.getX(), ms.getY());
				}
				else
				{
					DoFloodFill( ms.getX(), ms.getY());
				}
			}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
	}
	
}
