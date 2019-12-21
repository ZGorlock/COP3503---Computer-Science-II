
public class test
{
	int Width, Height, Area, Perimeter;
	
	public test()
	{
	}

	public test( int w, int h )
	{
		Width = w;
		Height = h;
		Area = w * h;
		Perimeter = w * 2 + h * 2;
	}
	
	public int getWidth()
	{
		return Width;
	}

	public void setWidth(int width)
	{
		Width = width;
	}

	public int getHeight()
	{
		return Height;
	}

	public void setHeight(int height)
	{
		Height = height;
	}

	public int getArea()
	{
		return Area;
	}

	public void setArea(int area)
	{
		Area = area;
	}

	public int getPerimeter()
	{
		return Perimeter;
	}

	public void setPerimeter(int perimeter)
	{
		Perimeter = perimeter;
	}
	
	
	
}
