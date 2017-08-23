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
