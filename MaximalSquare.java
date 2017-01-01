/*
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.

Analysis:
	Use DP
	b[i][j] represent the maximal size of the square that can be achieved at point (i, j)

*/
class MaximalSquare {
	public int maximalSquare(char[][] matrix) {
		if(matrix.length == 0)
			return 0;

		int m = matrix.length;
		int n = matrix[0].length;
		int[][] b = new int[m+1][n+1];

		int maxEdge = 0;
		for(int i = 1; i <= m; i++) {
			for(int j = 1; j <= n; j++) {
				if(matrix[i-1][j-1] == '1') {
					//the (i, j) position largest square depend on the left, top, topleft of (i, j)
					b[i][j] = Math.min(Math.min(b[i-1][j], b[i-1][j-1]), b[i][j-1]) + 1;
					maxEdge = Math.max(b[i][j], maxEdge);
				}
			}
		}

		return maxEdge * maxEdge;
	}
}