/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" 
cells are those horizontally or vertically neighboring. The same letter cell may not be 
used more than once.

For example,
Given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.

Analysis:
Consider the board as a graph where edges connect adjacent cells. Now the problem becomes 
finding a path in a graph. Given a starting node, we can use DFS or BFS to find a path. 
But for this problem, we have to find the starting node first.

The basic idea is:

	Loop through every cell in the board and perform DFS to match the word. Since a same cell 
	cannot be reused, we also need to keep track of visited cells during DFS and unmark it if 
	it is not being used.

We can also use BFS, but it requires to store the intermediate results, which in this problem 
is a tuple (x, y). Creating objects for tuples is quite expensive.

*/

class WordSearch
{
	public boolean exist(char[][] board, String word)
	{
		boolean[][] visited = new boolean[board.length][board[0].length];

		//find the starting char
		for(int i = 0; i < board.length; i++)
		{
			for(int j = 0; j < board[0].length; j++)
			{
				if(DFS(board, i, j, word, 0, visited))
					return true;
			}
		}
		return false;
	}

	public boolean DFS(char[][] board, int x, int y, String word, int curr, boolean[][] visited)
	{
		//validate input
		if(visited[x][y] || board[x][y] != word.charAt(curr))
			return false;

		//stop case
		if(curr == word.length() - 1)
			return true;

		//mark the node as visited
		visited[x][y] = true;

		//BFS on its neighbers
		if(x > 0 && DFS(board, x-1, y, word, curr+1, visited))
			return true;
		if(x+1 < board.length && DFS(board, x+1, y, word, curr+1, visited))
			return true;
		if(y > 0 && DFS(board, x, y-1, word, curr+1, visited))
			return true;
		if(y+1 < board[0].length && DFS(board, x, y+1, word, curr+1, visited))
			return true;

		//mark the node unused
		visited[x][y] = false;
		return false;
	}
}