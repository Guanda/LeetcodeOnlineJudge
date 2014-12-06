/*
You are climbing a stair case. It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Analysis:
Let Fn be the number of ways to climb n stairs taking only 1 or 2 steps. 
We know that F1=1 and F2=2. Now, consider Fn for n≥3. The final step will 
be of size 1 or 2, so Fn = Fn−1+Fn−2. This is the Fibonacci recurrence.

And for Fibonacci recurrence we have different ways to figure out. 
Here we have Iteration(DP) solution and recursive solution.
*/

class ClimbStairs
{
	//DP solution
	public int climbStairsIteration(int n)
	{
		if(n < 3)
			return n;

		int p1 = 1; 
		int p2 = 2;
		for(int i = 2; i < n; i++)
		{
			int tmp = p2;
			p2 = p1 + p2;
			p1 = tmp;
		}

		return p2;
	}

	//recursive solution
	public int climbStairsRecursive(int n)
	{
		if(n < 3)
			return n;

		return climbStairsRecursive(n-1) + climbStairsRecursive(n-2);
	}
}