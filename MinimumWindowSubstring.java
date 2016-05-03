/*
Given a string S and a string T, find the minimum window in S which will contain all 
the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be 
only one unique minimum window in S.

Analysis:
	Using map and two pointer to track

*/

class MinimumWindowSubstring {
	public String minWindow(String s, String t) {
		if(s == null || s.length() == 0 || t == null || t.length() == 0) {
			return "";
		}

		int[] sMap = new int[128];
		int[] tMap = new int[128];
		for(int i = 0; i < t.length(); i++) {
			tMap[t.charAt(i)]++;
		}

		int begin = 0;
		int end = 0;
		int found = 0;
		int min = Integer.MAX_VALUE;
		String result = "";

		while(end < s.length()) {
			if(found < t.length()) {
				if(tMap[s.charAt(end)] > 0) {
					sMap[s.charAt(end)]++;
					if(sMap[s.charAt(end)] <= tMap[s.charAt(end)]) {
						found++;
					}
				}
				end++;
			}

			while(found == t.length()) {
				if(end - begin < min) {
					min = end - begin;
					result = s.substring(begin, end);
				}

				//move begin to next
				if(tMap[s.charAt(begin)] > 0) {
					sMap[s.charAt(begin)]--;
					if(sMap[s.charAt(begin)] < tMap[s.charAt(begin)]) {
						found--;
					}
				}

				begin++;
			}
		}
		return result;
	}
}
