/**
 * Zachary Gill
 * COP 3503
 * section number 0001
 * Connect 4
 * 28 April 2016
 */

import java.util.HashMap;


public class MiniMax 
{
    //provides memoization for the minimax algorithm, by remembering certain board states
    private static HashMap<String, Integer> memo = new HashMap<String, Integer>();
    
	int m_nMaxPly = 7;
	
	//  This is the piece to search for. Should be either RED or YELLOW.
	int m_nSearchPiece = 0;
	
	// Set the piece for which a move will be found.
	public void SetSearchPiece( int nPiece )
	{
		m_nSearchPiece = nPiece;
	}

	// Compile a list of legal moves.
	int GetLegalMoves( int[][] nBoardData, int[] nMoveList )
	{
		int row, col;
		int nNumMoves = 0;

		// Loop through six rows.
		for( row=0; row<=5; row++ )
		{
			// Loop through seven columns.
			for( col=0; col<=6; col++ )
			{
				// If the board at this location is empty,
				//   then this square is a legal move.
				if( nBoardData[row][col] == Connect4.EMPTY &&
					( row == 5 || nBoardData[row+1][col] != Connect4.EMPTY ) )
				{
					// Put the row and column in the move list array.
					nMoveList[nNumMoves*2] = row;
					nMoveList[nNumMoves*2+1] = col;
					// Increment the index pointer.
					nNumMoves++;
				}
			}
		}

		// Return the number of legal moves that were found.
		return( nNumMoves );
	}

	//Performs the recursive search for the best move
    int DoSearch( Position pos, int nPiece, int nDepth, Board pBoard, int nAlpha, int nBeta )
    {        
        //Check if board has been calculated before
        String boardString = pBoard.toString();
        if (memo.containsKey(boardString)) {
            return memo.get(boardString);
        }
        
        // Local arrays for the legal move list and
        //   the result list.
        int[] nMoveList = new int[7*2];
        
        // Create min and max variables.
        int nMin = Integer.MAX_VALUE, nMax = Integer.MIN_VALUE;

        int baseCaseRet = -1;
        
        // First, see if a side has won.
        if( pBoard.DidSideWin( nPiece ) )
        {
            if( nPiece == m_nSearchPiece )
            {
                baseCaseRet = Integer.MAX_VALUE;
            }
            baseCaseRet = Integer.MIN_VALUE;
        }
        
        // See if we have a Cats game.
        else if( pBoard.IsCatsGame() )
        {
            // Score for Cats game is 0.
            baseCaseRet = 0;
        }
        
        // If we are at a leaf node, return the score.
        else if( nDepth >= m_nMaxPly )
        {
            baseCaseRet = ScoreIt( m_nSearchPiece, pBoard.GetBoardData() );
        }
        
        //if we hit a base case, memoize and return.
        if (baseCaseRet != -1) {
            memo.put(boardString, baseCaseRet);
            return baseCaseRet;
        }

        // Get the legal moves.
        int nMoves = GetLegalMoves( pBoard.GetBoardData(), nMoveList );

        // Safety so that we always have a move at depth 0
        if( nDepth == 0 )
        {
            pos.Row = nMoveList[0];
            pos.Col = nMoveList[1]; 
        }        
        
        // Loop through the legal moves.
        for( int i=0; i<nMoves; i++ )
        {
            // We need a board clone so that we can place pieces
            //   without messing up previous board positions.
            Board SaveMe = pBoard.Clone();
            
            // Place the piece from the current move in the list.
            SaveMe.PlacePiece( nMoveList[i*2], nMoveList[i*2+1], nPiece );
            
            //save a copy of alpha for later
            int saveAlpha = nAlpha;
            
            //clear memo before each move
            if (nDepth == 0)
                memo.clear();
            
            // Call DoSearch() recursively.
            int res = DoSearch( pos, nPiece ^ 1, nDepth + 1, SaveMe, nAlpha, nBeta );
            
            //save min and max values for this branch
            nMin = Math.min(nMin, res);
            nMax = Math.max(nMax, res);
            
            //if this call is a maximizer
            if (nPiece == m_nSearchPiece) {

                //update the alpha value
                nAlpha = Math.max(nAlpha, res);
                
                //if this position is better, record it
                if (nDepth == 0 && nAlpha > saveAlpha) {
                    pos.Row = nMoveList[i * 2];
                    pos.Col = nMoveList[i * 2 + 1];
                }
                
                //if we can prune, return early
                if (res >= nBeta) {
                    return res;
                }
            }
            
            //if this call is a minimizer
            else {
                
                //update the beta value
                nBeta = Math.min(nBeta, res);
                
                //if we can prune, return early
                if (res <= nAlpha) {
                    return res;
                }
            }
        }

        // If this is max of minimax, then return the max.
        if( nPiece == m_nSearchPiece )
        {
            return( nAlpha );
        }
        
        // If this is min of minimax, then return the min.
        else
        {
            return( nBeta );
        }
        
    }

	// Wrapper method that kicks off minimax to get a move.
	public void GetMove( Position pos, int[][] BoardData, int nPiece )
	{
		// Set the search piece.
		SetSearchPiece( nPiece );
		
		// Create a new board with this board data.
		Board brd = new Board();
		brd.SetBoardData( BoardData );

		// Call the recursive method.
		DoSearch( pos, nPiece, 0, brd, Integer.MIN_VALUE, Integer.MAX_VALUE ); //start alpha as min and beta as max
	}
	
	int ScoreIt( int nPiece, int[][] BoardData )
	{
		int Twos = 0;
		int Threes = 0;
		int Fours =0 ;
		
		for( int nRow=0; nRow<6; nRow++ )
		{
			int nCount = 0;
			for( int nCol=0; nCol<7; nCol++ )
			{
				if( BoardData[nRow][nCol] == nPiece )
				{
					nCount++;
				}
				else
				{
					if( nCount == 2 )
					{
						Twos++;
					}
					else if( nCount == 3 )
					{
						Threes++;
					}
					else if( nCount == 4 )
					{
						Fours++;
					}
					nCount = 0;
				}
			}
			if( nCount == 2 )
			{
				Twos++;
			}
			else if( nCount == 3 )
			{
				Threes++;
			}
			else if( nCount == 4 )
			{
				Fours++;
			}
		}
		
		for( int nCol=0; nCol<7; nCol++ )
		{
			int nCount = 0;
			for( int nRow=0; nRow<6; nRow++ )
			{
				if( BoardData[nRow][nCol] == nPiece )
				{
					nCount++;
				}
				else
				{
					if( nCount == 2 )
					{
						Twos++;
					}
					else if( nCount == 3 )
					{
						Threes++;
					}
					else if( nCount == 4 )
					{
						Fours++;
					}
					nCount = 0;
				}
			}
			if( nCount == 2 )
			{
				Twos++;
			}
			else if( nCount == 3 )
			{
				Threes++;
			}
			else if( nCount == 4 )
			{
				Fours++;
			}
		}
		
		// Loop through the diagonal data.
		for( int nDiagonalTest=0; nDiagonalTest<Board.m_nDiagonalData.length/4; nDiagonalTest++)
		{
			int nCount = 0;
			// Starting row.
			int nRow = Board.m_nDiagonalData[nDiagonalTest*4];
			// Starting column.
			int nCol = Board.m_nDiagonalData[nDiagonalTest*4+1];
			// YDirection for the iterations.
			int nYDir = Board.m_nDiagonalData[nDiagonalTest*4+2];
			// Number of iterations.
			int nIterations = Board.m_nDiagonalData[nDiagonalTest*4+3];

			// Loop through the iterations.
			for( int i=0; i<nIterations; i++ )
			{
				// If this is nSide then increment the counter.
				if( BoardData[nRow][nCol] == nPiece )
				{
					// Increment the counter.
					nCount++;
				}
				
				// This square does not equal nSide.
				else
				{
					if( nCount == 2 )
					{
						Twos++;
					}
					else if( nCount == 3 )
					{
						Threes++;
					}
					else if( nCount == 4 )
					{
						Fours++;
					}
					
					// Reset the counter.
					nCount = 0;
				}
				
				// Move the row position.
				nRow += nYDir;
				// Move the column position.
				nCol++;
			}
			
			if( nCount == 2 )
			{
				Twos++;
			}
			else if( nCount == 3 )
			{
				Threes++;
			}
			else if( nCount == 4 )
			{
				Fours++;
			}
			
		}

		int nPositionalAdvantage = 0;
		for( int nCol=0; nCol<7; nCol++ )
		{
			int nCount = 0;
			for( int nRow=0; nRow<6; nRow++ )
			{
				if( BoardData[nRow][nCol] == nPiece )
				{
					nCount++;
				}
			}
			
			if( nCol == 2 || nCol == 3 )
			{
				nPositionalAdvantage += nCount * 2;
			}
			else if( nCol == 1 || nCol == 4 )
			{
				nPositionalAdvantage += nCount;
			}
			
		}
		for( int nCol=0; nCol<7; nCol++ )
		{
			int nCount = 0;
			for( int nRow=0; nRow<6; nRow++ )
			{
				if( BoardData[nRow][nCol] != nPiece )
				{
					nCount++;
				}
			}
			
			if( nCol == 2 || nCol == 3 )
			{
				//nPositionalAdvantage -= nCount * 2;
			}
			else if( nCol == 1 || nCol == 4 )
			{
				//nPositionalAdvantage -= nCount;
			}
			
		}
		
		return( nPositionalAdvantage + Twos + Threes * 2 + Fours * 4 );
	}

	static String ArrayToString( int[] IntArray )
	{
		String strRet = "{ ";
		for( int i=0; i<IntArray.length; i++ )
		{
			strRet += (""+IntArray[i] );
			if( i < IntArray.length - 1 )
			{
				strRet += ", ";
			}
		}
		return( strRet + " }");
	}
	
}

