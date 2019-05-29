/*
Given an array of integers nums and a positive integer k, find whether it's possible 
to divide this array into k non-empty subsets whose sums are all equal.

 

Example 1:

Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 

Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.

Analysis:
	Since we need to partition the nums, so we can find out the target value in each group.
	
Solution 1: Recusive, Time Complexity: O(k^{N-k} k!), where NN is the length of nums, and 
k is as given. As we skip additional zeroes in groups, naively we will make O(k!) calls to 
search, then an additional O(k^{N-k}) calls after every element of groups is nonzero.

Solution 2:


*/
class Solution {
	// Solution 1
    public boolean canPartitionKSubsets(int[] a, int k) {
        int sum = IntStream.of(a).sum();
        return k != 0 && sum % k == 0 && canPartition(0, a, new boolean[a.length], k, 0, sum / k);
    }

    boolean canPartition(int start, int[] a, boolean[] seen, int k, int sum, int target) {
        if (k == 1) 
            return true;
        if (sum == target)
            return canPartition(0, a, seen, k - 1, 0, target);
        for (int i = start; i < a.length; i++)
            if (!seen[i]) {
                seen[i] = true;
                if (canPartition(i + 1, a, seen, k, sum + a[i], target))
                    return true;
                seen[i] = false;
            }
        return false;
    }
}