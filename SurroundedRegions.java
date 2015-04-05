/*
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

Analysis:
	Two ways to solve this problem. (BFS, DFS)
	Start from the boundary to find out all the 'O' and the use DFS or BFS to mark
	all the 'O' which connect with boundary 'O' to 'N'. Then scan whole table mark 
	'O' to 'X' and mark 'N' to 'O'.

*/

class SurroundedRegions
{
	public void surroundedRegions(char[][] board)
	{
		if(board == null || board.length == 0)
			return;

		int row = board.length - 1;
		int col = board[0].length - 1;

		for(int i = 0; i <= row; i++)
		{
			//left boundary
			if(board[i][0] == 'O')
				dfs(board, i, 0);
			//right boundary
			if(board[i][col] == 'O')
				dfs(board, i, col);
		}

		for(int i = 0; i <=col; i++)
		{
			//top boundary
			if(board[0][i] == 'O')
				dfs(board, 0, i);
			//bottom boudary
			if(board[row][i] == 'O')
				dfs(board, row, i);
		}

		//scan all table, change remain 'O' to 'X' and change all 'N' to 'O'
		for(int i = 0; i <= row; i++)
		{
			for(int j = 0; j <= col; j++)
			{
				if(board[i][j] == 'O')
					board[i][j] = 'X';
				else if(board[i][j] == 'N')
					board[i][j] = 'O';

			}
		}
	}

	public void dfs(char[][] board, int x, int y)
	{
		//recursive version of DFS, maybe not passing big data test so we can 
		//use iteration(stack) version, the element in stack is pair<i,j>
		board[x][y] = 'N';
		if(x-1>=0 && board[x-1][y] == 'O')
			dfs(board, x-1, y);
		if(y-1>=0 && board[x][y-1] == 'O')
			dfs(board, x, y-1);
		if(x+1<board.length && board[x+1][y] == 'O')
			dfs(board, x+1, y);
		if(y+1<board[0].length && board[x][y+1] == 'O')
			dfs(board, x, y+1);
	}
}