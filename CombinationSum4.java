/*
Given an integer array with all positive numbers and no duplicates, find the number of possible combinations 
that add up to a positive integer target.

Example:
nums = [1, 2, 3]
target = 4
The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
Note that different sequences are counted as different combinations.
Therefore the output is 7.

Analysis:
	Think about the recurrence relation first. How does the # of combinations of the target related to 
	the # of combinations of numbers that are smaller than the target?

	So we know that target is the sum of numbers in the array. Imagine we only need one more number to 
	reach target, this number can be any one in the array, right? So the # of combinations of target, 
	comb[target] = sum(comb[target - nums[i]]), where 0 <= i < nums.length, and target >= nums[i].

	In the example given, we can actually find the # of combinations of 4 with the # of combinations of 3(4 - 1), 
	2(4- 2) and 1(4 - 3). As a result, comb[4] = comb[4-1] + comb[4-2] + comb[4-3] = comb[3] + comb[2] + comb[1].

	Then think about the base case. Since if the target is 0, there is only one way to get zero, which is using 0, 
	we can set comb[0] = 1.

	EDIT: The problem says that target is a positive integer that makes me feel it's unclear to put it in the 
	above way. Since target == 0 only happens when in the previous call, target = nums[i], we know that this is 
	the only combination in this case, so we return 1.
*/
class CombinationSum4 {
	//Recursive solution
	public int combinationSum4(int[] nums, int target) {
	    if (target == 0) {
	        return 1;
	    }
	    int res = 0;
	    for (int i = 0; i < nums.length; i++) {
	        if (target >= nums[i]) {
	            res += combinationSum4(nums, target - nums[i]);
	        }
	    }
	    return res;
	}

	//dp solution
	private int[] dp;

	public int combinationSum4(int[] nums, int target) {
	    dp = new int[target + 1];
	    Arrays.fill(dp, -1);
	    dp[0] = 1;
	    return helper(nums, target);
	}

	private int helper(int[] nums, int target) {
	    if (dp[target] != -1) {
	        return dp[target];
	    }
	    int res = 0;
	    for (int i = 0; i < nums.length; i++) {
	        if (target >= nums[i]) {
	            res += helper(nums, target - nums[i]);
	        }
	    }
	    dp[target] = res;
	    return res;
	}
}