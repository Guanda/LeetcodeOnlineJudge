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

*/

class LongestIncreasingSubsequence {
	//Method 1:
	public int lengthOfLIS(int[] nums) {
		if(nums == null || nums.length == 0)
			return 0;

		int len = nums.length;
		//initial the lis array all elements are 0 which should be considered later.
		int[] lis = new int[len];

		int max = 0;
		for(int i = 1; i < len; i++) {
			for(int j = 0; j < i; j++) {
				if(nums[i] > nums[j] && lis[i] < lis[j] + 1)
					lis[i] = lis[j] + 1;
			}
			max = Math.max(lis[i], max);
		}

		//because we initail lis[i] is 0 which should be 1
		return max + 1;
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