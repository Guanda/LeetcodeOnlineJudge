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

class Subsets2
{
	public List<List<Integer>> subsetsWithDup(int[] num)
	{
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(num == null)
			return result;

		int len = num.length;
		Arrays.sort(num);
		//use hashset to remove duplicates
		HashSet<List<Integer>> hs = new HashSet<List<Integer>>();
		result.add(new ArrayList<Integer>());

		for(int i = 0; i < len; i++)
		{
			int lenTmp = result.size();
			for(int j = 0; j < lenTmp; j++)
			{
				List<Integer> ls = new ArrayList<Integer>(result.get(j));
				ls.add(num[i]);
				if(!hs.contains(ls))
				{
					result.add(ls);
					hs.add(ls);
				}
			}
		}
		return result;
	}
}