/*
Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), 
return 964176192 (represented in binary as 00111001011110000010100101000000).

Analysis:

The idea of using mask to check 1 bit each time, using AND operation.

Mask moves 1 bit each time using << operation.
Mask can be computed and saved before to speed up the reverse function.

E.g. 
iteration 1:  mask = 0000...00001,  then mask & n  = 0
iteration 2:  mask = 0000...00010, then mask & n  = 0
iteration 3:  mask = 0000...00100,  then mask & n  = 1
iteration 4:  mask = 0000...01000, then mask & n  = 1
...
iteration 32:  mask = 1000...00000, then mask & n  = 0

In this way, binary bits can be obtained from 32 iterations.
Reverse thus becomes pretty easy when using this looping.

*/

class ReverseBits
{
	public int reverseBits(int n) {
		int result = 0;
		for(int i = 0; i < 32; i++) {
			result += n & 1;
			//doing this just like operate as mask
			n >>>= 1;
			if(i != 31) {
				result <<= 1;
			}
		}
		return result;
	}

	//no bits manipulation solution
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