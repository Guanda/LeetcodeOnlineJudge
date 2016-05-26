/*
Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.

Follow up:

What if the given array is already sorted? How would you optimize your algorithm?

What if nums1's size is small compared to num2's size? Which algorithm is better?

What if elements of nums2 are stored on disk, and the memory is limited such that 
you cannot load all elements into the memory at once?

Analysis:
	1. Sort both arrays
	2. Use hashmap, save all elements in nums1 to map and check nums2, if existing, value--

*/

class IntersectionOfTwoArraysII {
	//method 1, sort
	public int[] intersect(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int[] result = new int[nums1.length];

		int i = 0;
		int j = 0;
		int count = 0;
		while(i < nums1.length && j < nums2.length) {
			if(nums1[i] == nums2[j]) {
				result[count++] = nums1[i];
				i++;
				j++;
			}
			else if(nums1[i] > nums2[j]) {
				j++;
			}
			else {
				i++;
			}
		}

		return Arrays.copyOfRange(result, 0, count);
	}

	//method 2, hashmap
	public int[] intersectBetter(int[] nusm1, int[] nums2) {
		int[] result = new int[nums1.length];
		HashMap<Integer, Integer> map = new HashMap<>();

		for(int i = 0; i < nums1.length; i++) {
			if(map.containsKey(nums1[i])) {
				map.put(nums1[i], map.get(nums1[i]) + 1);
			}
			else {
				map.put(nums1[i], 1);
			}
		}

		int count = 0;
		for(int i = 0; i < nums2.length; i++) {
			if(map.containsKey(nums2[i]) && map.get(nums2[i]) > 0) {
				result[count++] = nums2[i];
				map.put(nums2[i], map.get(nums2[i]) - 1);
			}
		}

		return Arrays.copyOfRange(result, 0, count);
	}
}