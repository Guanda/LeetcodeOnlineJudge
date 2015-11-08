/*
Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

*/

public class ValidAnagram
{
	public boolean isAnagram(String s, String t)
	{
		if(s.length() != t.length())
			return false;

		int[] c = new int[26];
		for(int i = 0; i < s.length(); i++)
		{
			c[s.charAt(i) - 'a']++;
		}

		for(int j = 0; j < t.length(); j++)
		{
			c[t.charAt(j) - 'a']--;
			if(c[t.charAt(j) - 'a'] < 0)
				return false;
		}
		return true;
	}
}