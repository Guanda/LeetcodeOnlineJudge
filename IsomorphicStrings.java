/*
Given two strings s and t, determine if they are isomorphic.
Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. 
No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.
Given "foo", "bar", return false.
Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.

Analysis:
	Use two maps to keep the reflect relection. One store s, the other store t
*/

class IsomorphicStrings {
	public boolean isIsomorphic(String s, String t) {
		char[] maps = new char[128];
		char[] mapt = new char[128];

		for(int i = 0; i < s.length(); i++) {
			//need check the maps and mapt are not empty
			if((maps[s.charAt(i)] != 0 && maps[s.charAt(i)] != t.charAt(i)) 
				|| (mapt[t.charAt(i)] != 0 && mapt[t.charAt(i)] != s.charAt(i))) {
				return false;
			}
			maps[s.charAt(i)] = t.charAt(i);
			mapt[t.charAt(i)] = s.charAt(i);
		}
		return true;
	}
}