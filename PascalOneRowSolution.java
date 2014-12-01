/*
Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?

*/

class PascalOneRowSolution
{
	public ArrayList<Integer> getRow(int rowIndex)
	{
		ArrayList<Integer> res = new ArrayList<Integer>();

		//special case rowIndex = 0
		if(rowIndex == 0)
		{
			res.add(1);
			return res;
		}

		//for the first row
		res.add(1);

		for(int i = 1; i <= rowIndex; i++)
		{
			res.add(0, 1);
			for(int j = 1; j < i; j++)
			{
				res.set(j, res.get(j) + res.get(j+1));
			}
		}
		return res;
	}
}