/*
Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .
A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.

Analysis:
	Use Trie and bfs
*/

public class WordDictionary {
    private TrieNode root;
    
    public WordDictionary() {
        root = new TrieNode();
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
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

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        Queue<TrieNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 0;
        while(!queue.isEmpty()) {
            char c = word.charAt(index);
            int size = queue.size();
            boolean flag = false;
            for(int i = 0; i < size; i++) {
                TrieNode curr = queue.poll();
                if(c == '.') {
                    for(TrieNode node : curr.children.values()) {
                        queue.add(node);
                        flag = flag || node.hasWord;
                    }
                }
                else if(curr.children.containsKey(c)){
                    TrieNode newNode = curr.children.get(c);
                    queue.add(newNode);
                    flag = flag || newNode.hasWord;
                }
            }
            index++;
            if(index >= word.length()) {
                return flag;
            }
        }
        return false;
    }
}

class TrieNode {
    char c;
    Map<Character, TrieNode> children = new HashMap<>();
    boolean hasWord;
    
    public TrieNode() {}
    
    public TrieNode(char c) {
        this.c = c;
    }
}


// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");