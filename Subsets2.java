/*
Given a collection of integers that might contain duplicates, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,2], a solution is:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/

class Subsets2 {
	//Method 1: recursive, 更通用.
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();
		if(nums == null) {
			return results;
		}
		if(nums.length == 0) {
			results.add(new ArrayList<Integer>());
			return results;
		}

		Arrays.sort(nums);

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
			//avoid duplicate
            //所有以同样subset开头的集合都continue掉
            if(i > start && nums[i] == nums[i-1]) 
                continue; 

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
	public List<List<Integer>> subsetsWithDup(int[] num) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(num == null)
			return result;

		int len = num.length;
		Arrays.sort(num);
		//use hashset to remove duplicates
		HashSet<List<Integer>> hs = new HashSet<List<Integer>>();
		result.add(new ArrayList<Integer>());

		for(int i = 0; i < len; i++) {
			int lenTmp = result.size();
			for(int j = 0; j < lenTmp; j++) {
				List<Integer> ls = new ArrayList<Integer>(result.get(j));
				ls.add(num[i]);
				if(!hs.contains(ls)) {
					result.add(ls);
					hs.add(ls);
				}
			}
		}
		return result;
	}
}