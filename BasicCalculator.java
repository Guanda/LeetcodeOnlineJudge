/*
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, 
non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:
"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23

Analysis:
	Start from +1 sign and scan s from left to right;
	if c == digit: This number = Last digit * 10 + This digit;
	if c == '+': Add num to result before this sign; This sign = Last context sign * 1; clear num;
	if c == '-': Add num to result before this sign; This sign = Last context sign * -1; clear num;
	if c == '(': Push this context sign to stack;
	if c == ')': Pop this context and we come back to last context;
	Add the last num. This is because we only add number after '+' / '-'.
*/
class BasicCalculator {
	public int calculate(String s) {
		if(s == null || s.length() == 0)
			return 0;
		Stack<Integer> stack = new Stack<Integer>();
		int result = 0;
		int number = 0;
		int sign = 1;
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c >= '0' && c <= '9') {
				number = number * 10 + (c - '0');
			}
			else if(c == '+'){
				result += sign * number;
				number = 0;
				sign = 1;
			}
			else if(c == '-') {
				result += sign * number;
				number = 0;
				sign = -1;
			}
			else if(c == '(') {
				//we push the result first, then sign;
				stack.push(result);
				stack.push(sign);
				//reset the sign and result for the value in the parenthesis
				result = 0;
				sign = 1;
			}
			else if(c == ')') {
				result += sign * number;
				number = 0;
				result *= stack.pop();
				result += stack.pop();
			}
		}
		if(number != 0) {
			result += sign * number;
		}

		return result;
	}
}