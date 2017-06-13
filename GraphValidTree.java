/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
write a function to check whether these edges make up a valid tree.

Notice:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, 
[0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example
Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Analysis:
	Solution 1: BFS
		两个标准：
		1). 所有点都要联通
		2). 是不能有环的

		可以提前判断顶点和边的关系：edges = n - 1
		基于此判断，可以确定hash set的size只要和n相同，就一定是既连通又没有环。

	Solution 2: UnionFind(并查集)
*/
class GraphValidTree {
    /**
     * Solution 1: BFS
     * @param n an integer
     * @param edges a list of undirected edges
     * @return true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if(edges.length != n - 1 || n == 0) {
            return false;
        }
        
        //process edges to make it a graph-able
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for(int i = 0; i < n; i++) {
            graph.put(i, new HashSet<Integer>());
        }
        for(int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        //BFS
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        
        queue.add(0);
        set.add(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (Integer neighbor : graph.get(node)) {
                if(set.contains(neighbor)) {
                    continue; 
                }
                queue.add(neighbor);
                set.add(neighbor);
            }
        }
        return set.size() == n;
    }


    /**
     * Solution 2: union find
     * @param n an integer
     * @param edges a list of undirected edges
     * @return true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if(edges.length != n - 1 || n == 0) {
            return false;
        }
        
        UnionFind uf = new UnionFind(n);
        
        for(int i = 0; i < edges.length; i++) {
            if(uf.find(edges[i][0]) == uf.find(edges[i][1])) {
                return false;
            }
            uf.union(edges[i][0], edges[i][1]);
        }
        return true;
    }
}

class UnionFind {
    private int[] father = null;
    
    public UnionFind(int n) {
        father = new int[n];
        for(int i = 0; i < n; i++) {
            father[i] = i;
        }
    }
    
    public int find(int x) {
        if(father[x] == x) {
            return x;
        }
        return father[x] = find(father[x]);
    }
    
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY) {
            father[rootX] = rootY;
        }
    }
}