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
	public int rob(int[] num) {
		if(num.length == 0) {
			return 0; 
		}

		int prevprev = 0;
		int prev = num[0];

		//Here start from 1!!!!
		for(int i = 1; i < num.length; i++) {
			int tmp = prev;
			prev = Math.max(prevprev + num[i], prev);
			prevprev = tmp;
		}
		return prev;
	}
}