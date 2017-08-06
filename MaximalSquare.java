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
	edge[i][j] represent the edge length of the largest square ENDING at position (i, j)
*/
class MaximalSquare {
    public int maxSquare(int[][] matrix) {
        // write your code here
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] edge = new int[row+1][col+1];
        
        int maxEdge = 0;
        for(int i = 1; i <= row; i++) {
            for(int j = 1; j <= col; j++) {
                if(matrix[i-1][j-1] > 0) {
                    edge[i][j] = Math.min(edge[i-1][j], Math.min(edge[i-1][j-1], edge[i][j-1])) + 1;
                }
                else {
                    edge[i][j] = 0;
                }
                maxEdge = Math.max(edge[i][j], maxEdge);
            }
        }
        
        return maxEdge * maxEdge;
    }


	// Optimize dp solution 1, to save space
	public int maxSquare(int[][] matrix) {
        // write your code here
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] edge = new int[2][col+1];
        
        int maxEdge = 0;
        for(int i = 1; i <= row; i++) {
            for(int j = 1; j <= col; j++) {
                if(matrix[i-1][j-1] > 0) {
                    edge[i%2][j] = Math.min(edge[(i-1)%2][j], Math.min(edge[(i-1)%2][j-1], edge[i%2][j-1])) + 1;
                }
                else {
                    edge[i%2][j] = 0;
                }

                maxEdge = Math.max(edge[i%2][j], maxEdge);
            }
        }
        
        return maxEdge * maxEdge;
    }
}