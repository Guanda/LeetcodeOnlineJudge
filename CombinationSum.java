/*
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

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
		ArrayList<Integer> list = new ArrayList<Integer>();

		if(candidates == null || candidates.length == 0 || target <= 0)
		{
			return result;
		}
		Arrays.sort(candidates);

		combinationSumHelper(candidates, target, 0, result, list);
		return result;
	}

	public void combinationSumHelper(int[] candidates, int target, int start,
									ArrayList<ArrayList<Integer>> result, 
									ArrayList<Integer> list)
	{
		if(0 == target)
		{
			result.add(new ArrayList<Integer>(list));
		}
		else
		{
			for(int i = 0; i < candidates.length && candidates[i] <= target; i++)
			{
				list.add(candidates[i]);
				combinationSumHelper(candidates, target-candidates[i], i, result, list);
				list.remove(list.size() - 1);
			}
		}

		return;
	}
}