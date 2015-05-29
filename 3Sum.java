/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)

Analysis:

Consider the 2Sum question first: sort array and then set two pointers, one from start and 
then other one from the last. time O(n)

So based on 2Sum problem, we can figure out it by using time O(n^2).

*/

class 3Sum
{
	public ArrayList<ArrayList<Integer>> 3sum(int[] num)
	{
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		HashSet<ArrayList<Integer>> hashSet = new HashSet<ArrayList<Integer>>();
		if(num.length < 3)
			return result;

		Arrays.sort(num);
		
		int n = num.length;

		//i<n-2 because we have at lest three elements in num[]
		for(int i = 0; i < n-2 && num[i] <= 0; i++)
		{
			//skip the same int in the loop
			if (i > 0 && num[i] == num[i-1]) 
				continue;

			int j = i + 1;
			int k = n - 1;
			while(j < k)
			{
				int sum_two = num[i] + num[j];
				if(sum_two + num[k] < 0)
				{
					j++;
				}
				else if(sum_two + num[k] > 0)
				{
					k--;
				}
				else
				{
					ArrayList<Integer> arr = new ArrayList<Integer>();
					arr.add(num[i]);
					arr.add(num[j]);
					arr.add(num[k]);

					//only add the unique one
					if(!hashSet.contains(arr))
					{
						hashSet.add(arr);
						result.add(arr);
					}
					j++;
					k--;
				}
			}
		}

		return result;
	}
}