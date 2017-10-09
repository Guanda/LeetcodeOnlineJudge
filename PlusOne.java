/*
Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.

Analysis:
	In case of very large number, so we cannot use revert to integer to calculate.
	Classic add-with-carry problem.
*/

class PlusOne {
	public int[] plusOne(int[] digits) {
        if(digits == null || digits.length == 0) {
            return digits;
        }
        
        int carry = 1;
        for(int i = digits.length - 1; i>= 0; i--) {
            int curr = digits[i] + carry;
            carry = curr / 10;
            digits[i] = curr % 10;
        }
        
        if(carry == 1) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            return result;
        }
        
        return digits;
	}
}

/*
Given a number (positive or negative), e.g., 95, as an array ['9','5'], increment it: change it to ['9','6']. 
The input array can be arbitrarily large.

examples:
input: 
char[] [‘9’, ‘5’]

output:
char[] [‘9’, ‘6’]

input: 
char[] [‘-’, ‘9’, ‘5’]

output:
char[] [‘-’, ‘9’, ‘4’]

public char[] increment(char[] chars) {
    if(chars == null || chars.length == 0) {
        return chars;
}
boolean isNegative = (chars[0] == ‘-’); // true
int carry = isNegative ? -1 : 1; // -1
int edge = isNegative ? 1 : 0; // 1
for(int i = chars.length - 1; i >= edge; i--) {
    int curr = Characters.Numberic(chars[i]) + carry; // -1, 0
    if(curr == -1) {
        carry = -1;
        char[i] = ‘9’ // 9
    }
    else {
    carry = curr / 10; // 0
    char[i] = Characters.Numberic(curr % 10); // 0
}
}

if(carry == 1) {
    char[] result = new char[chars.length + 1];
    result[0] = ‘1’;
    return result;
}

if(isNegative && chars[1] == ‘0’) {
    if(chars.length != 2) {
        chars[1] = ‘-’;
}
    return Arrays.copyRangOf(1);
}

return chars; // -09
}
*/
