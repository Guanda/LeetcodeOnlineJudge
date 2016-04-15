/*
Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num 
calculate the number of 1's in their binary representation and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can 
you do it in linear time O(n) /possibly in a single pass?

Space complexity should be O(n).

You should make use of what you have produced already.
Divide the numbers in ranges like [2-3], [4-7], [8-15] and so on. And try to generate new range from previous.
Or does the odd/even status of the number help you in calculating the number of 1s?

Analysis:
	For number 2(10), 4(100), 8(1000), 16(10000), ..., the number of 1's is 1. 
	Any other number can be converted to be 2^m + x. For example, 9=8+1, 10=8+2. 
	The number of 1's for any other number is 1 + # of 1's in x.

*/

class CountingBits{
	public int[] countBits(int num) {
		int[] count = new int[num + 1];
		for(int i = 0; i <= num; i++) {
			count[i] = count[i / 2] + i % 2;
		}

		return count;
	}
}
