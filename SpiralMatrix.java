/*
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].

Analysis:
If more than one row and column left, it can form a circle and we process the circle. Otherwise, if 
only one row or column left, we process that column or row ONLY.

*/

class SpiralMatrix
{
	public List<Integer> spiralMatrix(int[][] matrix)
	{
		List<Integer> result = new ArrayList<Integer>();

		if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
		{
			return result;
		}

		int m = matrix.length;
		int n = matrix[0].length;
		int x = 0;
		int y = 0;

		while(m > 0 && n > 0)
		{
			//if one row/column left, no circle can be formed
			if(m == 1)
			{
				for(int i = 0; i < n; i++)
				{
					result.add(matrix[x][y]);
					y++;
				}
				break;
			}
			else if(n == 1)
			{
				for(int i = 0; i < m; i++)
				{
					result.add(matrix[x][y]);
					x++;
				}
				break;
			}

			//process the circle

			//top, move right
			for(int i = 0; i < n-1; i++)
			{
				result.add(matrix[x][y]);
				y++;
			}

			//right, move down
			for(int i = 0; i < m-1; i++)
			{
				result.add(matrix[x][y]);
				x++;
			}

			//bottom, move left
			for(int i = 0; i < n-1; i++)
			{
				result.add(matrix[x][y]);
				y--;
			}

			//left, move up
			for(int i = 0; i < m-1; i++)
			{
				result.add(matrix[x][y]);
				x--;
			}

			x++;
			y++;
			m = m - 2;
			n = n - 2;
		}
		return result;
	}
}