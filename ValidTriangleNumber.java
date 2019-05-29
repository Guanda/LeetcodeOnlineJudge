/*
Given an array consists of non-negative integers, your task is to count the 
number of triplets chosen from the array that can make triangles if we take 
them as side lengths of a triangle.
Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].

Analysis:
	First, the valid triangle will be nums[i]+nums[j]>nums[k] after sort nums.

	Second, This problem is very similar to 3-Sum, in 3-Sum, we can use three 
	pointers (i, j, k and i < j < k) to solve the problem in O(n^2) time for a 
	sorted array, the way we do in 3-Sum is that we first lock pointer i and then 
	scan j and k, if nums[j] + nums[k] is too large, k--, otherwise j++, once we 
	complete the scan, increase pointer i and repeat.
*/

class Solution {
    public int triangleNumber(int[] nums) {
        int count = 0;
        if(nums == null || nums.length < 3)
            return count;
        Arrays.sort(nums);
        
        for(int k = nums.length - 1; k > 1; k--){
            for(int i = 0, j = k - 1; i < j;){
                if(nums[i] + nums[j] > nums[k]){
                    count += j - i;
                    j--;
                }
                else{
                    i++;
                }
            }
        }
        return count;
    }
}