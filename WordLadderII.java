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

Analysis:
	Use BFS first to generate graph based map and calculate distance between each node and start.
	Then use DFS to find out the path.
	Since we have distance map which level the map, so it is guaranteed that we have shortest path.
*/
class WordLadderII {
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> results = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        
        bfs(start, end, dict, map, distance);
        
        List<String> path = new ArrayList<>();
        
        dfs(start, end, path, map, distance, results);
        
        return results;
    }
    
    private void dfs(String start,
                     String end,
                     List<String> path,
                     Map<String, List<String>> map,
                     Map<String, Integer> distance,
                     List<List<String>> results) {
        path.add(start);
        if(start.equals(end)) {
            results.add(new ArrayList<String>(path));
        }
        else {
            for(String next : map.get(start)) {
                if (distance.containsKey(next) && distance.get(next) == distance.get(start) + 1) { 
                    dfs(next, end, path, map, distance, results);
                }
            }
        }
        path.remove(path.size() - 1);
    }
    
    private void bfs(String start,
                     String end,
                     Set<String> dict,
                     Map<String, List<String>> map,
                     Map<String, Integer> distance) {
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        distance.put(start, 0);
        for(String s : dict) {
            map.put(s, new ArrayList<String>());
        }
        
        while(!queue.isEmpty()) {
            String curr = queue.poll();
            List<String> adjList = findAdjList(curr, dict);
            for(String adj : adjList) {
                map.get(curr).add(adj);
                if(!distance.containsKey(adj)) {
                    distance.put(adj, distance.get(curr) + 1);
                    queue.add(adj);
                }
            }
        }
    }
    
    private List<String> findAdjList(String curr, Set<String> dict) {
        List<String> list = new ArrayList<String>();

        for(int i = 0; i < curr.length(); i++) {
            for(char c = 'a'; c <= 'z'; c++) {
                String s = curr.substring(0, i) + c + curr.substring(i + 1);
                if(dict.contains(s)) {
                    list.add(s);
                }
            }
        }
        return list;
    }
}