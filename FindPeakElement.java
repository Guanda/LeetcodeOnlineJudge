/*

A peak element is an element that is greater than its neighbors.
Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

Note:
Your solution should be in logarithmic complexity.

Anaylsis:
We can use Divide and Conquer to find a peak in O(Logn) time. 
The idea is Binary Search based, we compare middle element with 
its neighbors. If middle element is greater than both of its 
neighbors, then we return it. If the middle element is smaller 
than the its left neighbor, then there is always a peak in left 
half. If the middle element is smaller than the its right neighbor, 
then there is always a peak in right half. 
*/

class FindPeakElement
{
	public int findPeakElement(int[] num)
	{
		int left = 0;
		int right = num.length - 1;
		while(left < right)
		{
			int mid = (left + right) / 2;
			if(num[mid] < num[mid+1])
				left = mid + 1;
			else
				right = mid;
		}
		return left;
	}
}