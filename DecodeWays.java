/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.

Anaylsis:
	
Suppose we have an array of letters, A[1..n], and we already know the number of ways to decode 
A[1], A[1..2], A[1..i-1], how many ways to decode A[1..i]?

If A[i] is '0' and A[i-1] is neither '1' nor '2', invalid message;
If A[i] is '0' and A[i-1] is '1' or '2', the number of ways is the number of ways of A[1..i-2];
If A[i] can be combined with prior letter, the number of ways is the number of ways of A[1..i-1] + A[1..i-2];
Otherwise, it is equal to the number of ways of A[1..i-1].

*/

class DecodeWays
{
	private boolean isTwoDigitCode(char a, char b)
	{
		return (a == '1' || a == '2' && b >= '0' && b <= '6');
	}

	//time: O(n), space: O(n)
	public int numDecodings(String s)
	{
		int len = s.length();

		int[] count = new int[len + 1];
		count[0] = (len <= 0) ? 0 : 1;
		for(int i = 1; i <= len; i++)
		{
			char c = s.charAt(i-1);
			//invalid: non-digit
			if(c < '0' || c > '9')
				return 0;

			if(c == '0')
			{
				//invalid: start with 0 or previous digit is not 1 nor 2
				if(i - 1 == 0 || (s.charAt(i-2)) != '1' && s.charAt(i-2) != '2')
					return 0;
				count[i] = count[i-2];
			}
			else if(i-1 >0 && isTwoDigitCode(s.charAt(i-2), c))
			{
				count[i] = count[i-1] + count[i-2];
			}
			else
			{
				count[i] = count[i-1];
			}
		}
		return count[len];
	}

	//improve the above solution to save space
	//Notice that in above algorithm, we only need previous two counts, count[i-1] and count[i-2]. 
	public int numDecodings(String s)
	{
		int len = s.length();

		int w1 = (len <= 0) ? 0 : 1, w2 = w1;
		for(int i = 1; i <= len; i++)
		{
			char c = s.charAt(i-1);
			//w1 = count[i-2], tmp = w2 = count[i-1]
			int tmp = w2;

			//update w2 to be count[i]
			if(c == '0')
				w2 = 0;
			if(i-1>0 && isTwoDigitCode(s.charAt(i-2), c))
			{
				w2 += w1;
			}
			//invalid
			if(w2 == 0)
				return 0;

			//set w1 = tmp = count[i-1], i.e. A[(i+1) - 2]
			w1 = tmp;
		}
		return w2;
	}
}