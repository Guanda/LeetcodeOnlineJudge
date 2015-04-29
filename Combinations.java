/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]

Analysis:

Backtracking: 
	Given n=4 and k=2, a solution is [[4,1], [4,2], [4,3], [3,1], [3,2], [2,1]].
	Generalizing to all n's and k's, we have a set of
	Append n to the result of combine(n-1, k-1);
	Append n-1 to the result of combine(n-2, k-2);
	... ...
	Append k-1 to the result of combine(n-k+1, 1), i.e. (1, 2, ..., k).

*/

class Combinations
{
	public List<List<Integer>> combine(int n, int k)
	{
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(k == 1)
		{
			for(int i = 1; i <= n; i++)
			{
				List<Integer> tmp = new ArrayList<Integer>();
				tmp.add(i);
				result.add(tmp);
			}
			return result;
		}

		for(int i = n; i >= k; i--)
		{
			List<List<Integer>> res = combine(i-1, k-1);
			for(List<Integer> tmp : res)
			{
				tmp.add(i);
				result.add(tmp);
			}
		}
		return result;
	}
}