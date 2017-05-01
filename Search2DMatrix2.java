/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.
Given target = 20, return false.

*/

class Search2DMatrix2 {
	//method 1: normal. Time complexity: O(m+n)
	//从右上角或者左下角出发进行比较，每次比较之后能够删除一行或者一列或者both
	public boolean searchMatrix(int[][] matrix, int target) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return false;

		int m = matrix.length;
		int n = matrix[0].length;

		if(target < matrix[0][0] || target > matrix[m-1][n-1])
			return false;

		//从右上角出发
		int row = 0;
		int col = n - 1;

		while(row <= m-1 && col >= 0) {
			if(target > matrix[row][col])
				row++;
			else if(target < matrix[row][col])
				col--;
			else
				return true;
		}
		return false;
	}

	//method 2: Divide-Conquer, divide matrix to 4 smaller matrix based on mid element
	public boolean searchMatrixBetter(int[][] matrix, int target) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return false;

		int m = matrix.length;
		int n = matrix[0].length;

		return helper(matrix, 0, m-1, 0, n-1, target);
	}

	public boolean helper(int[][] matrix, int rowStart, int rowEnd, int colStart, int colEnd, int target) {
		if(rowStart > rowEnd || colStart > colEnd)
			return false;

		int rowMid = rowStart + (rowEnd - rowStart) / 2;
		int colMid = colStart + (colEnd - colStart) / 2;

		if(matrix[rowMid][colMid] == target)
			return true;
		//mid element larger than target, abandon bottom right part
		else if(matrix[rowMid][colMid] > target) {
			return helper(matrix, rowStart, rowMid-1, colStart, colMid-1, target) ||
				   helper(matrix, rowStart, rowMid-1, colMid, colEnd, target) ||
				   helper(matrix, rowMid, rowEnd, colStart, colMid-1, target);	
		}
		//mid element smaller than target, abandon top left part
		else {
			return helper(matrix, rowStart, rowMid, colMid+1, colEnd, target) ||
				   helper(matrix, rowMid+1, rowEnd, colStart, colMid, target) ||
				   helper(matrix, rowMid+1, rowEnd, colMid, colEnd, target);
		}
	}
}