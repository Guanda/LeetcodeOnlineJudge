/*
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
1. You must not modify the array (assume the array is read only).
2. You must use only constant, O(1) extra space.
3. Your runtime complexity should be less than O(n2).
4. There is only one duplicate number in the array, but it could be repeated more than once.

Analysis:

The main idea is the same with problem Linked List Cycle II,https://leetcode.com/problems/linked-list-cycle-ii/. 
Use two pointers the fast and the slow. The fast one goes forward two steps each time, while the slow one goes 
only step each time. They must meet the same item when slow==fast. In fact, they meet in a circle, the duplicate 
number must be the entry point of the circle when visiting the array from nums[0]. Next we just need to find the 
entry point. We use a point(we can use the fast one before) to visit form begining with one step each time, do the 
same job to slow. When fast==slow, they meet at the entry point of the circle. The easy understood code is as follows.

*/
class FindDuplicateNumber {
	public int findDuplicate(int[] nums) {
		if(nums.length > 1) {
			int slow = nums[0];
			int fast = nums[nums[0]];
			while(slow != fast) {
				slow = nums[slow];
				fast = nums[nums[fast]];
			}

			fast = 0;
			while(fast != slow) {
				fast = nums[fast];
				slow = nums[slow];
			}
			return slow;
		}
		
		return -1;
	}
}