/*
Given an array nums, there is a sliding window of size k which is moving from the very left of the 
array to the very right. You can only see the k numbers in the window. Each time the sliding window 
moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

Analysis:
	Using a data structure such as deque (double-ended queue).
	1. If an element in the deque and it is out of i-(k-1), we discard them. We just need to poll from the head, 
	   as we are using a deque and elements are ordered as the sequence in the array

	2. Now only those elements within [i-(k-1),i] are in the deque. We then discard elements smaller than a[i] 
	   from the tail. This is because if a[x] <a[i] and x<i, then a[x] has no chance to be the "max" in [i-(k-1),i], 
	   or any other subsequent window: a[i] would always be a better candidate.

	3. As a result elements in the deque are ordered in both sequence in array and their value. At each step the 
	   head of the deque is the max element in [i-(k-1),i]
*/
class SlidingWindowMaximum {
	public int[] maxSlidingWindow(int[] nums, int k) {
		if(nums == null || nums.length == 0)
			return new int[0];
		int[] result = new int[nums.length-k+1];
		int rIndex = 0;

		// store index
		Deque<Integer> q = new LinkedList<Integer>();

		for(int i = 0; i < nums.length; i++) {
			// remove numbers out of range k
			if(!q.isEmpty() && q.peek() < i - k + 1) {
				q.poll();
			}
			// remove smaller numbers in k range as they are useless
			while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
				q.pollLast();
			}
			// q contains index... result contains content
			q.offer(i);
			if (i >= k - 1) {
				result[rIndex++] = nums[q.peek()];
			}
		}
		return result;
	}
}