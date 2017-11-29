/*
Given a list of non-negative numbers and a target integer k, write a function to 
check if the array has a continuous subarray of size at least 2 that sums up to 
the multiple of k, that is, sums up to n*k where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
Note:
The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.

Analysis:
	We iterate through the input array exactly once, keeping track of the running sum mod k 
	of the elements in the process. If we find that a running sum value at index j has been 
	previously seen before in some earlier index i in the array, then we know that the 
	sub-array (i,j] contains a desired sum.

	(a+(n*x))%x is same as (a%x)

	For e.g. in case of the array [23,2,6,4,7] the running sum is [23,25,31,35,42] and the 
	remainders are [5,1,1,5,0]. We got remainder 5 at index 0 and at index 3. That means, in 
	between these two indexes we must have added a number which is multiple of the k. Hope 
	this clarifies your doubt :)
*/

class ContinuousSubarraySum {
	// Naive Solution: using prefix sum to check any subarray
    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        
        if(k == 0) {
            
        }
        
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for(int i = 1; i <= nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        
        for(int i = 2; i < prefixSum.length; i++) {
            for(int j = 0; j < i - 1; j++) {
                if(k == 0) {
                    if(prefixSum[i] == prefixSum[j]) {
                        return true;
                    }
                }
                else {
                    if((prefixSum[i] - prefixSum[j]) % k == 0) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }



    // Better solution:
    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int runningSum = 0;
        for(int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            if(k != 0) {
                runningSum %= k;
            }
            Integer prev = map.get(runningSum);
            if(prev != null) {
                if(i - prev > 1) {
                    return true;
                }
            }
            else {
                map.put(runningSum, i);
            }
        }
        return false;
    }
}