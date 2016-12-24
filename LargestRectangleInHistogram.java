/*
Given n non-negative integers representing the histogram's bar height where 
the width of each bar is 1, find the area of largest rectangle in the histogram.

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

The largest rectangle is shown in the shaded area, which has area = 10 unit, 
which is (1 + 1) * 5

For example,
Given heights = [2,1,5,6,2,3],
return 10.

Analysis:

For every bar ‘x’, we calculate the area with ‘x’ as the smallest bar in the rectangle. If we calculate such area 
for every bar ‘x’ and find the maximum of all areas, our task is done. How to calculate area with ‘x’ as smallest 
bar? We need to know index of the first smaller (smaller than ‘x’) bar on left of ‘x’ and index of first smaller 
bar on right of ‘x’. Let us call these indexes as ‘left index’ and ‘right index’ respectively.

We traverse all bars from left to right, maintain a stack of bars. Every bar is pushed to stack once. A bar is popped 
from stack when a bar of smaller height is seen. When a bar is popped, we calculate the area with the popped bar as 
smallest bar. How do we get left and right indexes of the popped bar – the current index tells us the ‘right index’ 
and index of previous item in stack is the ‘left index’. Following is the complete algorithm.

1) Create an empty stack.

2) Start from first bar, and do following for every bar ‘hist[i]’ where ‘i’ varies from 0 to n-1.
……a) If stack is empty or hist[i] is higher than the bar at top of stack, then push ‘i’ to stack.
……b) If this bar is smaller than the top of stack, then keep removing the top of stack while top of the stack is greater. 
	 Let the removed bar be hist[tp]. Calculate area of rectangle with hist[tp] as smallest bar. For hist[tp], the ‘left 
	 index’ is previous (previous to tp) item in stack and ‘right index’ is ‘i’ (current index).

3) If the stack is not empty, then one by one remove all bars from stack and do step 2.b for every removed bar.

*/

class LargestRectangleInHistogram {
	public int largestRectangleArea (int[] heights) {
		//The stack holds indexes of hist[] array
    	//The bars stored in stack are always in increasing order of their heights.
		Stack<Integer> stack = new Stack<Integer>();
		int maxArea = 0;
		int top; //To store top of stack
		int areaWithTop; //To store area with top bar as the smallest bar

		int i = 0;
		while(i < heights.length) {
			//If this bar is higher than the bar on top stack, push it to stack
			if(stack.size() == 0 || heights[i] >= heights[stack.peek()]) {
				stack.push(i);
				i++;
			}
			else {
				top = stack.pop();

				//Calculate the area with hist[tp] stack as smallest bar
				areaWithTop = heights[top] * (stack.size() == 0 ? i : i-stack.peek()-1);

				//update max area
				if(maxArea < areaWithTop)
					maxArea = areaWithTop;
			}
		}

		//Now pop the remaining elements in stack
		while(stack.size() != 0) {
				top = stack.pop();
				areaWithTop = heights[top] * (stack.size() == 0 ? i : i-stack.peek()-1);
				if(maxArea < areaWithTop)
					maxArea = areaWithTop;
		}
		return maxArea;
	}
}