/*
Given a string S and a string T, find the minimum window in S which will contain all 
the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be 
only one unique minimum window in S.

Analysis: 窗口指针类型为题
*/

class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if(s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }
        
        String minStr = "";
        int[] sourceHash = new int[256];
        int[] targetHash = new int[256];
        
        //initial targetHash
        for(int i = 0; i < t.length(); i++) {
            targetHash[t.charAt(i)]++;
        }
        
        int i = 0, j = 0;
        int ans = Integer.MAX_VALUE;
        for(i = 0; i < s.length(); i++) {
            while(j < s.length() && !isValid(sourceHash, targetHash)) {
                sourceHash[s.charAt(j)]++;
                if(j < s.length()) {
                    j++;
                }
                else {
                    break;
                }
            }
            if(isValid(sourceHash, targetHash)) {
                if(ans > j - i) {
                    ans = j - i;
                    minStr = s.substring(i, j);
                }
            }
            sourceHash[s.charAt(i)]--;
        }
        return minStr;
    }
    
    private boolean isValid(int[] sourceHash, int[] targetHash) {
        for(int i = 0; i < 256; i++) {
            if(targetHash[i] > sourceHash[i]) {
                return false;
            }
        }
        return true;
    }
}
