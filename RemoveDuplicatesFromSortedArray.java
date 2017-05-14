/*
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array A = [1,1,2],

Your function should return length = 2, and A is now [1,2].

Anaylsis:
	The problem needs the new array be unique array, otherwise we don't need replace the duplicates number with "else if".
*/

class RemoveDuplicatesFromSortedArray {
	public int removeDuplicates(int[] A) {
		int count = 0;
		for(int i = 1; i < A.length; i++) {
			if(A[i] == A[i-1])
				count++;
			else if(count > 0)
				A[i - count] = A[i];
		}
		return A.length - count;
	}
}