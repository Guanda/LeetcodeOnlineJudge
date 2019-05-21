/*
Given a list of words and two words word1 and word2, return the shortest distance between these 
two words in the list.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1
Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
*/

class Solution {
    // Method 1: use HashMap to save positions and then process
    public int shortestDistance(String[] words, String word1, String word2) {
        Map<String, ArrayList<Integer>> map = new HashMap<>();
        for(int i = 0; i < words.length; i++){
            if(!map.containsKey(words[i])){
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(words[i], list);
            }
            else{
                map.get(words[i]).add(i);
            }
        }
        // Now the problem becomes find the minimum diff in two sorted array
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
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

    //Method 2: better solution with only 1 loop and no extra space
    public int shortestDistance(String[] words, String word1, String word2) {
        int p1 = -1, p2 = -1;
        int result = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++){
            if(words[i].equals(word1)){
                p1 = i;
            }
            else if(words[i].equals(word2)){
                p2 = i;
            }
            if(p1 != -1 && p2 != -1){
                result = Math.min(result, Math.abs(p1 - p2));
            }
        }
        return result;
    }
}