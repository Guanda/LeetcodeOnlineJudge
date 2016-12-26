/*
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.

Analysis:

The order in s1 and s2 cannot be changed, so that is called interleaving

Use DP solution:
DP table represents if s3 is interleaving at (i+j)th position when s1 is at ith position, 
and s2 is at jth position. 0th position means empty string.

So if both s1 and s2 is currently empty, s3 is empty too, and it is considered interleaving. 
If only s1 is empty, then if previous s2 position is interleaving and current s2 position 
char is equal to s3 current position char, it is considered interleaving. similar idea applies 
to when s2 is empty. when both s1 and s2 is not empty, then if we arrive i, j from i-1, j, 
then if i-1,j is already interleaving and i and current s3 position equal, it s interleaving. 
If we arrive i,j from i, j-1, then if i, j-1 is already interleaving and j and current s3 position equal. 
It is interleaving.

*/
class InterleavingString {
	public boolean isInterleave(String s1, String s2, String s3) {
		if(s1 == null || s2 == null || s3 == null)
			return false;
		if(s3.length() != s1.length() + s2.length())
			return false;

		boolean[][] valid = new boolean[s1.length()+1][s2.length()+1];
		for(int i = 0; i < s1.length()+1; i++) {
			for(int j = 0; j < s2.length()+1; j++) {
				//s1 and s2 are both empty
				if(i == 0 && j == 0) {
					valid[i][j] = true;
				}
				//s1 is empty
				else if(i == 0) {
					valid[i][j] = (valid[i][j-1] && s2.charAt(j-1) == s3.charAt(j-1));
				}
				//s2 is empty
				else if(j == 0) {
					valid[i][j] = (valid[i-1][j] && s1.charAt(i-1) == s3.charAt(i-1));
				}
				else {
					valid[i][j] = (valid[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1)) || 
					(valid[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
				}
			}
		}
		return valid[s1.length()][s2.length()];
	}
}