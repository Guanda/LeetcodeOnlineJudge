/*
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" 
cells are those horizontally or vertically neighboring. The same letter cell may not be 
used more than once.

For example,
Given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.

Analysis:
	Consider the board as a graph where edges connect adjacent cells. Now the problem becomes 
	finding a path in a graph. Given a starting node, we can use DFS or BFS to find a path. 
	But for this problem, we have to find the starting node first.

	The basic idea is:
		Loop through the board find out every cell which start with word's first character and then
		start dfs to match the word. The way we track the visited cells is mark the visited cell to
		'#'.

	We can also use BFS, but it requires to store the intermediate results, which in this problem 
	is a tuple (x, y). Creating objects for tuples is quite expensive.
*/

class WordSearch {
    /**
     * @param board: A list of lists of character
     * @param word: A string
     * @return: A boolean
     */
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        
        if(word.length() == 0) {
            return true;
        }
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == word.charAt(0)) {
                    boolean result = dfs(board, i, j, word, 0);
                    if(result) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, int i, int j, String word, int start) {
        if(start == word.length()) {
            return true;
        }
        
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length 
        || board[i][j] != word.charAt(start)) {
            return false;
        }
        
        //mark the visited element
        board[i][j] = '#';
        boolean result = dfs(board, i, j+1, word, start + 1) || 
                        dfs(board, i, j-1, word, start + 1) ||
                        dfs(board, i+1, j, word, start + 1) ||
                        dfs(board, i-1, j, word, start + 1);
        board[i][j] = word.charAt(start);
        return result;
    }
}
