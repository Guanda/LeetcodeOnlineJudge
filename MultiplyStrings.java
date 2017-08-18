/*
Given two numbers represented as strings, return multiplication of the numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.

Analysis:
	Start from right to left, perform multiplication on every pair of digits, and add them together. 
	Let's draw the process! From the following draft, we can immediately conclude:
	 "num1[i] * num2[j]"" will be placed at indices "i + j" and "i + j + 1"

	Reference: https://discuss.leetcode.com/topic/30508/easiest-java-solution-with-graph-explanation
*/

public class MultiplyStrings {
	public String multiplyStrings(String num1, String num2) {
		if(num1.equals("0") || num2.equals("0"))
			return "0";

        int m = num1.length();
        int n = num2.length();
        int[] result = new int[m+n];
        
        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int sum = mul + result[i + j + 1];
                result[i + j + 1] = sum % 10;
                result[i + j] += sum / 10;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < result.length; i++) {
            if(sb.length() == 0 && result[i] == 0) {
                continue;
            }
            sb.append(result[i]);
        }
        
        return sb.length() == 0 ? "0" : sb.toString();
	}
}