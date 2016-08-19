/*
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the 
candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.

For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 

*/

public class CombinationSum
{
	public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target)
	{
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		if(candidates == null || candidates.length == 0 || target <= 0)
		{
			return result;
		}
		Arrays.sort(candidates);

		combinationSumHelper(candidates, target, 0, new ArrayList<Integer>(), result);
		return result;
	}

	public void combinationSumHelper(int[] candidates, int target, int start,
	                                ArrayList<Integer> list,
									ArrayList<ArrayList<Integer>> result)
	{
		if(0 == target)
		{
			result.add(new ArrayList<Integer>(list));
		}
		else
		{
			for(int i = start; i < candidates.length && candidates[i] <= target; i++)
			{
				list.add(candidates[i]);
				combinationSumHelper(candidates, target-candidates[i], i, list, result);
				list.remove(list.size() - 1);
			}
		}

		return;
	}
}