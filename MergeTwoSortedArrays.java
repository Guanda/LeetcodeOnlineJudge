/*
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. 
The number of elements initialized in nums1 and nums2 are m and n respectively.

Analysis:
The key of this problem is moving elements in two arrays backwards!!!!!!!!
Consider use in-place to avoid the extra space. 

*/

class MergeTwoSortedArrays
{
	public void merge(int[] nums1, int m, int[] nums2, int n)
	{
		while(m > 0 && n > 0)
        {
            if(nums1[m-1] >= nums2[n-1])
            {
                nums1[m+n-1] = nums1[m-1];
                m--;
            }
            else
            {
                nums1[m+n-1] = nums2[n-1];
                n--;
            }
        }
        
        //here we only need to consider the n>m situation, because if m>n, the rest of nums1 has left 
        //in the current array, we don't need to process it anymore
        while(n > 0)
        {
            nums1[m+n-1] = nums2[n-1];
            n--;
        }
	}
}