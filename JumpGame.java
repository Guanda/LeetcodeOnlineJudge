/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.
A = [3,2,1,0,4], return false.

Analysis:
	Method 1:
		Notice that the value of element i represents the maximum jump length.
		Suppose we are at A[i].
		If the value is no less than the distance to the last element, then yes;
		Or if any of the elements within the jump range can reach the last one, then yes;
		Otherwise, no.

	Method 2:
		Actually, all we want to know whether the next can-reach-end element is within my 
		range. So, during the iteration, we can store the position of the next can-reach-end 
		element and then for each successor element, we only need to check whether that node 
		is reachable.
*/

class JumpGame{
	//Method 1: DP, time O(n^2), time limit exceeded in OJ
	public boolean canJump(int[] nums) {
		if(nums == null || nums.length == 0) {
            return true;
        }
        
        //use dp, can[i] means current index i can be reached
        boolean[] can = new boolean[nums.length];
        can[0] = true;
        
        for(int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(can[j] && j + nums[j] >= i) {
                    can[i] = true;
                    break;
                }
            }
        }
        
        return can[nums.length - 1];
	}

	//Method 2: Greedy, time O(n)
	public boolean canJump(int[] nums) {
		if(nums == null || nums.length <= 1)
			return true;

		int next = nums.length - 1;
		for(int i = nums.length - 2; i >= 0; i--) {
			//check the distance between the next and current pointer
			if(nums[i] >= (next - i)) {
				next = i;
			}
		}
		return (next == 0);
	}
}