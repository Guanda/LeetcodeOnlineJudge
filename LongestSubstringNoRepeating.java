/*
Given a string, find the length of the longest substring without repeating characters. 
For example, the longest substring without repeating letters for "abcabcbb" is "abc", 
which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.

Analysis:
	We can use to pointers indicating the substring we are processing. And we should make 
	sure the substring only contains non-repeating characters.

	A boolean array is used to save the occurrence of characters. We move one pointer forward, 
	and check the boolean array. If it is false, we turn it to true. If it’s true, we need to 
	keep moving another pointer forward until it meets the same character as the first pointer 
	pointing to. We can update the max value every time if we move the first pointer.

	Complexity:
	In the worst case we only scan the string twice. So the complexity is O(n).
*/

class LongestSubstringNoRepeating {
	public int lengthOfLongestSubstring(String s) {
		if(s.length() <= 1)
			return s.length();

		int prev = 0;
		boolean[] letter = new boolean[256];
		int max = 0;
		for(int i = 0; i < s.length(); i++) {
			if(!letter[s.charAt(i)])
				letter[s.charAt(i)] = true;
			else {
				while(s.charAt(prev) != s.charAt(i)) {
					letter[s.charAt(prev)] = false;
					prev++;
				}
				prev++;
			}
			max = Math.max(max, i - prev + 1);
		}
		return max;
	}
}