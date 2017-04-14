/*
Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.
For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].

Analysis:
	Only need once walk through. 
	Three cases:
	1) nums[i] < lower, no effect of the result
	2) nums[i] == lower, continue consider lower++
	3) nums[i] > lower, means [lower ... nums[i]-1] is part of what we want. Then lower=nums[i]+1.
*/

class MissingRange {
	public List<Integer> missingRange(int[] nums, int lower, int upper) {
		int n = nums.length;
		List<Integer> result = new ArrayList<Integer>();
		for(int i = 0; (i < n) && (lower < upper) && (nums[i] <= upper); i++) {
			if(nums[i] > lower) {
				//lower ... nums[i]-1
				result.add(getRange(lower, nums[i]-1));
				lower = nums[i] + 1;
			}
			else if(nums[i] == lower) {
				lower++;
			}
		}
		if(lower <= upper) {
			result.add(getRange(lower, upper));
		}
		return result;
	}

	public String getRange(int from, int to) {
		return (from == to) ? String.valueOf(from) : (from + "->" + to);
	}
}