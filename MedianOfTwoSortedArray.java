/*
There are two sorted arrays nums1 and nums2 of size m and n respectively. Find the median of the 
two sorted arrays. The overall run time complexity should be O(log (m+n)).

Analysis:
	Because it requare O(log(m+n)) time, so we need binary search.
	This problem can be converted to the problem of finding kth element, k is (A's length + B' Length)/2.

	If any of the two arrays is empty, then the kth element is the non-empty array's kth element.
	If k == 0, the kth element is the first element of A or B.

	For normal cases(all other cases), we need to move the pointer at the pace of half of an array length.
*/

class MedianOfTwoSortedArray {
	//Method 1: easy to understand, but slow
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
	    int total = nums1.length+nums2.length;
	    if(total%2==0){
	        return (findKth(total/2+1, nums1, nums2, 0, 0)+findKth(total/2, nums1, nums2, 0, 0))/2.0;
	    }else{
	        return findKth(total/2+1, nums1, nums2, 0, 0);
	    }
	}
	 
	public int findKth(int k, int[] nums1, int[] nums2, int s1, int s2){
	    if(s1>=nums1.length)
	        return nums2[s2+k-1];
	 
	    if(s2>=nums2.length)
	        return nums1[s1+k-1];
	 
	    if(k==1)
	        return Math.min(nums1[s1], nums2[s2]);
	 
	    int m1 = s1+k/2-1;
	    int m2 = s2+k/2-1;
	 
	    int mid1 = m1<nums1.length?nums1[m1]:Integer.MAX_VALUE;    
	    int mid2 = m2<nums2.length?nums2[m2]:Integer.MAX_VALUE;
	 
	    if(mid1<mid2){
	        return findKth(k-k/2, nums1, nums2, m1+1, s2);
	    }else{
	        return findKth(k-k/2, nums1, nums2, s1, m2+1);
	    }
	}


	//Method 2: hard to understand
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;

		//odd
		if((m + n) % 2 != 0) {
			return (double) findKth(nums1, nums2, (m+n)/2, 0, m-1, 0, n-1);
		}
		//even
		else {
			return (findKth(nums1, nums2, (m+n)/2, 0, m-1, 0, n-1) + findKth(nums1, nums2, (m+n)/2-1, 0, m-1, 0, n-1)) * .5;
		}
	}

	public int findKth(int[] nums1, int[] nums2, int k, int aStart, int aEnd, int bStart, int bEnd) {
		int aLen = aEnd - aStart + 1;
		int bLen = bEnd - bStart + 1;

		//handle special cases
		if(aLen == 0)
			return nums2[bStart + k];
		if(bLen == 0)
			return nums1[aStart + k];
		if(k == 0)
			return nums1[aStart] < nums2[bStart] ? nums1[aStart] : nums2[bStart];

		int aMid = aLen * k / (aLen + bLen); //a's middle count
		int bMid = k - aMid - 1; //b's middle count

		//make aMid and bMid to be array index
		aMid = aMid + aStart;
		bMid = bMid + bStart;

		if(nums1[aMid] > nums2[bMid]) {
			k = k - (bMid-bStart + 1);
			aEnd = aMid;
			bStart = bMid + 1;
		}
		else {
			k = k - (aMid - aStart + 1);
			bEnd = bMid;
			aStart = aMid + 1;
		}
		return findKth(nums1, nums2, k, aStart, aEnd, bStart, bEnd);
	}
}