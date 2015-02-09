/*
Given two numbers represented as strings, return multiplication of the numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.

Analysis:
1. Since the numbers can be arbitrarily large, we need covert two numbers to array of integer and then calculate.

2. Think out of box. 
	digit 0 of result is n1[0]*n2[0]
	digit 1 of result is n1[0]*n2[1] + n1[1]*n2[0]
	digit i of result is sum_(k=0~i) {n1[k]*n2[i-k]}
   Based on this rule, do a better calculate.

*/

public class MultiplyStrings
{
	//Method 1:
	public String multiplyStrings(String num1, String num2)
	{
		if(num1.equals("0") || num2.equals("0"))
			return "0";

		int l1 = num1.length();
		int l2 = num2.length();
		int[] n1 = new int[l1];
		int[] n2 = new int[l2];
		int[] result = new int[l1+l2];

		//convert num1 to number array
		for(int i = 0; i < l1; i++)
		{
			n1[i] = num1.charAt(i) - '0';
		}

		//convert num2 to number array
		for(int i = 0; i < l2; i++)
		{
			n2[i] = num2.charAt(i) - '0';
		}

		//multiply into number array
		for(int i = 0; i < l1; i++)
		{
			for(int j = 0; j < l2; j++)
			{
				result[i+j+1] = result[i+j+1] + n1[i]*n2[j];
			}
		}

		//convert back to string
		StringBuilder sb = new StringBuilder();
		for(int k=l1+l2-1; k >= 0; k--)
		{
			sb.append((char)(result[k] % 10 + '0'));
			if(k > 0)
			{
				result[k-1] = result[k-1] + result[k] / 10;
			}
		}

		//trim 0 prefix 0's
		int count = sb.charAt(sb.length() - 1) == '0' ? 1 : 0;
		String s = sb.reverse().substring(count, sb.length());
		return (s.isEmpty()) ? "0" : s;
	}

	//Method 2:
	public String multiplyStrings(String num1, String num2)
	{
		if(num1.equals("0") || num2.equals("0"))
			return "0";

		int l1 = num1.length();
		int l2 = num2.length();
		int[] n1 = new int[l1];
		int[] n2 = new int[l2];

		//convert num1 to number array reversely
		for(int i = 0; i < l1; i++)
		{
			n1[l1-i-1] = num1.charAt(i) - '0';
		}

		//convert num2 to number array reversely
		for(int i = 0; i < l2; i++)
		{
			n2[l2-i-1] = num2.charAt(i) - '0';
		}

		//multiply into digit by digit
		int sum = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < l1+l2-1; i++)
		{
			for(int j = 0; j <= i; j++)
			{
				if(j < l1 && i-j < l2)
				{
					sum = sum + n1[j] * n2[i-j];
				}
			}
			sb.append((char)(sum % 10 + '0'));
			sum = sum / 10;
		}
		if(sum > 0)
			sb.append((char)(sum + '0'));
		String s = sb.reverse().toString();
		return (s.isEmpty()) ? "0" : s;
	}
}