/*
Given a string array words, find the maximum value of length(word[i]) * length(word[j]) 
where the two words do not share common letters. You may assume that each word will contain 
only lower case letters. If no such two words exist, return 0.

Example 1:
Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
Return 16
The two words can be "abcw", "xtfn".

Example 2:
Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
Return 4
The two words can be "ab", "cd".

Example 3:
Given ["a", "aa", "aaa", "aaaa"]
Return 0
No such pair of words.

Analysis:
	int has 32bits,but lower case letters only has 26 .we can use the lowest 26 bit of int indicates that the word 
	has how many kinds of lower case letters .If the lowest bit of int is 1,it indicates the word has lower case 
	letter 'a'.......the order of lower case letter is from right to left,like zyx.....cba. So elements[i] indicates 
	the condition of the word i having how many kinds of lower case letters .
*/

class MaxProductOfWordLength {
	public int maxProduct(String[] words) {
		int len = words.length;
		int[] elements = new int[len];

		for(int i = 0; i < len; i++) {
			for(int j = 0; j < words[i].length(); j++) {
				elements[i] = elements[i] | 1 << (words[i].charAt(j) - 'a');
			}
		}

		int result = 0;
		for(int i = 0; i < len; i++) {
			for(int j = 1; j < len; j++) {
				//check if two words have no common letters
				if((elements[i] & elements[j]) == 0)
					result = Math.max(result, words[i].length() * words[j].length());
			}
		}
		return result;
	}
}