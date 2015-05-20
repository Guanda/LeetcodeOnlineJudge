/*
Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.

Analysis:
Since we should use constant space, so hashmap is not working in this case.

Let's take another look at the problem. It is asking for the first missing POSITIVE integer.
So, given a number in the array,
1) if it is non-positive, ignore it;
2) if it is positive, say we have A[i] = x, we know it should be in slot A[x-1]! That is to say, 
   we can swap A[x-1] with A[i] so as to place x into the right place.

We need to keep swapping until all numbers are either non-positive or in the right places. 
The result array could be something like [1, 2, 3, 0, 5, 6, ...]. Then it's easy to tell that 
the first missing one is 4 by iterate through the array and compare each value with their index.

(If the current value larger than length of the array, keep it stay in same place)

*/

class FindMissingPositive
{
	public int firstMissingPositive(int[] nums)
	{
		//swap the element to their value position
		int i = 0;
		while(i < nums.length)
		{
			if(nums[i] > 0 && nums[i] <= nums.length && nums[i] != i+1 && nums[i] != nums[nums[i] - 1])
			{
				int tmp = nums[nums[i] - 1];
				nums[nums[i] - 1] = nums[i];
				nums[i] = tmp;
			}
			else
			{
				i++;
			}
		}

		//find the first positive missing integer
		i = 0;
		while(i < nums.length && nums[i] == i+1)
			i++;
		
		return i+1;
	}
}