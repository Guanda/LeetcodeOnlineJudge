/*
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move 
diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:
nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:
nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

Analysis:
	The idea is simple and intuitive:
	1. For each cell, try it's left, right, up and down for smaller number.
	2. If it's smaller, means we are on the right track and we should keep going. If larger, stop and return.
	3. Treat each cell as a start cell. Calculate and memorize the longest distance for this cell, 
	   so we don't need to calculate it again in the future.
*/
class LongestIncreasingPathInMatrix {
	public int longestIncreasingPath(int[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;

		int[][] cache = new int[matrix.length][matrix[0].length];
		int max = 0;
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				max = Math.max(dfs(matrix, i, j, cache, Integer.MIN_VALUE), max);
			}
		}

		return max;
	}

	public int dfs(int[][] matrix, int i, int j, int[][] cache, int previous) {
		//stop cases
		if(i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] <= previous)
			return 0;
		//if calculated before, no need to do it again
		if(cache[i][j] != 0)
			return cache[i][j];

		int curr = matrix[i][j];
		int a = dfs(matrix, i - 1, j, cache, curr) + 1;
		int b = dfs(matrix, i + 1, j, cache, curr) + 1;
		int c = dfs(matrix, i, j - 1, cache, curr) + 1;
		int d = dfs(matrix, i, j + 1, cache, curr) + 1;

		int max = Math.max(a, Math.max(b, Math.max(c, d)));
		cache[i][j] = max;
		return max;
	}
}