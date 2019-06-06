/*
Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

Analysis:
Two method, one is normal search, the other is binary search.

In fact, in this problem, we are going to use binary search to find the border of certain target, 
because there may be duplicate numbers. We can easily find the left border of certain target. 
And we can try to find the left border of target + 1. And check if it exists to determine the right border.

*/

class SearchRange{
	//Method 1: normal search
	public int[] searchRange(int[] A, int target){
		int[] result = new int[2];
		int p1 = -1;
		int p2 = -1;
        
		for(int i = 0; i <= A.length - 1; i++){
			if(A[i] == target){
				p1 = i;
				break;
			}
		}

		for(int i = A.length - 1; i >= 0; i--){
			if(A[i] == target){
				p2 = i;
				break;
			}
		}
		
		result[0] = p1;
		result[1] = p2;

		return result;
	}

	//Method 2: Binary search
	public int[] searchRangeBetter(int[] A, int target){
		int[] range = {-1, -1};
		int low = 0;
		int high = A.length - 1;

		while(low <= high){
			int mid = (low + high) / 2;
			if(A[mid] == target){
				if(mid == 0 || A[mid - 1] < target){
					//find the beginning
					range[0] = mid;
					low = mid;
					high = A.length - 1;
				}

				if(mid == A.length - 1 || A[mid + 1] > target){
					//find the ending
					range[1] = mid;
					low = 0;
					high = mid;
				}
				if(range[0] > -1 && range[1] > -1)
					return range;

				if(range[0] < 0)
					high = mid - 1;
				else
					low = mid + 1;
			}
			else if(A[mid] < target){
				low = mid + 1;
			}
			else{
				high = mid - 1;
			}
		}
		return range;
	}
}
