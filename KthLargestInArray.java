/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element 
in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.

Analysis:
1. The simplest way is sort array first then find the element
2. Using the quickSort idea, but try compare pivot and k to find it.

*/

class KthLargestInArray
{
	public int findKthLargest(int[] nums, int k)
	{
		if(k < 1 || nums == null)
			return 0;

		return getKth(nums, nums.length - k +1, 0, nums.length-1);
	}

	public int getKth(int[] nums, int k, int start, int end)
	{
		int pivot = nums[end];
		int left = start;
		int right = end;

		while(true)
		{
			while(nums[left] < pivot && left < right)
				left++;
			while(nums[right] >= pivot && left < right)
				right--;

			if(left == right)
				break;

			swap(nums, left, right);
		}
		swap(nums, left, end);

		if(k == left+1)
		{
			return pivot;
		}
		else if(k < left+1)
		{
			return getKth(nums, k, start, left-1);
		}
		else
		{
			return getKth(nums, k, left+1, end);
		}
	}

	public void swap(int[] nums, int left, int right)
	{
		int tmp = nums[left];
		nums[left] = nums[right];
		nums[right] = tmp;
	}
}