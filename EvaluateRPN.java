/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

Analysis:
	Use stack
*/

class EvaluateRPN {
	public int evalRPN(String[] tokens) {
		int a, b;
		Stack<Integer> S = new Stack<Integer>();
		for(String s : tokens) {
			if(s.equals("+")) {
				S.push(S.pop() + S.pop());
			}
			else if(s.equals("-")) {
				b = S.pop();
				a = S.pop();
				S.push(a - b);
			}
			else if(s.equals("*")) {
				S.push(S.pop() * S.pop());
			}
			else if(s.equals("/")){
				b = S.pop();
				a = S.pop();
				S.push(a / b);
			}
			else {
				S.push(Integer.parseInt(s));
			}
		}
		return S.pop();
	}
}