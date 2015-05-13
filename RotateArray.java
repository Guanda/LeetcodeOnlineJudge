/*
Rotate an array of n elements to the right by k steps.
For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Analysis:
	Reverse three times. First the whole array and then the first n-k+1, then rest.

This solution has not passed all the test case. Like: [1, 2] k=1. output: [1, 2], expected ??
*/

class RotateArray
{
	public void rotate(int[] nums, int k)
	{
		int n = nums.length;
		k = k % n;
		reverse(nums, 0, n-k-1);
		reverse(nums, n-k, n-1);
		reverse(nums, 0, n-1);

	}

	public void reverse(int[] nums, int start, int end)
	{
		int p = start;
		int q = end;
		while(p < q)
		{
			int tmp = nums[p];
			nums[p] = nums[q];
			nums[q] = tmp;
			p++;
			q--;
		}
	}
}