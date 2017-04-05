/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom 
right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Analysis
	This is a classic DP problem.

	Suppose we have a m by n table for the sum results.
	sum[i][j] is the minimum sum of all numbers from grid[0][0] to grid[i][j], inclusively.
	Then the DP formular becomes
	sum[i][j] = grid[i][j] + min(sum[i-1][j], sum[i][j-1])
*/

class MinimumPathSum {
	public int minPathSum(int[][] grid) {
		int row = grid.length;
		int column = grid[0].length;
		int[][] sum = new int[row][column];

		if(row == 0 || column == 0)
			return 0;
		sum[0][0] = grid[0][0];

		for(int i = 1; i < row; i++)
			sum[i][0] = grid[i][0] + sum[i-1][0];

		for(int j = 1; j < column; j++)
			sum[0][j] = grid[0][j] + sum[0][j-1];

		for(int i = 1; i < row; i++) {
			for(int j = 1; j < column; j++) {
				sum[i][j] = grid[i][j] + Math.min(sum[i-1][j], sum[i][j-1]);
			}
		}
		return sum[row-1][column-1];
	}
}