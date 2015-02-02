/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"

Analysis:

Use recursive approach. For each recursive call, there are 2 cases:

1. Number of "(" larger than number of ")" -> in this case, we can add either "(" or ")" in current recursive call
2. Number of "(" equal to number of ")" -> in this case, only "(" can be added.

Note that there is no case of number of "(" less then number of ")", because when they are equal, we always add "(" (case 2).

*/

public class GenerateParentheses
{	
	public ArrayList<String> generateParentheses(int n)
	{
		ArrayList<String> result = new ArrayList<String>();

		if(n == 0)
		{
			result.add("");
			return result;
		}
		helper(n, 0, 0, result, "");
		return result;
	}

	private void helper(int n, int left, int right, ArrayList<String> result, String tmp)
	{
		//exit situation, left == n
		if(left == n)
		{
			for(int i = 0; i < n - right; i++)
			{
				tmp = tmp + ")";
			}
			result.add(tmp);
			return;
		}

		//case 1: left > right
		if(left > right)
		{
			helper(n, left+1, right, result, tmp+"(");
			helper(n, left, right+1, result, tmp+")");
		}

		//case 2: left == right
		else
		{
			helper(n, left+1, right, result, tmp+"(");
		}
	}
}