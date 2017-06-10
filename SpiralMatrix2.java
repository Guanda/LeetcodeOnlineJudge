/*
Given an integer n, generate a square matrix filled with elements from 1 to n^2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]

Analysis:
Method 1:
	Four boundary

Method 2:
	Level by level.
*/

class SpiralMatrix2 {
	//Method 1
	public int[][] generateMatrix(int n) {
		int val = 1;
		int[][] matrix = new int[n][n];
		int top = 0, bottom = n-1, left = 0, right = n-1;

		while(left < right && top < bottom) {
			//top
			for(int i = left; i < right; i++) {
				matrix[top][i] = val;
				val++;
			}

			//right
			for(int i = top; i < bottom; i++) {
				matrix[i][right] = val;
				val++;
			}

			//bottom
			for(int i = right; i > left; i--) {
				matrix[bottom][i] = val;
				val++;
			}

			//left
			for(int i = bottom; i > top; i--) {
				matrix[i][left] = val;
				val++;
			}
			left++;
			right--;
			top++;
			bottom--;
		}

		//for n is odd, we need one more element
		if(n % 2 != 0)
			matrix[n/2][n/2] = val;
		
		return matrix;
	}


	//Method 2
	public int[][] generateMatrix(int n) {
		int val = 1;
		int[][] matrix = new int[n][n];

		for(int level = 0; level < n; level++, n--) {
			//top
			for(int i = level; i < n; i++) {
				matrix[level][i] = val;
				val++;
			}

			//right
			for(int i = level+1; i < n; i++) {
				matrix[i][n-1] = val;
				val++;
			}

			//bottom
			for(int i = n-2; i >= level; i--) {
				matrix[n-1][i] = val;
				val++;
			}

			//left
			for(int i = n-2; i > level; i--) {
				matrix[i][level] = val;
				val++;
			}	
		}
		return matrix;
	}
}
