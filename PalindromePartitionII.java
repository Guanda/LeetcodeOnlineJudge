/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

Analysis:
	DP Solution, from the beginning of string, use cut[] to save the cut

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
			for(int j = 0; i-j>=0 && i+j<s.length() && s.charAt(i-j) == s.charAt(i+j); j++) {
				cut[i+j+1] = Math.min(cut[i+j+1], 1 + cut[i-j]);

			}

			//even length
			for(int j = 1; i-j+1>=0 && i+j<s.length() && s.charAt(i-j+1) == s.charAt(i+j); j++) {
				cut[i+j+1] = Math.min(cut[i+j+1], 1 + cut[i-j+1]);
			}
		}
		return cut[s.length()];
	}
}