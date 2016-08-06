/*
Given a collection of distinct numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

Analysis:
	the basic idea is, to permute n numbers, we can add the nth number into 
	the resulting List<List<Integer>> from the n-1 numbers, in every possible position.

*/

class Permutations {
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		if(nums == null || nums.length == 0) {
			return results;
		}

		//initialize the results
		List<Integer> list = new ArrayList<Integer>();
		list.add(nums[0]);
		results.add(list);

		for(int i = 1; i < nums.length; i++) {
			List<List<Integer>> newResults = new ArrayList<List<Integer>>();
			//the positions are from 0 to i
			for(int j = 0; j <= i; j++) {
				for(List<Integer> l : results) {
					List<Integer> newList = new ArrayList<Integer>(l);
					newList.add(j, nums[i]);
					newResults.add(newList);
				}
			}
			results = newResults;
		}

		return results;
	}

	//method 2, backtracing, worse time complexity than method 1
	public List<List<Integer>> permuteBT(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		backtrack(list, new ArrayList<>(), nums);
		return list; 
	}

	public void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
		if(tempList.size() == nums.length) {
			list.add(new ArrayList<>(tempList));
		}
		else {
			for(int i = 0; i < nums.length; i++) {
				//element already exists, skip
				if(tempList.contains(nums[i]))
					continue;
				tempList.add(nums[i]);
				backtrack(list, tempList, nums);
				tempList.remove(tempList.size() - 1);
			}
		}
	}
}