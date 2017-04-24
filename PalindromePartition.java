/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return
  [
    ["aa","b"],
    ["a","a","b"]
  ]

Analysis:
	DFS
*/

class PalindromePartition {
	public List<List<String>> partition(String s) {
 		List<List<String>> result = new ArrayList<>();
 		if(s == null || s.length() == 0) {
 			return result;
 		}

 		List<String> partition = new ArrayList<String>();
 		addPalindrome(s, 0, partition, result);

 		return result;
 	}

 	public void addPalindrome(String s, int start, List<String> partition, List<List<String>> result) {
 		//stop condition
 		if(start == s.length()) {
 			//need deep copy
 			List<String> tmp = new ArrayList<String>(partition);
 			result.add(tmp);
 			return;
 		}

 		for(int i = start; i < s.length(); i++) {
 			String str = s.substring(start, i+1);
 			if(isPalindrome(str)) {
 				partition.add(str);
 				addPalindrome(s, i+1, partition, result);
 				//backtracking
 				partition.remove(partition.size() - 1);
 			}
 		}
 	}

 	public boolean isPalindrome(String str) {
 		int left = 0;
 		int right = str.length() - 1;

 		while(left < right) {
 			if(str.charAt(left) != str.charAt(right))
 				return false;
 			left++;
 			right--;
 		}
 		return true;
 	}
}