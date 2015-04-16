/*
Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), 
return 964176192 (represented in binary as 00111001011110000010100101000000).

*/

class ReverseBits
{
	public int reverseBits(int n)
	{
		String s = Integer.toBinaryString(n);
		char[] cArray = s.toCharArray();
		for(int i = 0; i <= s.length() / 2; i++)
		{
			char tmp = cArray[i];
			cArray[i] = cArray[s.length() - i - 1];
			cArray[s.length() - i - 1] = tmp;
		}

		int result = Integer.parseInt(cArray.toString(), 2);
		return result;
	}
}