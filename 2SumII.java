/*
Given an array of integers that is already sorted in ascending order, 
find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that 
they add up to the target, where index1 must be less than index2. Please 
note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2

Analysis:
Similar with the 2Sum problem, using two pointers from both sides

*/

class 2SumII
{
	public int[] twoSum(int[] nums, int target)
	{
		if(nums == null || nums.length == 0)
			return null;

		int[] result = new int[2];
		int i = 0;
		int j = nums.length - 1;
		while(i < j)
		{
			int tmp = nums[i] + nums[j];
			if(tmp < target)
			{
				i++;
			}
			else if(tmp > target)
			{
				j--;
			}
			else
			{
				//the result is not zero-based, so plus 1.
				result[0] = i+1;
				result[1] = j+1;
				return result;
			}
		}
		return null;
	}
}
