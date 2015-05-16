/*
Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.

Analysis:
We only need to add one more condition, which checks if the left-most element 
and the right-most element are equal. If they are we can simply drop one of them. 
In my solution below, I drop the left element whenever the left-most equals to 
the right-most.

*/

class FindMinInRotatedSortedArray2
{
	public int findMin(int[] nums)
	{
		return findMin(nums, 0, nums.length-1);
	}

	public int findMin(int[] nums, int left, int right)
	{
		if(left == right)
			return nums[left];
		if(right - left == 1)
			return Math.min(nums[left], nums[right]);

		int middle = (left + right) / 2;

		//no rotated
		if(nums[left] < nums[right])
		{
			return nums[left];
		}
		//shift it one step to the right
		else if(nums[left] == nums[right])
		{
			return findMin(nums, left+1, right);
		}
		//min in right side
		else if(nums[left] <= nums[middle])
		{
			return findMin(nums, middle, right);
		}
		//min in left side
		else
		{
			return findMin(nums, left, middle);
		}
	}
}