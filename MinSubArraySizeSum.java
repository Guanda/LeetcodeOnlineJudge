/*
Given an array of n positive integers and a positive integer s, find the minimal length of a 
subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

Analysis:
Use two pointers
And you should know the subarray is the consistently sub array in the array.

*/

class MinSubArraySizeSum
{
	public int minSubArrayLen(ints, int[] nums)
	{
		if(nums.length == 0)
			return 0;
		int first = 0;
		int second = 0;
		int min = nums.length + 1;
		int sum = nums[0];

		while(first < nums.length && second <= first)
		{
			if(sum < s)
			{
				first++;
				if(first < nums.length)
					sum = sum + nums[first];
			}
			else
			{
				min = Math.min(first-second+1, min);
				sum = sum - nums[second];
				second++;
			}
		}
		if(min == nums.length + 1)
			return 0;

		return min;
	}
}