/*
Given a string containing just the characters '(' and ')', find the length of the longest 
valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

Analysis:
	Using stack to track the index

*/

class LongestValidParentheses {
	public int longestValidParentheses(String s) {
		Stack<Integer> stack = new Stack<Integer>();
		//should give the stack initial value in case there is only one char existing
		stack.push(-1);
		int result = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '(') {
				stack.push(i);
			}
			else {
				//since the current char is ), pop first
				stack.pop();
				if(!stack.isEmpty()) {
					result = Math.max(result, i - stack.peek());
				}
				else {
					stack.push(i);
				}
			}
		}
		return result;
	}
}