/*
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false

Analysis:
	DP solution in two cases:
		1) p[j-1] == s[i-1] || p[j-1] == '?'：dp[i][j] = dp[i-1][j-1]
		2) p[j-1] == '*'：
			1. 匹配0个字符：dp[i][j] = dp[i][j-1]
			2. 匹配1个字符：dp[i][j] = dp[i-1][j-1]
			3. 匹配多个字符：dp[i][j] = dp[i-1][j]

*/

class WildcardMatching {
	public boolean isMatch(String s, String p) {
		int lenS = s.length();
		int lenP = p.length();

		boolean[][] dp = new boolean[lenS+1][lenP+1];

		//base cases:
		dp[0][0] = true;
		for(int i = 1; i <= lenS; i++) {
		    dp[i][0] = false;
		}
		for(int j = 1; j <= lenP; j++) {
		    dp[0][j] = p.charAt(j-1) == '*' && dp[0][j-1];
		}

		for(int i = 1; i <= lenS; i++) {
			for(int j = 1; j <= lenP; j++) {
			    if(p.charAt(j-1) != '*') {
			        dp[i][j] = dp[i-1][j-1] && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?');
			    }
				else {
					dp[i][j] = dp[i][j-1] || dp[i-1][j-1] || dp[i-1][j];
				}
			}
		}
		return dp[lenS][lenP];
	}
}