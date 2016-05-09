/*
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
Find all unique quadruplets in the array which gives the sum of target.

Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.
    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)

*/

public class FourSum
{
	public ArrayList<ArrayList<Integer>> 4Sum(int[] num, int target)
	{
		Arrays.sort(num);

		HashSet<ArrayList<Integer>> hashSet = new HashSet<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		for(int i = 0; i < num.length; i++)
		{
			for(int j = i + 1; j < num.length; j++)
			{
				int k = j + 1;
				int l = num.length - 1;
				while(k < l)
				{
					int sum = num[i] + num[j] + num[k] + num[l];
					if(sum < target)
					{
						k++;
					}
					else if(sum > target)
					{
						l--;
					}
					else
					{
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(num[i]);
						tmp.add(num[j]);
						tmp.add(num[k]);
						tmp.add(num[l]);
						if(!hashSet.contains(tmp))
						{
							hashSet.add(tmp);
							result.add(tmp);
						}
						k++;
						l--;	
					}

				}
			}
		}
		return result;
	}
}