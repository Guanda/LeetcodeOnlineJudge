/*
Implement strStr().
Returns the index of the first occurrence of needle in haystack, 
or -1 if needle is not part of haystack.

(Check if a string existing in another string)

*/

class StrStr()
{
	public int strStr(String haystack, String needle)
	{
		if(haystack == null || needle == null)
			return -1;

		int start = 0;
		int len = needle.length();

		while(start + len <= haystack.length())
		{
			if(haystack.substring(start, start + len).equals(needle))
			{
				return start;
			}
			start++;
		}
		return -1;
	}
}