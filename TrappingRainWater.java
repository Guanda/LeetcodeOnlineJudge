/*
Given n non-negative integers representing an elevation map where the 
width of each bar is 1, compute how much water it is able to trap after 
raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

*/

class TrappingRainWater {
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
}