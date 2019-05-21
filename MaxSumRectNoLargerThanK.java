class MaxSumRectNoLargerThanK {
    int[][] dp;
    public int maxSumSubmatrix(int[][] matrix, int k) {
        init(matrix);
        int m = matrix.length;
        int n = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for(int r0 = 0; r0 < m; r0++){
            for(int c0 = 0; c0 < n; c0++){
                for(int r1 = r0; r1 < m; r1++){
                    for(int c1 = c0; c1 < n; c1++){
                        int sum = getRectSum(r0, c0, r1, c1);
                        if(sum <= k) {
                            max = Math.max(sum, max);
                        }
                    }
                }
            }
        }
        return max;
    }
    
    public void init(int[][] matrix){
        int m = matrix.length + 1;
        int n = matrix[0].length + 1;
        dp = new int[m+1][n+1];
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i-1][j-1];
            }
        }
    }
    
    public int getRectSum(int r0, int c0, int r1, int c1){
        return dp[r1+1][c1+1] - dp[r0][c1+1] - dp[r1+1][c0] + dp[r0][c0];
    }
}