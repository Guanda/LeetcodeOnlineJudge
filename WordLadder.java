/*
Given two words (start and end), and a dictionary, find the length of shortest transformation 
sequence from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary

For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.

Analysis: BFS
*/

class WordLadder {
    public int ladderLength(String start, String end, Set<String> dict) {
        if(dict == null || dict.size() == 0) {
            return 0;
        }
        
        if(start.equals(end)) {
            return 1;
        }
        
        dict.add(start);
        dict.add(end);
        
        Queue<String> queue = new LinkedList<>();
        Set<String> hashset = new HashSet<>();
        queue.add(start);
        hashset.add(start);
        
        int len = 1;
        while(!queue.isEmpty()) {
            len++;
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                String curr = queue.poll();
                for(String next : getNextWords(curr, dict)) {
                    if(hashset.contains(next)) {
                        continue;
                    }
                    if(next.equals(end)) {
                        return len;
                    }
                    hashset.add(next);
                    queue.add(next);
                }
            }
        }
        return 0;
    }
    
    private List<String> getNextWords(String curr, Set<String> dict) {
        List<String> list = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < curr.length(); i++) {
                if (c == curr.charAt(i)) {
                    continue;
                }
                String nextWord = replace(curr, i, c);
                if (dict.contains(nextWord)) {
                    list.add(nextWord);
                }
            }
        }
        return list;
    }
    
    private String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }
}
