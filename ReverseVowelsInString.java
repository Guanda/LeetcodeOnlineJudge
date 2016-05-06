/*
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

*/

class ReverseVowelsInString {
	public String reverseVowels(String s) {
		String vowels = "aeiouAEIOU";

		int start = 0;
		int end = s.length() - 1;

		char[] array = s.toCharArray();

		while(start < end) {
			if(vowels.contains(array[start] + "") && vowels.contains(array[end] + "")) {
				char c = array[start];
				array[start] = array[end];
				array[end] = c;
				start++;
				end--;
			}
			else if(vowels.contains(array[start] + "")) {
				end--;
			}
			else if(vowels.contains(array[end] + "")) {
				start++;
			}
			else {
				start++;
				end--;
			}
		}

		return new String(array);
	}
}