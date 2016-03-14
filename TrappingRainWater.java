/*
Given n non-negative integers representing an elevation map where the 
width of each bar is 1, compute how much water it is able to trap after 
raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

Analysis:

对任意位置i，在i上的积水，由左右两边最高的bar：A[left] = max{A[j], j<i}, A[right] = max{A[j], j>i}决定。
定义Hmin = min(A[left], A[right])，则积水量Si为：

Hmin <= A[i]时，Si = 0
Hmin > A[i]时，Si = Hmin - A[i]



*/

class TrappingRainWater {
	//Method 1: using left[] and right[] to maintain max of left and right
	public int trap(int[] height) {
		if(height == null || height.length == 0)
			return 0;

		int water = 0;
		int[] left = new int[height.length];
		int[] right = new int[height.length];

		//fill the left array
		left[0] = height[0];
		for(int i = 1; i < height.length; i++) {
			left[i] = Math.max(height[i], left[i-1]);
		}

		//fill the right array
		right[height.length - 1] = height[height.length - 1];
		for(int j = height.length - 2; j >= 0; j--) {
			right[j] = Math.max(height[j], right[j+1]);
		}

		for(int i = 0; i < height.length; i++) {
			water += Math.min(left[i], right[i]) - height[i];
		}

		return water;
	}

	//optimize of method 1
	public int trapBetter(int[] height) {
		if(height == null || height.length == 0) {
			return 0;
		}

		int water = 0;
		int left = 0;
		int right = 0;
		int start = 0;
		int end = height.length - 1;

		while(start < end) {
			left = Math.max(left, height[start]);
			right = Math.max(right, height[end]);

			if(left < right) {
				water += left - height[start];
				start++;
			}
			else {
				water += right - height[end];
				end--;
			}
		}
		return water;
	}
}