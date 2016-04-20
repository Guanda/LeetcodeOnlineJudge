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
Method 1:
	If more than one row and column left, it can form a circle and we process the circle. Otherwise, if 
	only one row or column left, we process that column or row ONLY.

Mathod 2:
	Use level by level.

*/

class SpiralMatrix
{
	//Method 1:
	public List<Integer> spiralMatrix(int[][] matrix)
	{
		List<Integer> result = new ArrayList<Integer>();

		if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
		{
			return result;
		}

		int m = matrix.length;
		int n = matrix[0].length;
		//x, y represent the position of the current element
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

			//process the circle:
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

			//move to the next element, for row is one row under and column is one more left, so both ++
			x++;
			y++;
			
			//for every circle finished, rows and columns are both reduce 2
			m = m - 2;
			n = n - 2;
		}
		return result;
	}


	//Method 2:
	public List<Integer> spiralMatrix(int[][] matrix)
	{
		List<Integer> result = new ArrayList<Integer>();
		if(matrix.length == 0 || matrix[0].length == 0)
			return result;

		for(int level = 0, m = matrix.length, n = matrix[0].length; m>level && n>level; level++, m--, n--)
		{
			int right = n - 1;
			int bottom = m - 1;

			//top row
			for(int i = level; i <= right; i++)
				result.add(matrix[level][i]);
			if(bottom == level)
				return result;

			//right column
			for(int i = level+1; i <= bottom; i++)
				result.add(matrix[i][right]);
			if(right == level)
				return result;

			//bottom row
			for(int i = right-1; i >= level; i--)
				result.add(matrix[bottom][i]);

			//left column
			for(int i = bottom-1; i > level; i--)
				result.add(matrix[i][level]);
		}
		return result;
	}
}
