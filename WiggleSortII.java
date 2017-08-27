/*
Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example:
(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

Note:
You may assume all input has valid answer.

Analysis:
    I put the smaller half of the numbers on the even indexes and the larger half on the odd indexes, 
    both from right to left:

    Example nums = [1,2,...,7]      Example nums = [1,2,...,8] 

    Small half:  4 . 3 . 2 . 1      Small half:  4 . 3 . 2 . 1 .
    Large half:  . 7 . 6 . 5 .      Large half:  . 8 . 7 . 6 . 5
    --------------------------      --------------------------
    Together:    4 7 3 6 2 5 1      Together:    4 8 3 7 2 6 1 5

    I want:
    Odd-index numbers are larger than their neighbors.
    Since I put the larger numbers on the odd indexes, clearly I already have:
    Odd-index numbers are larger than or equal to their neighbors.

    Could they be "equal to"? That would require some number M to appear both in the smaller and 
    the larger half. It would be the largest in the smaller half and the smallest in the larger half. 
    Examples again, where S means some number smaller than M and L means some number larger than M.
*/

class WiggleSortII {
    // Better understanding version:
    public void wiggleSort(int[] nums) {
        // Write your code here
        if(nums == null || nums.length == 0) {
            return;
        }
        
        Arrays.sort(nums);
        int[] tmp = new int[nums.length];
        for(int m = 0; m < nums.length; m++) {
            tmp[m] = nums[m];
        }

        int mid = nums.length % 2 == 0 ? nums.length / 2 - 1 : nums.length / 2;
        
        int i = 1, j = nums.length - 1;
        while(i < nums.length) {
            nums[i] = tmp[j];
            i += 2;
            j--;
        }
        
        int k = 0, l = mid;
        while(k < nums.length) {
            nums[k] = tmp[l];
            k += 2;
            l--;
        }
    }


    // Clean code version
	public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int[] temp = new int[nums.length];
        int mid = nums.length % 2 == 0 ? nums.length/2 - 1 : nums.length/2;
        int index = 0;
        for(int i = 0; i <= mid; i++){
        	temp[index] = nums[mid-i];
        	if(index+1 < nums.length)
        		temp[index+1] = nums[nums.length-i-1];
        	index = index + 2;
        }
        for(int i = 0; i < nums.length; i++){
    		nums[i] = temp[i];
    	}
    }
}