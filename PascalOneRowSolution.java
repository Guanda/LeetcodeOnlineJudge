/*
Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?

Analysis:
Since each row of the Pascal's triangle is constructed based on 
previous row. We need to keep tracking of previous row. But we 
only need keep one row rather all of them.

*/

class PascalOneRowSolution
{
	public ArrayList<Integer> getRow(int rowIndex)
	{
		ArrayList<Integer> row = new ArrayList<Integer>();

		for(int i = 0; i <= rowIndex; i++)
		{
			ArrayList<Integer> preRow = row;
			row = new ArrayList<Integer>();

			//first row
			if(i == 0)
			{
				row.add(1);
			}
			else
			{
				for(int j = 0; j <= i; j++)
				{
					//this is for the current row number calculation
					int val = ((j > 0) ? preRow.get(j-1) : 0) + ((j < preRow.size()) ? preRow.get(j) : 0);
					row.add(val);
				}
			}
		}
		return row;
	}
}