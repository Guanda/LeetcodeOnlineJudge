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

		int[] map = new int[128];
		for(int i = 0; i < t.length(); i++) {
			map[t.charAt(i)]++;
		}

		int counter = t.length(); // check whether the substring is valid
        int begin=0, end=0; // two pointers, one point to tail and one head
        int head = 0;
        int len = Integer.MAX_VALUE; //the length of substring
        while(end < s.length()) {
        	if(map[s.charAt(end)] > 0) {
        		counter--;
        	}
        	map[s.charAt(end)]--;
        	end++;

        	// When we found a valid window, move start to find smaller window.
        	while(counter == 0) {
        		if(end - begin < len) {
        			len = end - begin;
        			head = begin;
        		}
        		map[s.charAt(begin)]++;
        		if(map[s.charAt(begin)] > 0) {
        			counter++;
        		}
        		begin++;
        	}
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(head, head + len);
	}
}
