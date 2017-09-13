/*
You are a professional robber planning to rob houses along a street. 
Each house has a certain amount of money stashed, the only constraint 
stopping you from robbing each of them is that adjacent houses have 
security system connected and it will automatically contact the police 
if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money 
of each house, determine the maximum amount of money you can rob tonight 
without alerting the police.

Analysis:
	An easy DP problem.
	The optimal way to rob all these homes is to either rob the last one 
	and optimally rob the rest without the immediate neighbor, or not rob 
	the last one and optimally rob all the ones before.

showing like this:
	Max(dp[k-2]+num[k], dp[k-1])
*/

class HouseRobber {
	// Solution 1: normal DP
	public long rob(int[] A) {
        // write your code here
        if(A == null || A.length == 0) {
            return 0;
        }
        
        long[] dp = new long[A.length + 1];
        dp[0] = 0;
        dp[1] = A[0];
        for(int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + A[i - 1]);
        }
        
        return dp[A.length];
    }


    // Solution 2: improved DP
    public long rob(int[] A) {
        // write your code here
        if(A == null || A.length == 0) {
            return 0;
        }
        
        long[] dp = new long[2];
        dp[0] = 0;
        dp[1] = A[0];
        for(int i = 2; i <= A.length; i++) {
            dp[i % 2] = Math.max(dp[(i - 1) % 2], dp[(i - 2) % 2] + A[i - 1]);
        }
        
        return dp[A.length % 2];
    }


    // Another version of solution2
	public int rob(int[] num) {
		if(num.length == 0) {
			return 0; 
		}

		int prevprev = 0;
		int prev = num[0];

		for(int i = 1; i < num.length; i++) {
			int tmp = prev;
			prev = Math.max(prevprev + num[i], prev);
			prevprev = tmp;
		}
		return prev;
	}
}