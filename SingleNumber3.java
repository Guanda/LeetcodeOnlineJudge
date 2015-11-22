/*
Given an array of numbers nums, in which exactly two elements appear only once 
and all the other elements appear exactly twice. Find the two elements that appear 
only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only 
constant space complexity?

Analysis:
	1. Use hashset to save element value and count.
	2. bit manipulation

*/

public class SingleNumber3
{
	//Method 1: time O(n), space O(n)
	public int[] singleNumber(int[] nums)
	{
		int[] result = new int[2];
		if(nums == null || nums.length <= 1) {
			return result;
		}

		HashSet<Integer> set = new HashSet<>();

		for(int i = 0; i < nums.length; i++) {
			if(set.contains(nums[i])) {
				set.remove(nums[i]);
			}
			else {
				set.add(nums[i]);
			}
		}

		Object[] ans = set.toArray();
		result[0] = (int)ans[0];
		result[1] = (int)ans[1];
		return result;
	}
}
