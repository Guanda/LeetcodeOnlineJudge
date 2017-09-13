/*
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to 
be validated.

Analysis:
	The number in each row, col and block should be unique. Go through every position of given board, 
	check if the number has been found in current row, column or block. If so, return false.

	The beauty of the formula given is that all x/y indices within one box yield the same number. 
	Essentially, k is mapping the 9x9 grid into a 3x3 grid of boxes like below:
	0 | 1 | 2
	3 | 4 | 5
	6 | 7 | 8

	Lets look at i=5, j=6 (bottom left index of the "right-center" box):
	k = 5 / 3 * 3 + 6 / 3 = 1*3+2=5

	Now lets take a look at i=6, j=5 (top right index of the "bottom-center" box:
	k= 6 / 3 * 3 + 5 / 3 = 2*3+1=7
*/

class ValidSudoku {
	public boolean validSudoku(char[][] board) {
		if(board == null || board.length != 9 || board[0].length != 9)
			return false;

		//initialize the checker
        List<boolean[]> rowChecker = new ArrayList<>();
        List<boolean[]> colChecker = new ArrayList<>();
        List<boolean[]> blockChecker = new ArrayList<>();

		for(int i = 0; i < 9; i++){
			rowChecker.add(new boolean[9]);
			colChecker.add(new boolean[9]);
			blockChecker.add(new boolean[9]);
		}

		//go through every position
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.') {
                    continue;
                }
                
                int pos = board[i][j] - '1';
                if(rowChecker.get(j)[pos] || colChecker.get(i)[pos] || blockChecker.get(i/3*3 + j/3)[pos]) {
                    return false;
                }
                else {
                    rowChecker.get(j)[pos] = true;
                    colChecker.get(i)[pos] = true;
                    blockChecker.get(i/3*3 + j/3)[pos] = true;
                }
            }
        }
		return true;
	}
}