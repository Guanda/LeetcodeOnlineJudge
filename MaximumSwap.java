/*
Given a non-negative integer, you could swap two digits at most once to get the 
maximum valued number. Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:
Input: 9973
Output: 9973
Explanation: No swap.
Note:
The given number is in the range [0, 108]
*/

class MaximumSwap {
    public int maximumSwap(int num) {
        String s = Integer.toString(num);
        if(s.length() <= 1) {
            return num;
        }
        
        int[] bucket = new int[10];
        for(int i = 0; i < s.length(); i++) {
            bucket[s.charAt(i) - '0'] = i;
        }
        
        for(int i = 0; i < s.length(); i++) {
            for(int j = 9; j > s.charAt(i) - '0'; j--) {
                if(bucket[j] > i) {
                    char[] digits = s.toCharArray();
                    char tmp = digits[i];
                    digits[i] = digits[bucket[j]];
                    digits[bucket[j]] = tmp;
                    return Integer.valueOf(new String(digits));
                }
            }
        }
        
        return num;
    }
}