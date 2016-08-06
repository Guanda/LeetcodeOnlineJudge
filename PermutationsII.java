/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].

Analysis:

Method 1:
	Insert element one by one to different positions for permutation.
	当只有1时候：[1]
	当加入2以后：[2, 1], [1, 2]
	当加入3以后：[3, 2, 1], [2, 3, 1], [2, 1, 3], [3, 1, 2], [1, 3, 2], [1, 2, 3]
	前3个permutation分别对应将3插入[2, 1]的0, 1, 2的位置。同理后3个为插入[1, 2]的。因此可以用逐个插入数字来构造所有permutations。

	Use set to maintain

Method 2:
	For each number in the array, swap it with every element after it. 
	To avoid duplicate, we need to check the existing sequence first.

Method 3:
	recursive solution, similar to method 2

*/

class PermutationsII {
	//Method 1:
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> currentResults = new ArrayList<List<Integer>>();
		if(nums == null || nums.length == 0)
			return currentResults;

		currentResults.add(new ArrayList<Integer>());

		for(int i = 0; i < nums.length; i++) {
			Set<List<Integer>> set = new HashSet<List<Integer>>();
			for(List<Integer> l: currentResults) {
				for(int k = 0; k < l.size() + 1; k++) {
					l.add(k, nums[i]);
					List<Integer> list = new ArrayList<Integer>(l);
					l.remove(k);
					set.add(list);
				}
			}

			for(List<Integer> tmp: set) {
			    currentResults.add(tmp);
			}
		}

		List<List<Integer>> results = new ArrayList<List<Integer>>();
		for(List<Integer> tmpL: currentResults) {
			if(tmpL.size() == nums.length) {
				results.add(tmpL);
			}
		}

		return results;
	}


	//Method 2:
	public List<List<Integer>> permuteUniqueBetter(int[] nums) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		helper(nums, 0, results);

		return results;
	}

	public void helper(int[] nums, int start, List<List<Integer>> results) {
		if(start == nums.length) {
			List<Integer> list = new ArrayList<Integer>();
			for(int i = 0; i < nums.length; i++) {
				list.add(nums[i]);
			}
			results.add(list);
		}

		for(int i = start; i < nums.length; i++) {
			if(!containsDuplicate(nums, start, i)) {
				swap(nums, start, i);
				helper(nums, start+1, results);
				swap(nums, start, i);
			}
		}
	}

	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	private boolean containsDuplicate(int[] arr, int start, int end) {
		for (int i = start; i <= end-1; i++) {
			if (arr[i] == arr[end]) {
				return true;
			}
		}
		return false;
	}


	//Method 3: recursive
	public List<List<Integer>> permuteUniqueBest(int[] num) {
	        Arrays.sort(num);
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        List<Integer> current = new ArrayList<Integer>();
	        boolean[] visited = new boolean[num.length];
	        permute(result, current, num, visited);
	        return result;
	}

    private void permute(List<List<Integer>> result, List<Integer> current, int[] num, boolean[] visited) {
        if (current.size() == num.length) {
            result.add(new ArrayList<Integer>(current));
            return;
        }
        for (int i=0; i<visited.length; i++) {
            if (!visited[i]) {
                if (i > 0 && num[i] == num[i-1] && visited[i-1]) {
                    return;
                }
                visited[i] = true;
                current.add(num[i]);
                permute(result, current, num, visited);
                current.remove(current.size()-1);
                visited[i] = false;
            }
        }
    }
}
