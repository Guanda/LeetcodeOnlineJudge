/*
Given a positive integer n, find the least number of perfect square numbers 
(for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.

Analysis:
	Use DP
*/
class PerfectSquares {
	public int numSquares(int n) {
		if(n <= 0)
			return 0;

		//initial count[], count[i] = the least number of perfect square numbers 
        // which sum to i. Note that count[0] is 0.
		int[] count = new int[n+1];
		count[0] = 0;
		for(int i = 1; i <= n; i++) {
			count[i] = Integer.MAX_VALUE;
		}

		for(int i = 1; i <= n; i++) {
			for(int j = 1; j*j <= i; j++) {
				count[i] = Math.min(count[i], count[i-j*j] + 1);
			}
		}

		return count[n];
	}
}