/*
Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
1. The order of returned grid coordinates does not matter.
2. Both m and n are less than 150.
Example:
Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic
Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).

Analysis:
    Use BFS to find out all the boarders/highest points
*/

class PacificAtlanticWaterFlow {
    int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] pVisited = new boolean[m][n];
        boolean[][] aVisited = new boolean[m][n];
        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();
        
        //initial vertical boarder
        for(int i = 0; i < m; i++) {
            pQueue.offer(new int[]{i, 0});
            aQueue.offer(new int[]{i, n-1});
            pVisited[i][0] = true;
            aVisited[i][n-1] = true;
        }
        
        // initial horizontal boarder
        for(int i = 0; i < n; i++) {
            pQueue.offer(new int[]{0, i});
            aQueue.offer(new int[]{m-1, i});
            pVisited[0][i] = true;
            aVisited[m-1][i] = true;
        }
        
        bfs(matrix, pQueue, pVisited);
        bfs(matrix, aQueue, aVisited);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if (pVisited[i][j] && aVisited[i][j]){
                    result.add(new int[]{i, j});
                }
            }
        }
        return result;
    }
    
    public void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited){
        int m = matrix.length;
        int n = matrix[0].length;
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            for(int[] d : dir){
                int x = curr[0] + d[0];
                int y = curr[1] + d[1];
                if(x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || matrix[x][y] < matrix[curr[0]][curr[1]]){
                    continue;
                }
                visited[x][y] = true;
                queue.offer(new int[]{x, y});
            }
        }
    }
}