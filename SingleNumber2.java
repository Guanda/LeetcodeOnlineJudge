/*
Given an array of integers, every element appears three times except for one. Find that single one.

Anaylsis:
We can sum the bits in same positions for all the numbers and take modulo with 3. 
The bits for which sum is not multiple of 3, are the bits of number with single occurrence.

Let us consider the example array {5, 5, 5, 8}. The 101, 101, 101, 1000
Sum of first bits%3 = (1 + 1 + 1 + 0)%3 = 0;
Sum of second bits%3 = (0 + 0 + 0 + 0)%0 = 0;
Sum of third bits%3 = (1 + 1 + 1 + 0)%3 = 0;
Sum of fourth bits%3 = (1)%3 = 1;
Hence number which appears once is 1000
*/

class SingleNumber2
{
	public int singleNumber2(int[] A)
	{
		int result = 0;
		int x, sum;

		//Iterate every bit
		for(int i = 0; i < 32; i++)
		{
			//find sum of set bits at ith position in all array elements
			sum = 0;
			x = 1 << i;
			for(int j = 0; j < A.length; j++)
			{
				if((A[j] & x) != 0)
					sum++;
			}

			//the bits with sum not multiple of 3, are the bits of element with single occurrence
			if(sum % 3 != 0)
				result = result | x;
		}
		return result;
	}
}