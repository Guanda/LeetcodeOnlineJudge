/*
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.

Anaylsis:
	No matter what m and n is, the result should be the common left most header!!

*/

class BitwiseANDNumbersRange
{
	public int rangeBitwiseAnd(int m, int n)
	{
		int c = 0;
		while(m != n)
		{
			m >>= 1;
			n >>= 1;
			c++;
		}
		return n << c;
	}
}