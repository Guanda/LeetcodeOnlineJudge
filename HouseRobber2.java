/*
Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for 
his thievery so that he will not get too much attention. This time, all houses at this 
place are arranged in a circle. That means the first house is the neighbor of the last 
one. Meanwhile, the security system for these houses remain the same as for those in 
the previous street.

Given a list of non-negative integers representing the amount of money of each house, 
determine the maximum amount of money you can rob tonight without alerting the police.

Analysis:
	This is an extension of House Robber. There are two cases here 1) 1st element is included 
	and last is not included 2) 1st is not included and last is included. Therefore, we can 
	use the similar dynamic programming approach to scan the array twice and get the larger value.

*/

class HouseRobber2
{
	public int rob(int[] nums)
	{
		if(nums == null || nums.length == 0)
			return 0;

		if(nums.length == 1)
			return nums[0];

		if(nums.length == 2)
			return Math.max(nums[0], nums[1]);

		//include the first element not include last
		int prevprev1 = 0;
		int prev1 = nums[0];

		for(int i = 2; i < nums.length; i++)
		{
			int tmp = prev1;
			prev1 = Math.max(prev1, prevprev1+nums[i-1]);
			prevprev1 = tmp;
		}

		//include the last not include the first
		int prevprev2 = 0;
		int prev2 = nums[1];

		for(int i = 2; i < nums.length; i++)
		{
			int tmp = prev2;
			prev2 = Math.max(prev2, prevprev2+nums[i]);
			prevprev2 = tmp;
		}

		return Math.max(prev1, prev2);
	}
}