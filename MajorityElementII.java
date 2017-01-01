/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
The algorithm should run in linear time and in O(1) space.

Analysis:
	How many majority elements could it possibly have? ---- 0, 1, or 2
	Boyer-Moore Majority Vote algorithm, see the method3 in MajorityElement problem.

*/
class MajorityElementII {
	public List<Integer> majorityElement(int[] nums) {
		List<Integer> result = new ArrayList<Integer>();
		if(nums == null || nums.length == 0) {
			return result;
		}

		int count1 = 0, count2 = 0;
		int candidate1 = nums[0];
		int candidate2 = nums[0];
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] == candidate1)
				count1++;
			else if(nums[i] == candidate2)
				count2++;
			else if(count1 == 0) {
				candidate1 = nums[i];
				count1 = 1;
			}
			else if(count2 == 0) {
				candidate2 = nums[i];
				count2 = 1;
			}
			else {
				count1--;
				count2--;
			}
		}

		//ready for the second loop to check
		count1 = 0;
		count2 = 0;
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] == candidate1)
				count1++;
			else if(nums[i] == candidate2)
				count2++;
		}
		if(count1 > nums.length / 3)
			result.add(candidate1);
		if(count2 > nums.length / 3)
			result.add(candidate2);

		return result;
	}
}