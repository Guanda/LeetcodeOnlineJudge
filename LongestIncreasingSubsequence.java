/*
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that 
there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n^2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?

Analysis:
	Method 1: Time O(n^2)
		Let arr[0..n-1] be the input array and L(i) be the length of the LIS till index i such that 
		arr[i] is part of LIS and arr[i] is the last element in LIS, then L(i) can be recursively written as.
		L(i) = { 1 + Max ( L(j) ) } where j < i and arr[j] < arr[i] and if there is no such j then L(i) = 1

	Method 2: Time O(nlogn)
		tails is an array storing the smallest tail of all increasing subsequences with length i+1 in tails[i].
		For example, say we have nums = [4,5,6,3], then all the available increasing subsequences are:
		len = 1   :      [4], [5], [6], [3]         => tails[0] = 3
		len = 2   :      [4, 5], [5, 6], [4, 6]     => tails[1] = 5
		len = 3   :      [4, 5, 6]                  => tails[2] = 6
		We can easily prove that tails is a increasing array. Therefore it is possible to do a binary search in 
		tails array to find the one needs update.

		Each time we only do one of the two:
		(1) if x is larger than all tails, append it, increase the size by 1
		(2) if tails[i-1] < x <= tails[i], update tails[i]
*/

class LongestIncreasingSubsequence {
	//Method 1:
	public int lengthOfLIS(int[] nums) {
		if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] dp = new int[nums.length];
        int max = 0;
        for(int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
            max = Math.max(dp[i], max);
        }
        
        return max;
	}

	//Method 2:
	public int lengthOfLIS(int[] nums) {
		if(nums == null || nums.length == 0)
			return 0;

		int[] tails = new int[nums.length];
		int size = 0;
		for(int x : nums) {
			int i = 0, j = size;
			//binary search
			while(i != j) {
				int m = (i + j) / 2;
				if(tails[m] < x) {
					i = m + 1;
				}
				else {
					j = m;
				}
			}
			tails[i] = x;
			if(i == size)
				size++;
		}
		return size;
	}
}