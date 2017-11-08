/*
Given a n,m which means the row and column of the 2D matrix and an array of pair A(size k). 
Originally, the 2D matrix is all 0 which means there is only sea in the matrix. The list pair has k operator and 
each operator has two integer A[i].x, A[i].y means that you can change the grid matrix[A[i].x][A[i].y] 
from sea to island. Return how many island are there in the matrix after each operator.

Notice
0 is represented as the sea, 1 is represented as the island. 
If two 1 is adjacent, we consider them in the same island. 
We only consider up/down/left/right adjacent.

Example
Given n = 3, m = 3, array of pair A = [(0,0),(0,1),(2,2),(2,1)].
return [1,1,2,2].

Analysis:
	Too expensive to use DFS or BFS.
	Use UnionFind here.
*/
public class NumberOfIslandsII {
    /**
     * @param n an integer
     * @param m an integer
     * @param operators an array of point
     * @return an integer array
     */
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        if(positions == null) {
            return result;
        }
        
        int[] dirX = {1, 0, 0, -1};
        int[] dirY = {0, 1, -1, 0};
        int[][] islands = new int[m][n];
        
        UnionFind uf = new UnionFind(m, n);
        int count = 0;
        
        for(int i = 0; i < positions.length; i++) {
            int x = positions[i][0];
            int y = positions[i][1];
            if(islands[x][y] != 1) {
                count++;
                islands[x][y] = 1;
                int id = uf.convertToId(x, y, n);
                for(int j = 0; j < 4; j++) {
                    int currX = x + dirX[j];
                    int currY = y + dirY[j];
                    if(currX >= 0 && currX < m && currY >= 0 && currY < n && islands[currX][currY] == 1) {
                        int currId = uf.convertToId(currX, currY, n);
                        int father = uf.find(id);
                        int fatherCurr = uf.find(currId);
                        if(father != fatherCurr) {
                            count--;
                            uf.union(father, fatherCurr);
                        }
                    }
                }
            }
            result.add(count);
        }
        return result;
    }
}

class UnionFind {
    Map<Integer, Integer> father = new HashMap<Integer, Integer>();
    
    int convertToId(int x, int y, int n) {
        return x * n + y;
    }
    
    // use convertToId method change 2D element into 1D union find array
    public UnionFind(int n, int m) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int id = convertToId(i, j, m);
                father.put(id, id);
            }
        }
    }
    
    int find(int x) {
        int parent = father.get(x);
        while(parent != father.get(parent)) {
            parent = father.get(parent);
        }
        
        int next;
        while(x != father.get(x)) {
            next = father.get(x);
            father.put(x, parent);
            x = next;
        }
        return parent;
    }
    
    void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY) {
            father.put(rootX, rootY);
        }
    }
}