/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

*/

class ValidParentheses
{
	public boolean isValid(String s)
	{
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i < s.length(); i++)
		{
			char ch = s.charAt(i);
			if(ch == '(' || ch == '{' || ch == '[')
				stack.push(ch);
			if(ch == ')')
			{
				if(stack.isEmpty() || stack.peek() != '(')
					return false;
				stack.pop();
			}
			if(ch == '}')
			{
				if(stack.isEmpty() || stack.peek() != '{')
					return false;
				stack.pop();
			}
			if(ch == ']')
			{
				if(stack.isEmpty() || stack.peek() != '[')
					return false;
				stack.pop();
			}
		}
		return stack.isEmpty();
	}
}