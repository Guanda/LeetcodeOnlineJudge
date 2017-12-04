/*
Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Write a function to determine if a given target is in the array.

Analysis:
Follow the way in SearchInRotatedSortedArray.java, the only differece will be how to determine
the sorted part. Here we will have duplicates, so every time compare with the edge case, if they
are equal, move one more step to find out the not equal element, so we can find the sorted part.

*/

class SearchInRotatedSortedArray2 {
	public boolean search(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;

		while(left <= right) {
			int middle = (left + right) / 2;
			if(nums[middle] == target)
				return true;

			//the first half is sorted
			if(nums[left] < nums[middle]) {
				if(nums[left] <= target && target < nums[middle])
					right = middle - 1;
				else
					left = middle + 1;
			}
			//the second half is sorted
			else if(nums[left] > nums[middle]) {
				if(nums[middle] < target && target <= nums[right])
					left = middle + 1;
				else
					right = middle - 1;
			}
			else {
				left++;
			}
		}
		return false;
	}
}