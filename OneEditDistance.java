/*
Given two strings S and T, determine if they are both one edit distance apart.
*/

class OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
     	if(s == null || t == null) {
     		return false;
     	}   

     	for(int i = 0; i < Math.min(s.length(), t.length()); i++) {
     		if(s.charAt(i) != t.charAt(i)) {
     			if(s.length() == t.length()) {
     				return s.substring(i + 1).equals(t.substring(i + 1));
     			}
     			else if(s.length() < t.length()) {
     				return s.substring(i).equals(t.substring(i + 1));
     			}
     			else {
     				return s.substring(i + 1).equals(t.substring(i));
     			}
     		}
     	}

     	//All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t 
    	return Math.abs(s.length() - t.length()) == 1;
    }
}