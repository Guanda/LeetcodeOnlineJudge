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

*/

class Subsets
{
	public List<List<Integer>> subsets(int[] S)
	{
		if(S == null)
			return null;

		Arrays.sort(S);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for(int i = 0; i < S.length; i++)
		{
			List<List<Integer>> tmp = new ArrayList<List<Integer>>();

			//get sets that are already in result
			for(List<Integer> a : result)
			{
				tmp.add(new ArrayList<Integer>(a));
			}

			//add S[i] to existing sets
			for(List<Integer> a : tmp)
			{
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
