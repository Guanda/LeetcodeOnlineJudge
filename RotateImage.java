/*
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?

Analysis:
	By using the relation "matrix[i][j] = matrix[n-1-j][i]", we can loop through the matrix.
*/

class RotateImage {
	public void rotate(int[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return;
		
		int n = matrix.length;
		for(int i = 0; i < n/2; i++) {
			//be careful how you end the inner loop
			for(int j = i; j < n - 1 - i; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[n-1-j][i];
				matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
				matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
				matrix[j][n-1-i] = tmp;
			}
		}
	}

	// easy understandable solution:
	/*
	The idea was firstly transpose the matrix and then flip it symmetrically. For instance,

	1  2  3             
	4  5  6
	7  8  9
	after transpose, it will be swap(matrix[i][j], matrix[j][i])

	1  4  7
	2  5  8
	3  6  9
	Then flip the matrix horizontally. (swap(matrix[i][j], matrix[i][matrix.length-1-j])

	7  4  1
	8  5  2
	9  6  3
	*/
	public void rotate(int[][] matrix) {
        for(int i = 0; i<matrix.length; i++){
            for(int j = i; j<matrix[0].length; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i =0 ; i<matrix.length; i++){
            for(int j = 0; j<matrix.length/2; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-1-j] = temp;
            }
        }
    }

}
