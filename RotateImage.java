/*
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?

Analysis:
	By using the relation "matrix[i][j] = matrix[n-1-j][i]", we can loop through the matrix.

*/

class RotateImage
{
	public void rotate(int[][] matrix)
	{
		int n = matrix.length;
		for(int i = 0; i < n/2; i++)
		{
			//be careful how you end the inner loop
			for(int j = i; j < n - 1 - i; j++)
			{
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[n-1-j][i];
				matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
				matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
				matrix[j][n-1-i] = tmp;
			}
		}
	}
}