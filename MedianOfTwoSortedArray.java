/*
There are two sorted arrays nums1 and nums2 of size m and n respectively. Find the median of the 
two sorted arrays. The overall run time complexity should be O(log (m+n)).

Analysis:
	Because it requare O(log(m+n)) time, so we need binary search.
	This problem can be converted to the problem of finding kth element, k is (A's length + B' Length)/2.

	If any of the two arrays is empty, then the kth element is the non-empty array's kth element.
	If k == 1, the kth element is the first element of A or B.

	Median of two sorted Arrays 这题复杂度是log(k), 这个算法每次丢掉k/2个数，丢掉logk次就能找到我们要找的数。

	int keyA = indexA + mid >= lenA ? Integer.MAX_VALUE: A[indexA + mid];
	int keyB = indexB + mid >= lenB ? Integer.MAX_VALUE: B[indexB + mid];

	上面这两句话，就是取出当前A数组和B数组中 第k/2个数，如果数组不足k/2个数，则设置为MAX_VALUE，因为每次丢掉一些数，所以A和B数组
	的前面有些数是无效的，我们不能正真的删除，可以增加一个偏移indexA和indexB。

	下面的分析就是要联系整个算法，keyA和keyB比，哪个小，我们就丢掉谁的前k/2个数，反证法中也说明了，第k个数不会出现在这前k/2个数中，
	所以丢掉以后我们就需要调整indexA或者indexB, 然后就是在剩余的数组中，找寻第k - k/2个数，k每次缩小一半，那么直到k=1为止，最后直
	接返回A[indexA]和B[indexB]中的最小值即可
*/

class MedianOfTwoSortedArray {
	//Method 1: easy to understand. Please use it in interview.
    public double findMedianSortedArrays(int[] A, int[] B) {
        int len = A.length + B.length;
        if(len % 2 == 1) {
            return findKth(A, 0, B, 0, len / 2 + 1);
        }
        else {
            return (findKth(A, 0, B, 0, len / 2 + 1) + findKth(A, 0, B, 0, len / 2)) / 2.0;
        }
    }
    
    private int findKth(int[] A, int aStart, int[] B, int bStart, int k) {
        //stop cases and edge case
        if(aStart >= A.length) {
            return B[bStart + k - 1];
        }
        if(bStart >= B.length) {
            return A[aStart + k - 1];
        }
        if(k == 1) {
            return Math.min(A[aStart], B[bStart]);
        }
        
        int aMid = aStart + k / 2 - 1;
        int bMid = bStart + k / 2 - 1;
        
        int aKey = aMid < A.length ? A[aMid] : Integer.MAX_VALUE;
        int bKey = bMid < B.length ? B[bMid] : Integer.MAX_VALUE;
        
        //判断应该去掉哪一个数组的k/2个元素
        //如果aKey<bKey，说明A里面的前k/2个元素可以去掉
        //就是说第k个元素一定不在小的那一部分里
        if(aKey < bKey) {
        	//从A里扔掉前k大里的k/2个数
            return findKth(A, aMid + 1, B, bStart, k - k / 2);
        }
        else {
        	//从B里扔掉前k大里的k/2个数
            return findKth(A, aStart, B, bMid + 1, k - k / 2);
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