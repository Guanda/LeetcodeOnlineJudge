/*
Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, 
where a move is incrementing a selected element by 1 or decrementing a selected element by 1.

You may assume the array's length is at most 10,000.

Example:
Input:
[1,2,3]
Output:
2

Explanation:
Only two moves are needed (remember each move increments or decrements one element):
[1,2,3]  =>  [2,2,3]  =>  [2,2,2]

Analysis:
	The key is to find the median of array. And we don't need to really find it, just use left and right,
	make them meet in the middle.
*/
class MinMovesToEqualArrayII {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int result = 0;
        int left = 0;
        int right = nums.length - 1;
        while(left < right) {
            result += nums[right] - nums[left];
            right--;
            left++;
        }
        return result;
    }
}