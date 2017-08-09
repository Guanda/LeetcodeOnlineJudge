/*
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element. (This means * cannot be the first char)

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true

Analysis:

Method 1:
	First of all, this is one of the most difficulty problems. It is hard to think through all 
	different cases. The problem should be simplified to handle 2 basic cases:

	the second char of pattern is not "*"
	the second char of pattern is  "*"

	For the 1st case, if the first char of pattern is not ".", the first char of pattern and 
	string should be the same. Then continue to match the remaining part.

	For the 2nd case, if the first char of pattern is "." or first char of pattern == the first 
	char of string, continue to match the remaining part. Remember to consider the * can represent
	0 element.

Method 2:
	DP solution

 -- dp[s.length() + 1][p.length() + 1], where dp[i][j] means the first i characters from string 
    s matches the first j characters in string p. 
 -- Initial state: dp[0][0] = true, e.g. "" -> "", true. 
                        dp[i][0] = false, i >= 1, any string cannot match a empty string 
                        dp[0][i], if (p.charAt(j) == '*'), dp[0][j] = dp[0][j - 2] 

 -- Transit function: 
      -- If p.charAt(j) != '*'. Then IF s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.'. 
            -- dp[i][j] = dp[i - 1][j - 1];
      -- Else  // p.charAt(j - 1) == "*"
           -- If s.charAt(i - 1) != p.charAt(j - 2) && p.charAt(j - 2) != '.' 
               Then dp[i][j] = dp[i][j - 2] // zero matched, e.g. s = acdd, p = acb*dd. 
           -- Else 
                Then dp[i][j] = dp[i][j - 2]  ||  // zero matched
                                dp[i][j - 1] || // 1 matched
                                dp[i - 1][j] // 2+ matched
*/

class RegularExpressionMatching {
	//method 1
	public boolean isMatch(String s, String p) {
		//base case
		if(p.length() == 0)
			return s.length() == 0;

		//special case and the case that second charactor is not * can combine together
		if(p.length() == 1 || p.charAt(1) != '*') {
			if(s.length() < 1)
				return false;
			else if(s.charAt(0) != p.charAt(0) && p.charAt(0) != '.')
				return false;
			else
				return isMatch(s.substring(1), p.substring(1));
		}
		//the case that when second charactor is *, complex case
		else {
			//case 2.1: * stands for 0 element
			if(isMatch(s, p.substring(2)))
				return true;

			//case 2.2: * stands for 1 or more elements
			//so try every substring
			int i = 0;
			while(i < s.length() && (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.')) {
				if(isMatch(s.substring(i+1), p.substring(2)))
					return true;
				i++;
			}
			return false;
		}
	}

	//method 2
	public boolean isMatchBetter(String s, String p) {
		if(p == null || p.length() == 0)
			return s == null || s.length() == 0;

		int rows = s.length();
		int cols = p.length();
		boolean[][] dp = new boolean[rows+1][cols+1];
		dp[0][0] = true;

		//initial dp[0][j], remember the special case dp[0][1]
		dp[0][1] = false;
		for(int j = 2; j <= cols; j++) {
			if(p.charAt(j-1) == '*')
				dp[0][j] = dp[0][j-2];
		}

		for(int i = 1; i <= rows; i++) {
			for(int j = 1; j <= cols; j++) {
				char sChar = s.charAt(i-1);
				char pChar = p.charAt(j-1);
				if(pChar != '*') {
					if(pChar == sChar || pChar == '.')
						dp[i][j] = dp[i-1][j-1];
				} 
				else {
					//in case that dp out of boundry
					if(j == 1)
						return false;

					if(sChar != p.charAt(j-2) && p.charAt(j-2) != '.') {
						dp[i][j] = dp[i][j-2];
					} 
					else {
						dp[i][j] = dp[i][j-2] || dp[i][j-1] || dp[i-1][j];
					}
				}
			}
		}
		return dp[rows][cols];
	}
}
