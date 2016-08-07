/*
 Given a 2D board and a list of words from the dictionary, find all words in the board.

 Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

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
    Set<String> result = new HashSet<String>();

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for(String word : words) {
            trie.insert(word);
        }

        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                dfs(board, visited, "", i, j, trie);
            }
        }

        return new ArrayList<String>(result);
    }

    public void dfs(char[][] board, boolean[][] visited, String str, int x, int y, Trie trie) {
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length)
            return;

        if(visited[x][y])
            return;

        str += board[x][y];
        if(!trie.startsWith(str))
            return;

        if(trie.search(str))
            result.add(str);

        visited[x][y] = true;
        dfs(board, visited, str, x-1, y, trie);
        dfs(board, visited, str, x+1, y, trie);
        dfs(board, visited, str, x, y-1, trie);
        dfs(board, visited, str, x, y+1, trie);
        visited[x][y] = false;
    }
}

//The implementation of Trie data structure
class TrieNode {
    public TrieNode[] children = new TrieNode[26];
    public String item = "";

    public TrieNode(){}
}

public class Trie {
    private TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    //insert a word into trie
    public void insert(String word) {
        TrieNode node = root;
        for(char c : word.toCharArray()) {
            if(node.children[c-'a'] == null)
                node.children[c-'a'] = new TrieNode();
            node = node.children[c-'a'];
        }
        node.item = word;
    }

    //returns if the word is in the trie
    public boolean search(String word) {
        TrieNode node = root;
        for(char c : word.toCharArray()) {
            if(node.children[c - 'a'] == null)
                return false;

            node = node.children[c - 'a'];
        }
        return node.item.equals(word);
    }

    //returns if there is any word in the trie that starts with the given prefix
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for(char c : prefix.toCharArray()) {
            if(node.children[c - 'a'] == null)
                return false;

            node = node.children[c - 'a'];
        }
        return true;
    }
}