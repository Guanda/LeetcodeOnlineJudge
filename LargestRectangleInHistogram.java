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
	For every bar ‘x’, we calculate the area with ‘x’ as the smallest bar in the rectangle. 
	If we calculate such area for every bar ‘x’ and find the maximum of all areas, our task 
	is done. How to calculate area with ‘x’ as smallest bar? We need to know index of the first 
	smaller (smaller than ‘x’) bar on left of ‘x’ and index of first smaller bar on right of ‘x’. 
	Let us call these indexes as ‘left index’ and ‘right index’ respectively.

	We traverse all bars from left to right, maintain a stack of bars. Every bar is pushed to stack once. 
	A bar is popped from stack when a bar of smaller height is seen. When a bar is popped, we calculate 
	the area with the popped bar as smallest bar. How do we get left and right indexes of the popped 
	bar – the current index tells us the ‘right index’ and index of previous item in stack is the ‘left index’. 
	Following is the complete algorithm.

	1) Create an empty stack.

	2) Start from first bar, and do following for every bar heights[i]’ where ‘i’ varies from 0 to n-1.
	……a) If stack is empty or heights[i] is higher than the bar at top of stack, then push ‘i’ to stack.
	……b) If this bar is smaller than the top of stack, then keep removing the top of stack while top of the stack 
	     is greater. Let the removed bar be heights[tp]. Calculate area of rectangle with heights[tp] as smallest bar. 
	     For heights[tp], the ‘left index’ is previous (previous to tp) item in stack and ‘right index’ is ‘i’ 
	     (current index).

	3) If the stack is not empty, then one by one remove all bars from stack and do step 2.b for every removed bar.
*/

class LargestRectangleInHistogram {
	public int largestRectangleArea (int[] heights) {
		//The stack holds indexes of heights[] array
    	//The bars stored in stack are always in increasing order of their heights.
        if(height ==  null || height.length == 0) {
            return 0;
        }
        
        int max = 0;
        int i = 0;
        Stack<Integer> stack = new Stack<>();
        for(; i < height.length; i++) {
            while(!stack.isEmpty() && height[i] < height[stack.peek()]) {
                int w = 0;
                int h = height[stack.pop()];
                if(!stack.isEmpty()) {
                    w = i - stack.peek() - 1;
                }
                else {
                    w = i;
                }
                max = Math.max(max, w * h);
            }
            stack.push(i);
        }
        
        while(!stack.isEmpty()) {
            int w = 0;
            int h = height[stack.pop()];
            if(!stack.isEmpty()) {
                w = i - stack.peek() - 1;
            }
            else {
                w = i;
            }
            max = Math.max(max, w * h);
        }
        
        return max;
	}
}