/*
Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].

*/

public class SummaryRanges
{
	public List<String> summaryRanges(int[] nums)
	{
		List<String> result = new ArrayList<>();
		if(nums == null || nums.length == 0) {
			return result;
		}

		if(nums.length == 1) {
			result.add(nums[0]+"");
			return result;
		}

		int j = 0;
		for(int i = 1; i < nums.length; i++) {
			if(nums[i - 1] + 1 < nums[i]) {
				if(j == i - 1) {
					result.add(nums[j] + "");
				}
				else {
					result.add(nums[j] + "->" + nums[i - 1]);
				}

				if(i == nums.length - 1) {
					result.add(nums[i] + "");
				}

				j = i;
			}
			else if(i == nums.length - 1) {
				result.add(nums[j] + "->" + nums[i]);
			}
		}
		return result;
	}
}