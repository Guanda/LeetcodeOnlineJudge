/*
Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.

Analysis:
	Shift can be used to solve this problem. We shift the divisor left until it 
	just smaller than dividend but if we keep shifting one more bit, it’s larger 
	than dividend. Then we can add the shifted value to the result and subtract 
	the shifted value from dividend. Keep doing this until dividend is smaller 
	than divisor. In fact, every integer can be represented by a set of base 2 so 
	that shifting can be used.

*/

public class DivideTwoInteger
{
	public int divideTwoInteger(int dividend, int divisor)
	{
		long p = Math.abs((long)dividend);
		long q = Math.abs((long)divisor);
		int result = 0;

		while(p >= q)
		{
			int counter = 0;
			while(p >= (q << counter))
			{
				counter++;
			}
			result += 1 << (counter - 1);
			p -= q << (counter - 1);
		}

		//take care of the boundry case
		if(dividend == Integer.MIN_VALUE && divisor == -1)
		{
			return Integer.MAX_VALUE;
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