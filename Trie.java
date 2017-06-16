/*
Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.

Analysis:
A trie node should contains the character, its children and the flag that marks if it is a leaf node.

*/

/**
 * Your Trie object will be instantiated and called as such:
 * Trie trie = new Trie();
 * trie.insert("lintcode");
 * trie.search("lint"); will return false
 * trie.startsWith("lint"); will return true
 */
class TrieNode {
    // Initialize your data structure here.
    char c;
    Map<Character, TrieNode> children = new HashMap<>();
    boolean hasWord;
    
    public TrieNode(){}
    
    public TrieNode(char c) {
        this.c = c;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
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

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if(searchWordNodePos(word) == null) {
            return false;
        }
        return searchWordNodePos(word).hasWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return searchWordNodePos(prefix) != null;
    }
    
    // helper method for search and startWith methods
    private TrieNode searchWordNodePos(String s) {
        TrieNode curr = root;
        Map<Character, TrieNode> currChildren = root.children;
        
        char[] wordArray = s.toCharArray();
        for(int i = 0; i < wordArray.length; i++) {
            char wc = wordArray[i];
            if(currChildren.containsKey(wc)) {
                curr = currChildren.get(wc);
                currChildren = curr.children;
            }
            else {
                return null;
            }
        }
        return curr;
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");