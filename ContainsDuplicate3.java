/*
Given an array of integers, find out whether there are two distinct indices i and j in the array 
such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.

Analysis:
	This problem requires to maintain a window of size k of the previous values that can be queried for value ranges. 
	The best data structure to do that is Binary Search Tree. As a result maintaining the tree of size k will result 
	in time complexity O(N lg K). In order to check if there exists any value of range abs(nums[i] - nums[j]) to simple 
	queries can be executed both of time complexity O(lg K)

	Here is the whole solution using TreeMap.

*/
class ContainsDuplicate3 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return false;
        }

        final TreeSet<Integer> values = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {

            final Integer floor = values.floor(nums[i] + t);
            final Integer ceil = values.ceiling(nums[i] - t);
            if ((floor != null && floor >= nums[i]) || (ceil != null && ceil <= nums[i])) {
                return true;
            }

            values.add(nums[i]);
            //make sure the range of index is k.
            if (i >= k) {
                values.remove(nums[i - k]);
            }
        }

        return false;
    }
}