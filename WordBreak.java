/*
Given a string s and a dictionary of words dict, determine if s can be segmented into 
a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".

Analysis:
	Use DP solution.
*/

class WordBreak {
	public boolean workBreak(String s, Set<String> wordDict) {
		boolean[] result = new boolean[s.length()+1];
		result[0] = true;

		for(int i = 1; i <= s.length(); i++) {
			for(int j = 0; j < i; j++) {
				if(result[j] && wordDict.contains(s.substring(j, i))) {
					result[i] = true;
					break;
				}
			}
		}

		return result[s.length()];
	}
}