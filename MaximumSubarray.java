/*
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.

More practice:
If you have figured out the O(n) solution, try coding another solution using the divide 
and conquer approach, which is more subtle.

Anaylsis:
	The changing condition for dynamic programming is "We should ignore the sum of 
	the previous n-1 elements if nth element is greater than the sum."
*/

class MaximumSubarray {
	//DP solution
	public int maxSubarray(int[] A) {
		int max = A[0];
		int[] sum = new int[A.length];
		sum[0] = A[0];

		for(int i = 1; i < A.length; i++) {
			sum[i] = Math.max(A[i], sum[i-1] + A[i]);
			max = Math.max(max, sum[i]);
		}

		return max;
	}

	//prefix sum solution, 找到之前最小的prefix sum和目前最大的prefix sum，相减即为max
    public int maxSubArray(int[] nums) {        
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int minSum = 0; //i之前最小的prefixSum
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
        }
        
        return max;
    }
}