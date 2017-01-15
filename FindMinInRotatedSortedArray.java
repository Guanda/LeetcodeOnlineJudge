/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.

Analysis:
The key is how to use binary search to solve this problem.
Compare the middle element with the left one to find out  whether the minimum element in the left or right part.
Remember the case that no rotated.

*/

class FindMinInRotatedSortedArray
{
	public int findMin(int[] num)
	{
		return findMin(num, 0, num.length-1);
	}

	public int findMin(int[] num, int left, int right)
	{
		if(left == right)
			return num[left];
		if(right - left == 1)
			return Math.min(num[left], num[right]);

		int mid = left + (right - left) / 2;

		//no rotated
		if(num[left] < num[right])
			return num[left];
		//go right side
		else if(num[left] < num[mid])
			return findMin(num, mid, right);
		//go left side
		else
			return findMin(num, left, mid);
	}
}
