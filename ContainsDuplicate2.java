/*
Given an array of integers and an integer k, find out whether there there are two 
distinct indices i and j in the array such that nums[i] = nums[j] and the difference 
between i and j is at most k.

*/

class ContainsDuplicate2
{
	public boolean containsNearbyDuplicate(int[] nums, int k)
	{
		if(nums == null || nums.length == 0 || k < 0)
			return false;

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < nums.length; i++)
		{
			if(map.containsKey(nums[i]))
			{
				if(Math.abs(i - map.get(nums[i])) <= k)
				{
					return true;
				}
				map.put(nums[i], i);
			}
			else
			{
				map.put(nums[i], i);
			}
		}
		return false;
	}
}