/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.

Analysis:
	Binary Search.
	First search for the row that may contain the target.
	Second search for the target in that row.

*/

class Search2DMatrix
{
	public boolean searchMatrix(int[][] matrix, int target)
	{
		//binary search to find the row
		int top = 0, bottom = matrix.length - 1;
		while(top < bottom)
		{
			int middle = (top + bottom) / 2;
			if(target == matrix[middle][0])
				return true;
			else if(target < matrix[middle][0])
				bottom = middle - 1;
			//this is the point to specify the row
			else if(target < matrix[middle+1][0])
			{
				top = middle;
				break;
			}
			else
			{
				top = middle + 1;
			}
		}

		//binary search to find the target in that row
		int row = top;
		int left = 0;
		int right = matrix[row].length - 1;

		//remember we need cover the "=" case here
		while(left <= right)
		{
			int middle = (left + right) / 2;
			if(target == matrix[row][middle])
			{
				return true;
			}
			else if(target < matrix[row][middle])
			{
				right = middle - 1;
			}
			else
			{
				left = middle + 1;
			}
		}
		return false;
	}
}