/*
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
Find all unique quadruplets in the array which gives the sum of target.

Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.
    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)
*/

public class FourSum {
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (nums == null || nums.length < 4)
			return result;

		Arrays.sort(nums);
		Set<List<Integer>> hashSet = new HashSet<List<Integer>>();
		for(int i = 0; i < nums.length; i++) {
			for(int j = i + 1; j < nums.length; j++) {
				int k = j + 1;
				int l = nums.length - 1;
				while(k < l) {
					int sum = nums[i] + nums[j] + nums[k] + nums[l];
					if(sum < target) {
						k++;
					}
					else if(sum > target) {
						l--;
					}
					else {
						List<Integer> tmp = new ArrayList<Integer>();
						tmp.add(nums[i]);
						tmp.add(nums[j]);
						tmp.add(nums[k]);
						tmp.add(nums[l]);
						if(!hashSet.contains(tmp)) {
							hashSet.add(tmp);
							result.add(tmp);
						}
						k++;
						l--;	
					}
				}
			}
		}
		return result;
	}
}