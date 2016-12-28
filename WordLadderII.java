/*
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) 
from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.

*/
class WordLadderII {
	/**
	 * we are essentially building a graph, from start, BF.
	 * and at each level we find all reachable words from parent.
	 * we stop if the current level contains end,
	 * we return any path whose last node is end.
	 * 
	 * to achieve BFT, use a deuqe;
	 * a key improvement is to remove all the words we already reached
	 * in PREVIOUS LEVEL; we don't need to try visit them again
	 * in subsequent level, that is guaranteed to be non-optimal solution.
	 * at each new level, we will removeAll() words reached in previous level from dict.
	 */
	public List<List<String>> findLadders(String start, String end, Set<String> dict) {
	    List<List<String>> results = new ArrayList<List<String>>();
	    dict.add(end);
	    // instead of storing words we are at, we store the paths.
	    Deque<List<String>> paths = new LinkedList<List<String>>();
	    List<String> path0 = new LinkedList<String>();
	    path0.add(start);
	    paths.add(path0);
	    // if we found a path ending at 'end', we will set lastLevel,
	    // use this data to stop iterating further.
	    int level = 1, lastLevel = Integer.MAX_VALUE;
	    Set<String> wordsPerLevel = new HashSet<String>();
	    while (!paths.isEmpty()) {
	        List<String> path = paths.pollFirst();
	        if (path.size() > level) {
	            dict.removeAll(wordsPerLevel);
	            wordsPerLevel.clear();
	            level = path.size();
	            if (level > lastLevel)
	                break; // stop and return
	        }
	        //  try to find next word to reach, continuing from the path
	        String last = path.get(level - 1);
	        char[] chars = last.toCharArray();
	        for (int index = 0; index < last.length(); index++) {
	            char original = chars[index];
	            for (char c = 'a'; c <= 'z'; c++) {
	                chars[index] = c;
	                String next = new String(chars);
	                if (dict.contains(next)) {
	                    wordsPerLevel.add(next);
	                    List<String> nextPath = new LinkedList<String>(path);
	                    nextPath.add(next);
	                    if (next.equals(end)) {
	                        results.add(nextPath);
	                        lastLevel = level; // curr level is the last level
	                    } else
	                        paths.addLast(nextPath);
	                }
	            }
	            chars[index] = original;
	        }
	    }
	    
	    return results;
	}
}