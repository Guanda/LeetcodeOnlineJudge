/*
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".

Anaylsis:
	Need a hash table that maps from the remainder to its position of the fractional part. 
	Once you found a repeating remainder, you may enclose the reoccurring fractional part 
	with parentheses by consulting the position from the table.

	The remainder could be zero while doing the division. That means there is no repeating 
	fractional part and you should stop right away.

	Be wary of edge case such as negative fractions and nasty extreme case such as -2147483648 / -1.

*/

class FractionToDecimal
{
	public String fractionToDecimal(int numerator, int denominator)
	{
		if(numerator == 0)
			return "0";
		if(denominator == 0)
			return "";

		String result = "";

		//if the result is negative
		if((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0))
		{
			result += "-";
		}

		//conver all to positive and avoid the negative overflow for 2147483648, change to long
		long num = numerator, den = denominator;
		num = Math.abs(num);
		den = Math.abs(den);

		//the integer part of result
		long rest = num / den;
		result += String.valueOf(rest);

		//to divide no reminder
		long reminder = (num % den) * 10;
		if(reminder == 0)
			return result;

		//decimal part
		HashMap<Long, Integer> map = new HashMap<Long, Integer>();
		result += ".";
		while(reminder != 0)
		{
			//if the reminder existing before, start recurring
			if(map.containsKey(reminder))
			{
				//the position of recurring starts, because we have ".", the value should be start point
				int begin = map.get(reminder);
				String part1 = result.substring(0, begin);
				//the length of result minus begin should be the recurring part number
				String part2 = result.substring(begin, result.length());
				result = part1 + "(" + part2 + ")";
				return result;
			}

			//keep dividing
			map.put(reminder, result.length());
			rest = reminder / den;
			result += String.valueOf(rest);
			reminder = (reminder % den) * 10;
		}
		return result;
	}
}