/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that 
no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, 
where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]

Analysis:
	Use DFS, remember to remove the element in current loop after dfs fucntion execute.
	And one solution is a list of string.
	
	In the 45 diag lines col - row are always same , and in 135 drag lines col + row are always same
*/

class N-Queens {
	private Set<Integer> col = new HashSet<Integer>();
	private Set<Integer> diag1 = new HashSet<Integer>();
	private Set<Integer> diag2 = new HashSet<Integer>();

	public List<List<String>> solveNQueens(int n) {
		List<List<String>> result = new ArrayList<List<String>>();
		dfs(result, new ArrayList<String>(), 0, n);
		return result;
	}

	public void dfs(List<List<String>> result, List<String> list, int row, int n) {
		if(row == n) {
			//here we have to use new to construct new list, otherwise the list will be updated
			//here we just copy of the list
			result.add(new ArrayList<String>(list));
			return;
		}

		for(int i = 0; i < n; i++) {
			if(col.contains(i) || diag1.contains(row + i) || diag2.contains(row - i)) {
				continue;
			}

			char[] array = new char[n];
			Arrays.fill(array, '.');
			array[i] = 'Q';
			String rowString = new String(array);

			list.add(rowString);
			col.add(i);
			diag1.add(row + i);
			diag2.add(row - i);

			dfs(result, list, row + 1, n);

			list.remove(list.size() - 1);
			col.remove(i);
			diag1.remove(row + i);
			diag2.remove(row - i);
		}
	}
}