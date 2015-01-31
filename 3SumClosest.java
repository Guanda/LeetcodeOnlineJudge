/*
Given an array S of n integers, find three integers in S such that the sum 
is closest to a given number, target. Return the sum of the three integers. 
You may assume that each input would have exactly one solution.

Analysis:
Just similar with 3 sum problem, the only difference is remember min minus every time.

*/

class 3SumClosest
{
	public int 3SumClosest(int[] num, int target)
	{
		int min = Integer.MAX_VALUE;
		int result = 0;

		Arrays.sort(num);

		for(int i = 0; i < num.length; i++)
		{
			int j = i + 1;
			int k = num.length - 1;
			while(j < k)
			{
				int sum = num[i] + num[j] + num[k];
				int diff = Math.abs(sum - target);

				if(diff == 0)
					return sum;
				if(diff < min)
				{
					min = diff;
					result = sum;
				}
				if(sum <= target)
				{
					j++;
				}
				else
				{
					k--;
				}
			}
		}
		return result;
	}
}