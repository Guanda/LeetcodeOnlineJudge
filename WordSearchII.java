/*
 Given a 2D board and a list of words from the dictionary, find all words in the board.

 Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" 
 cells are those horizontally or vertically neighboring. The same letter cell may not be 
 used more than once in a word.

 For example,
 Given words = ["oath","pea","eat","rain"] and board =

 [
 ['o','a','a','n'],
 ['e','t','a','e'],
 ['i','h','k','r'],
 ['i','f','l','v']
 ]
 Return ["eat","oath"].
 Note:
 You may assume that all inputs are consist of lowercase letters a-z.
 */

public class WordSearchII {
    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    public ArrayList<String> wordSearchII(char[][] board, ArrayList<String> words) {
        // write your code here
        ArrayList<String> result = new ArrayList<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        Trie tree = new Trie();
        for(String word : words) {
            tree.insert(word);
        }
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                String curr = "";
                dfs(result, visited, board, i, j, tree.root, curr);
            }
        }
        return result;
    }
    
    private void dfs(ArrayList<String> result,
                     boolean[][] visited, 
                     char[][] board,
                     int i, int j,
                     TrieNode node,
                     String str) {
        if(node.hasWord && !result.contains(str)) {
            result.add(str);
        }
        
        int[] dirX = {1, -1, 0, 0};
        int[] dirY = {0, 0, 1, -1};
        
        if(inBound(board, visited, i, j) && node.children.containsKey(board[i][j])) {
            for(int k = 0; k < 4; k++) {
                visited[i][j] = true;
                String curr = str + Character.toString(board[i][j]);
                dfs(result, visited, board, i+dirX[k], j+dirY[k], node.children.get(board[i][j]), curr);
                visited[i][j] = false;
            }
        }
    }
    
    private boolean inBound(char[][] board, boolean[][] visited, int i, int j) {
        return i >= 0 && i < board.length && j >= 0 && j < board[0].length && !visited[i][j];
    }


    class TrieNode {
        char c;
        boolean hasWord;
        Map<Character, TrieNode> children = new HashMap<>();
        
        public TrieNode(){}
        
        public TrieNode(char c) {
            this.c = c;
        }
    }
    
    class Trie {
        private TrieNode root;
        
        public Trie() {
            root = new TrieNode();
        }
        
        public void insert(String word) {
            TrieNode curr = root;
            Map<Character, TrieNode> currChildren = root.children;
            
            char[] wordArray = word.toCharArray();
            for(int i = 0; i < wordArray.length; i++) {
                char wc = wordArray[i];
                if(currChildren.containsKey(wc)) {
                    curr = currChildren.get(wc);
                }
                else {
                    TrieNode newNode = new TrieNode(wc);
                    currChildren.put(wc, newNode);
                    curr = newNode;
                }
                currChildren = curr.children;
                if(i == wordArray.length - 1) {
                    curr.hasWord = true;
                }
            }
        }
    }
}