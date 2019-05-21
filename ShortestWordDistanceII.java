/*
Design a class which receives a list of words in the constructor, and implements a method that 
takes two words word1 and word2 and return the shortest distance between these two words in the 
list. Your method will be called repeatedly many times with different parameters. 

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1
Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

*/
class WordDistance {

    Map<String, ArrayList<Integer>> map = new HashMap<>();
    
    public WordDistance(String[] words) {
        for(int i = 0; i < words.length; i++){
            if(map.containsKey(words[i])){
                map.get(words[i]).add(i);
            }
            else{
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(words[i], list);
            }
        }
    }
    
    public int shortest(String word1, String word2) {
        ArrayList<Integer> list1 = map.get(word1);
        ArrayList<Integer> list2 = map.get(word2);
        int i = 0;
        int j = 0;
        int result = Integer.MAX_VALUE;
        while(i < list1.size() && j < list2.size()){
            result = Math.min(result, Math.abs(list1.get(i) - list2.get(j)));
            if(list1.get(i) < list2.get(j)){
                i++;
            }
            else{
                j++;
            }
        }
        return result;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */