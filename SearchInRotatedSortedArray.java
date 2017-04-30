/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Analysis:
	For this problem we don't need to know where the pivot is. Look at the middle element, compare it with
	the left most and right most element, if the left most is less than middle, we will know all elements in 
	the first half must be in strictly increasing order. Like this do binary search.
*/

class SearchInRotatedSortedArray {
	public int search(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;

		while(left <= right) {
			int middle = left + (right - left) / 2;
			if(nums[middle] == target)
				return middle;

			//the first half is sorted
			if(nums[left] <= nums[middle]) {
				if(nums[left] <= target && target < nums[middle])
					right = middle - 1;
				else
					left = middle + 1;
			}
			//the second half is sorted
			else {
				if(nums[middle] < target && target <= nums[right])
					left = middle + 1;
				else
					right = middle - 1;
			}
		}
		return -1;
	}
}
