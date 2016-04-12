/*
Given a triangle, find the minimum path sum from top to bottom. 
Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Anaylsis:
	Declare pathSum array with length of triangle size(). For triangle, 
	the bottom row length is equal to the height of triangle, so use 
	pathSum to hold the bottom row's value, then from bottom to up, 
	find minimum path
	
	This is kind of DP solution. Give pathSum array and basic value for last row,
	then calculate from bottom to up, narrow the pathSum and finally we will get 
	the result.
*/

class TriangleMinPathSum
{
	public int minPathSum(ArrayList<ArrayList<Integer>> triangle)
	{
		if(triangle == null || triangle.size() == 0)
			return 0;

		int[] pathSum = new int[triangle.size()];

		int row = triangle.size();
		for(int i = row - 1; i >= 0; i--)
		{
			int col = triangle.get(i).size();
			for(int j = 0; j < col; j++)
			{
				if(i == row - 1)
				{
					//from bottom to up, now current is bottom
					pathSum[j] = triangle.get(i).get(j);
				}
				else
				{
					//if not bottom level, so from previous level wich are store
					//in pathSum find smaller value can access current point, then
					//update it
					pathSum[j] = Math.min(pathSum[j], pathSum[j+1]) + triangle.get(i).get(j);
				}
			}
		}
		return pathSum[0];
	}
}
