/*
Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:
Return true if there exists i, j, k 
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Your algorithm should run in O(n) time complexity and O(1) space complexity.

Examples:
Given [1, 2, 3, 4, 5],
return true.

Given [5, 4, 3, 2, 1],
return false.

Analysis:
	Use a and b to maintanence the smallest two elements
	if nums[i] smaller than a, then a=nums[i]
	if a < nums[i] < b, then b=nums[i]
	if nums[i] > b, then three elements found, return true

*/

public class IncreasingTripletSubsequence {
	public boolean increasingTriplet(int[] nums) {
		if(nums == null || nums.length == 0)
			return false;

		int a = Integer.MAX_VALUE;
		int b = Integer.MAX_VALUE;

		for(int i = 0; i < nums.length; i++) {
			if(nums[i] < a)
				a = nums[i];
			else if(a < nums[i] && b > nums[i])
				b = nums[i];
			else if(nums[i] > b)
				return true;
		}
		return false;
	}
}