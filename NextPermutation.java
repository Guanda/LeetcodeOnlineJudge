/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

Analysis:
Two steps to do this:
	1) Find the longest descending tail of the array and reverse them into ascending order.
	2) If there is any number ahead of the descending tail, it should be replace with the first greater number in the tail.
	The algorithm below runs in time O(n) and takes O(1) space. We reverse the tail rather than sort them, since we already 
	know they are in descending order. Some sorting algorithms may take O(nlogn) or O(n^2) time to sort a reverse ordered 
	array rather than O(n). See wiki for more fun about sorting algorithms.

*/

class NextPermutation
{
	public void nextPermutation(int[] nums)
	{
		//find the longest decending tail
		int curr = nums.length - 1;
		while(curr > 0 && nums[curr-1] >= nums[curr])
		{
			curr--;
		}

		//reverse the decending tail
		int left = curr;
		int right = nums.length - 1;
		while(left < right)
		{
			int tmp = nums[left];
			nums[left] = nums[right];
			nums[right] = tmp;
			left++;
			right--;
		}

		if(curr > 0)
		{
			//insert curr-1 to the right spot
			int next = curr;
			curr = curr - 1;
			while(nums[next] <= nums[curr])
			{
				next++;
			}
			//swap the next and curr
			int tmp = nums[curr];
			nums[curr] = nums[next];
			nums[next] = tmp;
		}
	}
}