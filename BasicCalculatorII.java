/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . 
The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5

*/
class BasicCalculatorII {
	public int calculate(String s) {
		if(s == null || s.length() == 0) {
			return 0;
		}
		Stack<Integer> stack = new Stack<Integer>();
		int number = 0;
		int result = 0;
		char sign = '+';
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c >= '0' && c <= '9') {
				number = number * 10 + (c - '0');
			}
			//not use else if is beacuse the last char will be number and we should still go though this logic
			if((c < '0' || c > '9') && c != ' ' || i == s.length()-1) {
				if(sign == '+') {
					stack.push(number);
				}
				else if(sign == '-') {
					stack.push(-number);
				}
				else if(sign == '*') {
					stack.push(stack.pop() * number);
				}
				else if(sign == '/') {
					stack.push(stack.pop() / number);
				}
				sign = c;
				number = 0;
			}
		}
		for(int i : stack) {
			result += i;
		}
		return result;
	}
}