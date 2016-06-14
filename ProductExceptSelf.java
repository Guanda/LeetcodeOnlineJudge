/*
Given an array of n integers where n > 1, nums, return an array output such 
that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does 
not count as extra space for the purpose of space complexity analysis.)

Analysis:
	Method 1:
	The idea is for every elements in array, we can calculate the left of it and then
	the right of it and then multiply them we will get what we want.

	for example: [1,2,3,4], each elemnt's left is [1,1,2,6] and right is [24,12,4,1]
	result should be [24,12,8,6]

	And the most left and most right value are always 1.

	We cannot just get all products and divided by each element, because we should consider 0.

*/

public class ProductExceptSelf
{
	public int[] productExceptSelf(int[] nums)
	{
		int[] result = new int[nums.length];
		result[0] = 1;
		for(int i = 1; i < nums.length; i++) {
			result[i] = result[i-1] * nums[i-1];
		}
		int right = 1;
		for(int i = nums.length - 1; i >= 0; i--) {
			result[i] = result[i] * right;
			right = right * nums[i];
		}
		return result;
	}
}