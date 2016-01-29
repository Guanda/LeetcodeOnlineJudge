/*
Given an integer, write a function to determine if it is a power of three

*/

class PowerOfThree {
	//Method 1: iteration
	public boolean isPowerOfThree(int n) {
		while(n % 3 == 0 && n > 1) {
			n = n / 3;
		}
		return n == 1;
	}

	//Method 2: 3^19 is the largest number which is less than max integer
	public boolean isPowerOfThree2(int n) {
		return (1162261467 % n == 0);
	}
}