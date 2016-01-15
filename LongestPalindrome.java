/*
Given a string S, find the longest palindromic substring in S. 
You may assume that the maximum length of S is 1000, and there 
exists one unique longest palindromic substring.

Analysis:

1. Dynamic programming solution, O(N2) time and O(N2) space:
		Consider the case “ababa”. If we already knew that “bab” is a palindrome, it is obvious that “ababa” must be a palindrome 
		since the two left and right end letters are the same. P[i,j] means substring start from i and end with j is palindrome.
		So: P[i,i] is true and P[i,i+1] equals (s[i]==s[i+1]).

		Then we can consider the situation that length is more than 3: P[ i, j ] ← ( P[ i+1, j-1 ] and Si = Sj )

2. Based on the solution above, improve it to O(1) space:
		We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded from its center, and there 
		are only 2N-1 such centers. (N for center is one character and N-1 for center is between two characters)

*/

class LongestPalindome
{
	//method 1, time: O(n2), space: O(n2)
	public String longestPalindrome(String s)
	{
		int n = s.length();
		int maxLen = 1;
		int longestBegin = 0;
		boolean[][] table = new boolean[n][n];

		for(int i = 0; i < n; i++)
			table[i][i] = true;

		// check for sub-string of length 2.
		for(int i = 0; i < n-1; i++)
		{
			if(s.charAt(i) == s.charAt(i+1))
			{
				table[i][i+1] = true;
				longestBegin = i;
				maxLen = 2;
			}
		}

		// Check for lengths greater than 2. len is length of substring
		for(int len = 3; len <= n; len++)
		{
			for(int i = 0; i < n-len+1; i++)
			{
				// Get the ending index of substring from starting index i and length len
				int j = i + len - 1;
				if(s.charAt(i) == s.charAt(j) && table[i+1][j-1])
				{
					table[i][j] = true;
					longestBegin = i;
					maxLen = len;
				}
			}
		}
		return s.substring(longestBegin, longestBegin+maxLen);
	}

	//method 2, time: O(n2), space: O(1)
	public String longestPalindrome2(String s)
	{
		int n = s.length();
		if(n == 0)
			return "";
		String longest = s.substring(0, 1);	//single char is palindrome

		for(int i = 0; i < n-1; i++)
		{
			//center is one char
			String s1 = expandAroundCenter(s, i, i);
			if(s1.length() > longest.length())
				longest = s1;

			//center is between two chars
			String s2 = expandAroundCenter(s, i, i+1);
			if(s2.length() > longest.length())
				longest = s2;
		}
		return longest;
	}

	public String expandAroundCenter(String s, int c1, int c2)
	{
		int n = s.length();
		while(c1 >= 0 && c2 <= n-1 && s.charAt(c1) == s.charAt(c2))
		{
			c1--;
			c2++;
		}
		return s.substring(c1+1, c2);
	}


}