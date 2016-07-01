/*
Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.

Analysis:
In case of very large number, so we cannot use revert to integer to calculate.
Classic add-with-carry problem.

*/

class PlusOne
{
	public int[] plusOne(int[] digits)
	{
		int carry = 1;
		int[] result = new int[digits.length];
		for(int i = digits.length - 1; i >= 0; i--)
		{
			int val = digits[i] + carry;
			result[i] = val % 10;
			carry = val / 10;
		}

		//consider the first digit for the result
		if(carry == 1)
		{
			//since all the other digits would be 0, just use default value
			result = new int[digits.length + 1];
			result[0] = 1;
		}
		
		return result;
	}
}
