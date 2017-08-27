/*
Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:
Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?

Analysis:
	1. use loop
	2. find the occur sequence which is from 1 to 9 and 1 to 9
*/

public class AddDigits {
	//method 1
	public int addDigits(int num) {
		while(num / 10 > 0) {
			int sum = 0;
			while(num > 0) {
				sum += num % 10;
				num = num / 10;				
			}
			num = sum;
		}
		return num;
	}

	//method 2
	public int addDigits2(int num) {
		return (num - 1) % 9 + 1;
	}
}