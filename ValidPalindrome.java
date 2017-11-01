/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.
*/

class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if(s == null || s.length() <= 1) {
        	return true;
        }

        char[] arr = s.toCharArray();
        int left = 0, right = arr.length - 1;
        while(left < right) {
        	if(!Character.isLetterOrDigit(arr[left])) {
        		left++;
        	}
        	else if(!Character.isLetterOrDigit(arr[right])) {
        		right--;
        	}
        	else if(Character.toLowerCase(arr[left++]) != Character.toLowerCase(arr[right--])) {
        		return false;
        	}
        }

        return true;
    }
}