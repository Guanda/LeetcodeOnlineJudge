/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the 
bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Note: m and n will be at most 100.

Analysis:

Method 1: DP solution.
	Very similar with the MinimumPathSum problem. Use 2D sum array to store the count.

Method 2: Maths solution
	Think about it in this way:
	There are in total m+n-2 steps from Start to End and n-1 (or m-1) "turning points" where we move from 
	one column (or row) to the next one. Note that the "turning point" is not necessary to be a turn-left 
	or turn-right point. For instance, moving from (i, j) to (i, j+1) can also be counted as a turning 
	point since it moves to next column.

	Now the question becomes how many ways we can choose n-1 (or m-1) turning points from the total steps, 
	i.e. C(m+n-2, n-1). We only need to choose either row- or column-turning points since once those points 
	are selected, the path has been determined. Obviously, the combination can be computed in O(min(m, n)) 
	time with O(1) space.


*/

class UniquePaths
{
	//Method 1
	public int uniquePaths(int m, int n)
	{
		int[][] counts = new int[m][n];
		counts[0][0] = 1;

		//for first row
		for(int i = 0; i < m; i++)
		{
			counts[i][0] = 1;
		}

		//for first column
		for(int j = 0; j < n; j++)
		{
			counts[0][j] = 1;
		}

		//fill up the rest of the 2D array
		for(int i =1; i < m; i++)
		{
			for(int j = 1; j < n; j++)
			{
				counts[i][j] = counts[i-1][j] + counts[i][j-1];
			}
		}

		return counts[m-1][n-1];
	}


	//Method 2
	public int uniquePaths(int m, int n)
	{
		if(m == 0 || n == 0)
			return 0;

		int x = Math.min(m, n);
		int y = Math.max(m, n);
		double count = 1;
		for(int i = 1; i < x; i ++)
		{
			count = count * (y + i - 1);
			count = count / i;
		}
		return (int)count;
	}
}