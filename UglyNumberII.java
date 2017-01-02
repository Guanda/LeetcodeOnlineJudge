/*
Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number.

Analysis:
	Here is a time efficient solution with O(n) extra space. The ugly-number sequence is 
	1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, …

	because every number can only be divided by 2, 3, 5, one way to look at the sequence is to split the 
	sequence to three groups as below:
	(1) 1×2, 2×2, 3×2, 4×2, 5×2, …
	(2) 1×3, 2×3, 3×3, 4×3, 5×3, …
	(3) 1×5, 2×5, 3×5, 4×5, 5×5, …

	We can find that every subsequence is the ugly-sequence itself (1, 2, 3, 4, 5, …) multiply 2, 3, 5. 
	Then we use similar merge method as merge sort, to get every ugly number from the three subsequence. 
	Every step we choose the smallest one, and move one step after.
*/
class UglyNumberII {
	public int nthUglyNumber(int n) {
		int[] ugly = new int[n];
		ugly[0] = 1;
		//Initialize three array index variables i2, i3, i5 to point to 1st element of the ugly array:
		int i2 = 0, i3 = 0, i5 = 0;
		int nextMultipleOf2 = 2, nextMultipleOf3 = 3, nextMultipleOf5 = 5;
		int nextUgly = 1;
		for(int i = 1; i < n; i++) {
			nextUgly = Math.min(Math.min(nextMultipleOf2, nextMultipleOf3), nextMultipleOf5);
			ugly[i] = nextUgly;
			if(nextUgly == nextMultipleOf2) {
				i2++;
				nextMultipleOf2 = ugly[i2] * 2;
			}
			if(nextUgly == nextMultipleOf3) {
				i3++;
				nextMultipleOf3 = ugly[i3] * 3;
			}
			if(nextUgly == nextMultipleOf5) {
				i5++;
				nextMultipleOf5 = ugly[i5] * 5;
			}
		}
		return nextUgly;
	}
}