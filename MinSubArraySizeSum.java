/*
Given an array of n positive integers and a positive integer s, find the minimal length of a 
subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

Analysis:
	Use two pointers both from beginning
	And you should know the subarray is the consistently sub array in the array.
*/

class MinSubArraySizeSum {
	public int minSubArrayLen(int s, int[] nums) {
		if(nums.length == 0)
			return 0;
		int first = 0;
		int second = 0;
		int min = Integer.MAX_VALUE;
		int sum = 0;

		while(first < nums.length) {
			sum = sum + nums[first];
			while(sum >= s) {
				min = Math.min(first-second+1, min);
				sum = sum - nums[second];
				second++;
			}
			first++;
		}

		return (min == Integer.MAX_VALUE) ? 0 : min;
	}
}