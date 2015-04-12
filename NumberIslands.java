/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island 
is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3

Anaylsis:

The algorithm works as follow:

	Scan each cell in the grid.
	If the cell value is '1' explore that island.
	Mark the explored island cells with 'x'.
	Once finished exploring that island, increment islands counter.

*/

class NumberIslands
{
	public int numIslands(char[][] grid)
	{
		int islands = 0;
		if(grid != null && grid.length != 0 && grid[0].length != 0)
		{
			for(int i = 0; i < grid.length; i++)
			{
				for(int j = 0; j < grid[0].length; j++)
				{
					if(grid[i][j] == '1')
					{
						dfs(grid, i, j);
						islands++;
					}
				}
			}
		}
		return islands;
	}

	public void dfs(char[][] grid, int i, int j)
	{
		if(i < 0 || grid.length <= i || j < 0 || grid[0].length <= j || grid[i][j] != '1')
		{
			return;
		}

		grid[i][j] = 'X';
		dfs(grid, i+1, j);
		dfs(grid, i-1, j);
		dfs(grid, i, j+1);
		dfs(grid, i, j-1);
	}
}