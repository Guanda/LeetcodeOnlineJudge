/*
Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) 
of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:
nums: [1,2,3]
Result: [1,2] (of course, [1,3] will also be ok)

Example 2:
nums: [1,2,4,8]
Result: [1,2,4,8]

Analysis:
	steps:
		1. Sort
		2. Find the length of longest subset
		3. Record the largest element of it.
		4. Do a loop from the largest element to nums[0], add every element belongs to the longest subset.
*/
class LargestDivisibleSubset {
	public List<Integer> largestDivisibleSubset(int[] nums) {
		List<Integer> result = new ArrayList<>();
		if(nums == null || nums.length == 0) {
			return result;
		}

		Arrays.sort(nums);
		int[] dp = new int[nums.length];
		dp[0] = 1;

		//for each element in nums, find the length of largest subset it has.
		for(int i = 1; i < nums.length; i++) {
			for(int j = i-1; j >= 0; j--) {
				if(nums[i] % nums[j] == 0) {
					dp[i] = Math.max(1 + dp[j], dp[i]);
				}
			}
		}

		//pick the index of the largest element in dp.
		int maxIndex = 0;
		for(int i = 1; i < nums.length; i++) {
			maxIndex = dp[i] > dp[maxIndex] ? i : maxIndex;
		}

		//from nums[maxIndex] to 0, add every element belongs to the largest subset.
		int tmp = nums[maxIndex];
		//the current count
		int curDp = dp[maxIndex];
		for(int i = maxIndex; i >= 0; i--) {
			if(tmp % nums[i] == 0 && dp[i] == curDp) {
				result.add(nums[i]);
				tmp = nums[i];
				curDp--;
			}
		}
		return result;
	}
}