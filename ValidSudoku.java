/*

Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.

*/

/*
Analysis:
The number in each row, col and block should be unique. Go through every position of given board, check if the 
number has been found in current row, column or block. If so, return false.
*/

class ValidSudoku
{
	public boolean validSudoku(char[][] board)
	{
		if(board == null || board.length != 9 || board[0].length != 9)
			return false;

		//initialize the checker
		ArrayList<boolean[]> rowChecker = new ArrayList<boolean[]>();
		ArrayList<boolean[]> colChecker = new ArrayList<boolean[]>();
		ArrayList<boolean[]> blockChecker = new ArrayList<boolean[]>();

		for(int i = 0; i < 9; i++)
		{
			rowChecker.add(new boolean[9]);
			colChecker.add(new boolean[9]);
			blockChecker.add(new boolean[9]);
		}

		//go through every position
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				if(board[i][j] == '.')
				{
					continue;
				}

				int c = board[i][j] - '1';
				if(rowChecker.get(j)[c] == true || colChecker.get(i)[c] == true || blockChecker.get(i/3*3 + j/3)[c] == true)
				{
					return false;
				}
				else
				{
					rowChecker.get(j)[c] = true;
					colChecker.get(i)[c] = true;
					blockChecker.get(i/3*3 + j/3)[c] = true;
				}
			}
		}
		return true;
	}
}