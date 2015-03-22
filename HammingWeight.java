/*
Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).

For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.

*/

public class HammingWeight
{
	//method 1: use toBinaryString method
	public int hammingWeight(int n)
	{
		String s = Integer.toBinaryString(n);
		int count = 0;

		for(int i = 0; i < s.length(); i++)
		{
			if(s.charAt(i) == '1')
				count++;
		}
		return count;
	}

	//method 2: bit manipulation
	public int hammingWeight2(int n)
	{
		int count = 0;
		while(n != 0)
		{
			count += n & 1;
			n >>= 1;
		}
		return count;
	}

	//method 3: bit manipulation(best solution)
	//iteration times equal 1's times.
	public int hammingWeight3(int n)
	{
		int count = 0;
		while(n != 0)
		{
			n = n & (n-1);
			count++;
		}
		return count;
	}
}