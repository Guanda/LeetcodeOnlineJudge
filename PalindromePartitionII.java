/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

Analysis:
	DP Solution
	The definition of 'cut' array is the minimum number of cuts of a sub string. More specifically, 
	cut[n] stores the cut number of string s[0, n-1].

	Here is the basic idea of the solution:

	Initialize the 'cut' array: For a string with n characters s[0, n-1], it needs at most n-1 cut. Therefore, 
	the 'cut' array is initialized as cut[i] = i-1

	Use two variables in two loops to represent a palindrome:
	The external loop variable 'i' represents the center of the palindrome. The internal loop variable 'j' represents 
	the 'radius' of the palindrome. Apparently, j <= i is a must.
	This palindrome can then be represented as s[i-j, i+j]. If this string is indeed a palindrome, then one possible 
	value of cut[i+j] is cut[i-j] + 1, where cut[i-j] corresponds to s[0, i-j-1] and 1 correspond to the palindrome 
	s[i-j, i+j];
*/
class PalindromePartitionII {
	public int minCut(String s) {
		int[] cut = new int[s.length() + 1];

		//initial cut[]
		for(int i = 0; i <= s.length(); i++) {
			cut[i] = i - 1;
		}

		//based on the position i, go before and after to check character
		for(int i = 0; i < s.length(); i++) {
			//odd length
			//以i为轴，左右距离为j进行判断。 从i-j到i+j是一个以i为中心的palindrome
			for(int j = 0; i-j>=0 && i+j<s.length() && s.charAt(i-j) == s.charAt(i+j); j++) {
				cut[i+j+1] = Math.min(cut[i+j+1], 1 + cut[i-j]);

			}

			//even length
			//以中心左元素为起点，判断长度为偶数的palindrome
			for(int j = 1; i-j+1>=0 && i+j<s.length() && s.charAt(i-j+1) == s.charAt(i+j); j++) {
				cut[i+j+1] = Math.min(cut[i+j+1], 1 + cut[i-j+1]);
			}
		}
		return cut[s.length()];
	}
}