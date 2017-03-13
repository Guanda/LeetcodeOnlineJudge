/*
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes 
that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:
Input:
"abccccdd"
Output:
7
Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7

Analysis:
    Count the number of same pairs, then this can be used to put in the different direction to consist 
    of palindrome. Then if there exist more chars, we can put one in the middle.
*/
class LongestPalindrome {
	public int longestPalindrome(String s) {
        if(s == null || s.length() == 0) 
        	return 0;
        Set<Character> hs = new HashSet<Character>();
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            if(hs.contains(s.charAt(i))){
                hs.remove(s.charAt(i));
                count++;
            }
            else {
                hs.add(s.charAt(i));
            }
        }
        if(!hs.isEmpty()) 
        	return count*2+1;
        return count*2;
	}
}