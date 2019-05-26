/*
Given a list of words and two words word1 and word2, return the shortest distance between 
these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “makes”, word2 = “coding”
Output: 1
Input: word1 = "makes", word2 = "makes"
Output: 3
Note:
You may assume word1 and word2 are both in the list.

Analysis:
	i1 and i2 are the indexes where word1 and word2 were last seen. Except if they're 
	the same word, then i1 is the previous index.
*/

class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int result = Integer.MAX_VALUE;
        int p1 = -1, p2 = -1;
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals(word1)){
                p1 = i;
            }
            if(words[i].equals(word2)){
                if(word1.equals(word2)){
                    p1 = p2;
                }
                p2 = i;
            }
            if(p1 != -1 && p2 != -1)
                result = Math.min(result, Math.abs(p1 - p2));
        }
        return result;
    }
}