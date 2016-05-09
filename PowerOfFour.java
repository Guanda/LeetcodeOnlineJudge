/*
Given an integer (signed 32 bits), write a function to check whether it is a power of 4.

Example:
Given num = 16, return true. Given num = 5, return false.

Follow up: Could you solve it without loops/recursion?

*/

class PowerOfFour {
	//Method 1: use loop
	public boolean isPowerOfFour(int num) {
		while(num > 1) {
			if(num % 4 != 0)
				return false;

			num = num / 4;
		}

		return num == 1;
	}

	//Method 2: bit manipulation
	public boolean isPowerOfFourBetter(int num) {
		//first two conditions are for power of 2
		return (num & (num - 1)) == 0 && num > 0 && (num - 1) % 3 == 0;
	}
}