/*
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array A = [1,1,1,2,2,3],

Your function should return length = 5, and A is now [1,1,2,2,3].

Analysis:
	Same as problem RemoveDuplicatesFromSortedArray, difference is update when the element appears
	more than twice.
*/

class RemoveDuplicatesFromSortedArray2 {
	public int removeDuplicates(int[] A) {
		if(A.length <= 2)
			return A.length;

		int prev = 1; //point to previous
		int curr = 2; //point to current

		while(curr < A.length) {
			if(A[curr] == A[prev] && A[curr] == A[prev-1])
				curr++;
			else {
				//update the third element, so prev need ++
				prev++;
				//after prev++, it becomes the third element, update it
				A[prev] = A[curr];
				curr++;
			}
		}
		//start from 0, so need plus 1
		return prev+1;
	}
}