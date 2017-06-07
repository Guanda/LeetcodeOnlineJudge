/*
Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.

Analysis:
	Using hashset to save all elements in nums1 and check the nums2
	Time Complexity: O(n)
*/

class IntersectionOfTwoArrays {
	//Solution 1: use two hashsets
	public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> intersectionSet = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        
        for(int i = 0; i < nums2.length; i++) {
            if(set.contains(nums2[i])) {
                intersectionSet.add(nums2[i]);
            }
        }
        
        int[] result = new int[intersectionSet.size()];
        int k = 0;
        for(Integer num : intersectionSet) {
            result[k++] = num;
        }
        
        return result;
    }


	//Solution2: use one hashmap
	public int[] intersection(int[] nums1, int[] nums2) {
		//count is tracking the numbers in result array
		int count = 0;
		int[] result = new int[nums1.length];
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < nums1.length; i++) {
			map.put(nums1[i], 1);
		}

		for(int i = 0, j = 0; i < nums2.length; i++) {
			if(map.containsKey(nums2[i]) && map.get(nums2[i]) == 1) {
				result[j++] = nums2[i];
				count++;
				map.put(nums2[i], 2);
			}
		}
		return Arrays.copyOfRange(result, 0, count);
	}
}