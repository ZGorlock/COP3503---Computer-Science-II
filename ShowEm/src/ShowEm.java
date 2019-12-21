import java.io.File;
import java.util.Scanner;


public class ShowEm
{

	static int Rows, Cols;
	static int[][] Grid;
	
	static void LoadData() throws Exception
	{
		Scanner in = new Scanner( new File("input.txt"));
		Rows = in.nextInt();
		Cols = in.nextInt();
		
		Grid = new int[Rows][Cols];
		for( int row=0; row<Rows; row++ )
		{
			for( int col=0; col<Cols; col++ )
			{
				Grid[row][col] = in.nextInt();
			}
		}
		
		in.close();
	}

	public static void main(String[] args)
	{
		try
		{
			LoadData();
		}
		catch(Exception ex){}
		
		for( int row=0; row<Rows; row++ )
		{
			for( int col=0; col<Cols; col++ )
			{
				System.out.print(" "+Grid[row][col]);
			}
			System.out.println("");
		}
		
	}

}
