/*
Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 2^31).

Example 1:
Input:
3
Output:
3

Example 2:
Input:
11
Output:
0
Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.

Analysis:
	Straight forward way to solve the problem in 3 steps:
		1. find the length of the number where the nth digit is from
		2. find the actual number where the nth digit is from
		3. find the nth digit and return
*/
class NthDigit {
	public int findNthDigit(int n) {
		int len = 1;
		long count = 9;
		int start = 1;

		//step 1
		while(n > len * count) {
			n -= len * count;
			len++;
			count *= 10;
			start *= 10;
		}

		//step 2
		start += (n - 1) / len; //减1是因为start 自己算一个数，要把start 从计算中抠掉
		String s = Integer.toString(start);

		//step 3
		return Character.getNumericValue(s.charAt((n - 1) % len));
	}
}