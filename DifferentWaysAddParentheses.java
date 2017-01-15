/*
Given a string of numbers and operators, return all possible results from 
computing all the different possible ways to group numbers and operators. 
The valid operators are +, - and *.

Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]

Analysis:
	Use Divide-Conquer based on operators

*/

public class DifferentWaysAddParentheses {
	public List<Integer> diffWaysToCompute(String input) {
		List<Integer> result = new ArrayList<Integer>();

		if(input == null || input.length() == 0)
			return result;

		for(int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if(isOperator(c)) {
				List<Integer> a = diffWaysToCompute(input.substring(0, i));
				List<Integer> b = diffWaysToCompute(input.substring(i+1));

				for(int p : a) {
					for(int q : b) {
						result.add(calc(p, q, c));
					}
				}
			}
		}

		//if input is a single number
		if(result.isEmpty()) {
			result.add(Integer.parseInt(input));
		}
		
		return result;
	}

	public boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*';
	}

	public int calc(int a, int b, char c) {
		if(c == '+')
			return a + b;
		else if(c == '-')
			return a - b;
		else if(c == '*')
			return a * b;
		else
			return Integer.MAX_VALUE;
	}
}