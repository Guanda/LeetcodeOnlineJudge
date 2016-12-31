/*
Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. 
Find and return the shortest palindrome you can find by performing this transformation.

For example:
Given "aacecaaa", return "aaacecaaa".
Given "abcd", return "dcbabcd".

Analysis:
	Use KMP alogrithm
	The key of KMP is to build a look up table (partial match table, PMT) that records the match result of 
	prefix and postfix. Value in the table means the max len of matching substring that exists in both prefix and postfix.
	In the prefix this substring should starts from 0, while in the postfix this substring should ends at current index.

	This problem asks us to add string before the input so the result string will be a palindrome.
	We can convert it to an alternative problem"find the longest palindrome substring starts from index 0".
	If we can get the length of such substring, then we can easily build a palindrome string by inserting the 
	reverse part of substring after such substring before the original string.

	Example:
	input string:
	abacd
	longest palindrome substring starts from 0:
	aba
	Insert the reverse part of substring after palindrome substring before the head:
	dcabacd

	Now the problem becomes how to find the longest palindrome substring starts from 0.
	We can solve it by using a trick + KMP.

	The trick is to build a temp string like this:

	s + "#" + reverse(s)
	Then we run KMP on it, the value in last cell will be our solution. In this problem, we don't need to use KMP
	to match strings but instead we use the lookup table in KMP to find the palindrome.

	We add "#" here to force the match in reverse(s) starts from its first index
	What we do in KMP here is trying to find a match between prefix in s and a postfix in reverse(s). 
	The match part will be palindrome substring.

	Example:
	input:
	catacb
	Temp String:
	catacb # bcatac
	KMP table:
	c a t a c b # b c a t a c
	0 0 0 0 1 0 0 0 1 2 3 4 5
	In the last cell, we got a value 5. It means in s we have a substring of length 5 that is palindrome.
*/
class ShortestPalindrome {
	public String shortestPalindrome(String s) {
	    String temp = s + "#" + new StringBuilder(s).reverse().toString();
	    int[] table = getTable(temp);
	    
	    //get the maximum palin part in s starts from 0
	    return new StringBuilder(s.substring(table[table.length - 1])).reverse().toString() + s;
	}

	//Look at the KMP, this is how Partial Match Table works
	public int[] getTable(String s){
	    //get lookup table
	    int[] table = new int[s.length()];
	    	    
	    int index = 0;
	    //skip index 0, we will not match a string with itself
	    for(int i = 1; i < s.length(); i++){
	        if(s.charAt(index) == s.charAt(i)){
	            //we can extend match in prefix and postfix
	            table[i] = table[i-1] + 1;
	            index ++;
	        }else{
	            //match failed, we try to match a shorter substring
	            //by assigning index to table[i-1], we will shorten the match string length, and jump to the 
	            //prefix part that we used to match postfix ended at i - 1
	            index = table[i-1];
	            
	            while(index > 0 && s.charAt(index) != s.charAt(i)){
	                //we will try to shorten the match string length until we revert to the beginning of match (index 1)
	                index = table[index-1];
	            }
	            
	            //when we are here may either found a match char or we reach the boundary and still no luck
	            //so we need check char match
	            if(s.charAt(index) == s.charAt(i)){
	                //if match, then extend one char 
	                index ++ ;
	            }
	            table[i] = index;
	        }
	    }
	    return table;
	}
}