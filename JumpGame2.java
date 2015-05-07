/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

Analysis
Method 1:
	we start from an O(n^2) algorithm where we go backwards, compute the minimum steps for the current element by checking 
	each element within its range to find the next one with smallest number of steps.

Method 2:
	We can use Greedy algorithm here. The basic idea is each time when we jump, we jump to the farthest slot.
	Start from slot i,
	~ next is the next position where we will jump, so it starts from 0.
	~ max is the farthest position we can reach so far, so every time when we jump, we set next to current max.
	Note: It is possible that the endpoint is unreachable (e.g. 3 2 1 0 0 0 0 0 0 1). In that case, at some jump point, 
	we will find out that we are jumping to the same point. Then, game over.

*/

class JumpGame2
{
	//Method 1: time limit exceeded
	public int jump(int[] nums)
	{
		if(nums.length <= 1)
			return 0;

		int[] steps = new int[nums.length - 1];
		for(int i = nums.length - 2; i >= 0; i--)
		{
			if(nums[i] >= (nums.length - 1 - i))
			{
				steps[i] = 1;
			}
			else
			{
				int min = nums.length;
				for(int j = 1; j <= nums[i]; j++)
				{
					min = Math.min(min, steps[i+j]);
				}
				steps[i] = min + 1;
			}
		}
		return steps[0];
	}


	//Method 2
	public int jump(int[] nums)
	{
		int steps = 0;
		for(int i = 0, max = 0, next = 0; i < nums.length - 1 && next < nums.length - 1; i++)
		{
			max = Math.max(max, i+nums[i]);
			if(i == next) //ready to jump
			{
				//unreachable
				if(max == next)
					return -1;
				next = max;
				steps++;
			}
		}
		return steps;
	}
}