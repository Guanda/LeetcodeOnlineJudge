/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"

Analysis:
	Use recursive approach. For each recursive call, there are 2 cases:

	1. Number of "(" larger than number of ")" -> in this case, we can add either "(" or ")" in current recursive call
	2. Number of "(" equal to number of ")" -> in this case, only "(" can be added.

	Note that there is no case of number of "(" less then number of ")", because when they are equal, 
	we always add "(" (case 2).
*/
public class GenerateParentheses {	
	public List<String> generateParentheses(int n) {
		List<String> result = new ArrayList<String>();

		if(n == 0) {
			result.add("");
			return result;
		}
		helper(n, 0, 0, result, "");
		return result;
	}

	private void helper(int n, int left, int right, List<String> result, String curr) {
		//exit situation, left == n
        if(left == n && right == n) {
            result.add(curr);
            return;
        }
        
        if(left < n) {
            helper(n, result, left + 1, right, curr + "(");
        }
        if(right < n && left > right) {
            helper(n, result, left, right + 1, curr + ")");
        }
	}
}