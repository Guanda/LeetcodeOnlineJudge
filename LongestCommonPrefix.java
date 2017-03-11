/*
Write a function to find the longest common prefix string amongst an array of strings.
*/

class LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
		if(strs == null || strs.length == 0)
			return "";

		if(strs.length == 1)
			return strs[0];

		String str = strs[0];
		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			for(int j = 1; j < strs.length; j++) {
				//Don't forget to check if index is out of range of other str in strs beside strs[0].
				if(strs[j].length() == i || strs[j].charAt(i) != c) {
					return str.substring(0, i);
				}
			}
		}
		return str;
	}
}