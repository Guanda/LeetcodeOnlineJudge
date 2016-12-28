/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

  [
    ["aa","b"],
    ["a","a","b"]
  ]

*/

 class PalindromePartition
 {
 	public ArrayList<ArrayList<String>> partition(String s)
 	{
 		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
 		if(s == null || s.length() == 0)
 		{
 			return result;
 		}

 		ArrayList<String> partition = new ArrayList<String>();
 		addPalindrome(s, 0, partition, result);

 		return result;
 	}

 	public void addPalindrome(String s, int start, ArrayList<String> partition, ArrayList<ArrayList<String>> result)
 	{
 		//stop condition
 		if(start == s.length())
 		{
 			ArrayList<String> tmp = new ArrayList<String>(partition);
 			result.add(tmp);
 			return;
 		}

 		for(int i = start + 1; i <= s.length(); i++)
 		{
 			String str = s.substring(start, i);
 			if(isPalindrome(str))
 			{
 				partition.add(str);
 				addPalindrome(s, i, partition, result);
 				//be careful about this, it will go back to previous case
 				partition.remove(partition.size() - 1);
 			}
 		}
 	}

 	public boolean isPalindrome(String str)
 	{
 		int left = 0;
 		int right = str.length() - 1;

 		while(left < right)
 		{
 			if(str.charAt(left) != str.charAt(right))
 				return false;
 			left++;
 			right--;
 		}
 		return true;
 	}
 }