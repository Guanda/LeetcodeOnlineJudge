/*
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where 
the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6] 

Time Complexity:
	// O(k * 2^n) time:
    // 每个解的长度平均为k, copy list的时间复杂度是O(k)
    // 解空间:
    // 因为元素只能用一次, 所以对于长度是k的解, 解的个数最多是C(n,k)个, 而一般情况下, 
    // 解的个数远小于C(n,k), 那么问题来了, 对于长度为k的解, worst case是什么？
    // int[] arr = {1,1,1,1,1,1,1,1,1,1}  target = 2 这时候解的个数才达到C(n,k),
    // 注意: 即便在ret.add之前要去重, 我们也是在找到了可行解之后再检查是否是重复解, 所以
    //      解空间树是包含重复解的, 所以时间复杂度也包括这些重复解
    //
    // 还有一点是, 题解的长度并不是固定的, 也就是k可能有多个, 所以可能是C(n,0) ~ C(n,n)
    // 中的多个之和, 而我们知道C(n,0) + C(n,1) + C(n,2) + ... C(n,n) = 2^n
    // 所以可以把时间复杂度算成O(k * 2^n)
*/

public class CombinationSum2 {
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
				if (i > start && candidates[i] == candidates[i-1]) 
					continue;
				list.add(candidates[i]);
				combinationSumHelper(candidates, target-candidates[i], i+1, list, result);
				list.remove(list.size() - 1);
			}
		}
		return;
	}
}