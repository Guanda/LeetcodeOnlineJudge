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

public class CombinationSum {
	public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		if(candidates == null || candidates.length == 0 || target <= 0) {
			return result;
		}
		Arrays.sort(candidates);

		combinationSumHelper(candidates, target, 0, new ArrayList<Integer>(), result);
		return result;
	}

	public void combinationSumHelper(int[] candidates, int target, int start,
	                                ArrayList<Integer> list,
									ArrayList<ArrayList<Integer>> result) {
		if(0 == target) {
			result.add(new ArrayList<Integer>(list));
		}
		else {
			for(int i = start; i < candidates.length && candidates[i] <= target; i++) {
				list.add(candidates[i]);
				combinationSumHelper(candidates, target-candidates[i], i, list, result);
				list.remove(list.size() - 1);
			}
		}
		return;
	}


	// DP solution
	public List<List<Integer>> combinationSumDP(int[] candidates, int target) {

		Arrays.sort(candidates); // sort candidates to try them in asc order

		List<List<List<Integer>>> dp = new ArrayList<>();

		for (int i = 1; i <= target; i++) { // run through all targets from 1 to t
			List<List<Integer>> newList = new ArrayList<>(); // combs for curr i
			// run through all candidates <= i
			for (int j = 0; j < candidates.length && candidates[j] <= i; j++) {
				// special case when curr target is equal to curr candidate
				if (i == candidates[j]) {
					newList.add(Arrays.asList(candidates[j]));
				}
				// if current candidate is less than the target use prev results
				else {
					for (List<Integer> l : dp.get(i - candidates[j] - 1)) {
						// make sure it is in ascending order and no duplicates
						if (candidates[j] <= l.get(0)) {
							List cl = new ArrayList<>();
							cl.add(candidates[j]);
							cl.addAll(l);
							newList.add(cl);
						}
					}
				}
			}
			dp.add(newList);
		}
		return dp.get(target - 1);
	}
}