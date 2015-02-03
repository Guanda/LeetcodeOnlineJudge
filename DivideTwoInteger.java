/*
Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.

*/

public class DivideTwoInteger
{
	public int divideTwoInteger(int dividend, int divisor)
	{
		long p = Math.abs(dividend);
		long q = Math.abs(divisor);
		int result = 0

		while(p >= q)
		{
			int counter = 0;
			while(p >= (q << 1))
			{
				counter++;
			}
			result += 1 << (counter - 1);
			p -= q << (counter - 1);
		}

		if((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0))
		{
			return result;
		}
		else
		{
			return -result;
		}
	}
}