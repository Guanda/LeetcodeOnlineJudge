/*
Given a set of distinct integers, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,3], a solution is:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
Analysis:
	Based on S[n-1], for every element in it, add new S[n]
	Use DFS, remember the key word in the question: "all" which means use DFS
*/

class Subsets {
	//Method 1: recursive, 更通用.
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();
		if(nums == null) {
			return results;
		}
		if(nums.length == 0) {
			results.add(new ArrayList<Integer>());
			return results;
		}

		helper(new ArrayList<Integer>(), nums, 0, results);
		return results;
	}

	//递归三要素
	//1. 递归的定义：在nums中找到所有以subset开头的集合，并放到results
	private void helper(List<Integer> subset, int[] nums, int start, List<List<Integer>> results) {
		//2. 递归的拆解
		//deep copy, 不能只用add放进subset的reference，以后的修改会影响到reference
		results.add(new ArrayList<Integer>(subset));

		for(int i = start; i < nums.length; i++) {
			subset.add(nums[i]);
			helper(subset, nums, i+1, results);

			//回溯的过程，之前加进去的需要去掉，以备下次添加新的element
			//例如从[1,2]到[1], 以备下一轮变[1,3]
			subset.remove(subset.size() - 1);
		}

		//3. 递归的出口
		//return;
	}


	//Method 2: iteration
	public List<List<Integer>> subsets(int[] S) {
		if(S == null)
			return null;

		Arrays.sort(S);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for(int i = 0; i < S.length; i++) {
			List<List<Integer>> tmp = new ArrayList<List<Integer>>();

			//get sets that are already in result
			for(List<Integer> a : result) {
				tmp.add(new ArrayList<Integer>(a));
			}

			//add S[i] to existing sets
			for(List<Integer> a : tmp) {
				a.add(S[i]);
			}

			//add S[i] only as a set
			List<Integer> single = new ArrayList<Integer>();
			single.add(S[i]);
			tmp.add(single);

			result.addAll(tmp);
		}

		//add empty set
		result.add(new ArrayList<Integer>());

		return result;
	}
}
