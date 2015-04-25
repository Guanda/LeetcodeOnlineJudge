/*
The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the 
sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.

Analysis:

To solve this problem, we need to look at the gray code sequence. Assuming n = 3, we have:
000
001
011
010
110
111
101
100
We can see that the last two digits of 4 codes at the bottom is just the descending sequence 
of the first 4 codes. The first 4 codes are 0, 1, 3, 2. So, we can easily get the last 4 
codes: 2 + 4, 3 + 4, 1 + 4, 0 + 4, which is 6, 7, 5, 4. We can keep doing this until we reach n digits.

*/

class GrayCode
{
	public List<Integer> grayCode(int n)
	{
		List<Integer> result = new ArrayList<Integer>();
		result.add(0);
		for(int i = 0; i < n; i++)
		{
			int size = result.size();
			for(int j = size-1; j >= 0; j--)
			{
				result.add(result.get(j) + size);
			}
		}
		return result;
	}
}