/*
Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]

Analysis:
Level by level.

*/

class SpiralMatrix2
{
	public int[][] generateMatrix(int n)
	{
		int val = 1;
		int[][] matrix = new int[n][n];

		for(int level = 0; level < n; level++, n--)
		{
			//top
			for(int i = level; i < n; i++)
			{
				matrix[level][i] = val;
				val++;
			}

			//right
			for(int i = level+1; i < n; i++)
			{
				matrix[i][n-1] = val;
				val++;
			}

			//bottom
			for(int i = n-2; i >= level; i--)
			{
				matrix[n-1][i] = val;
				val++;
			}

			//left
			for(int i = n-2; i > level; i--)
			{
				matrix[i][level] = val;
				val++;
			}
		}
		return matrix;
	}
}