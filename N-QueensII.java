/*
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.

Analysis:
	Similar idea of N-Queens problem.
	In the 45 diag lines col - row are always same , and in 135 drag lines col + row are always same
*/

class N-QueensII {
	private Set<Integer> col = new HashSet<Integer>();
	private Set<Integer> diag1 = new HashSet<Integer>();
	private Set<Integer> diag2 = new HashSet<Integer>();

	public int totalNQueens(int n) {
		return dfs(n, 0, 0);
	}

	private int dfs(int n, int row, int count) {
		//now place the Q
		if(row == n) {
			count++;
		}

		for(int i = 0; i < n; i++) {
			if(col.contains(i) || diag1.contains(row+i) || diag2.contains(row-i))
				continue;

			col.add(i);
			diag1.add(row+i);
			diag2.add(row-i);

			count = dfs(n, row+1, count);

			col.remove(i);
			diag1.remove(row+i);
			diag2.remove(row-i);
		}
		return count;
	}
}