/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 6.

Analysis:

Method 1:
	This question is similar as [Largest Rectangle in Histogram]:

	You can maintain a row length of Integer array H recorded its height of '1's, and scan and update 
	row by row to find out the largest rectangle of each row.

	For each row, if matrix[row][i] == '1'. H[i] +=1, or reset the H[i] to zero.
	and accroding the algorithm of [Largest Rectangle in Histogram], to update the maximum area.

Method 2:
	The DP solution proceeds row by row, starting from the first row. Let the maximal rectangle area at row i 
	and column j be computed by [right(i,j) - left(i,j)]*height(i,j).

	All the 3 variables left, right, and height can be determined by the information from previous row, and 
	also information from the current row. So it can be regarded as a DP solution. The transition equations are:

	left(i,j) = max(left(i-1,j), cur_left), cur_left can be determined from the current row
	right(i,j) = min(right(i-1,j), cur_right), cur_right can be determined from the current row
	height(i,j) = height(i-1,j) + 1, if matrix[i][j]=='1';
	height(i,j) = 0, if matrix[i][j]=='0'

*/

class MaxRectangle {
	//Method 1
	public int maxRectangle(char[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;
		int rLen = matrix.length;
		int cLen = matrix[0].length;
		int[] h = new int[cLen+1];
		int maxArea = 0;

		for(int row = 0; row < rLen; row++) {
			Stack<Integer> stack = new Stack<Integer>();
			for(int i = 0; i < cLen+1; i++) {
			    if(i < cLen) {
    				if(matrix[row][i] == '1') {
    					h[i] += 1;
    				}
    				else {
    					h[i] = 0;
    				}			        
			    }

				if(stack.size() == 0 || h[stack.peek()] <= h[i]) {
					stack.push(i);
				}
				else {
					while(stack.size() != 0 && h[i] < h[stack.peek()]) {
						int top = stack.pop();
						int areaWithTop = h[top] * (stack.size() == 0 ? i : i - stack.peek() - 1);
						if(areaWithTop > maxArea)
							maxArea = areaWithTop;
					}
					stack.push(i);
				}
			}
		}
		return maxArea;
	}

	//Method 2
	public int maxRectangleDP(char[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;
		int rLen = matrix.length;
		int cLen = matrix[0].length;

		//prepare the dp arrays
		int[] left = new int[cLen];
		int[] right = new int[cLen];
		int[] height = new int[cLen];
		Arrays.fill(left, 0);
		Arrays.fill(right, cLen);
		Arrays.fill(height, 0);

		int max = 0;
		for(int i = 0; i < rLen; i++) {
			int cur_left = 0;
			int cur_right = cLen;
			//compute height (can do this from either side)
			for(int j = 0; j < cLen; j++) {
				if(matrix[i][j] == '1')
					height[j]++;
				else
					height[j] = 0;
			}
			//compute left (from left to right)
			for(int j = 0; j < cLen; j++) {
				if(matrix[i][j] == '1')
					left[j] = Math.max(left[j], cur_left);
				else {
					left[j] = 0;
					cur_left = j+1;
				}
			}
			//compute right (from right to left)
			for(int j = cLen-1; j >= 0; j--) {
				if(matrix[i][j] == '1') {
					right[j] = Math.min(right[j], cur_right);
				}
				else {
					right[j] = cLen;
					cur_right = j;
				}
			}

			//compute the area of rectangle (can do this from either side)
			for(int j = 0; j < cLen; j++) {
				max = Math.max(max, height[j] * (right[j] - left[j]));
			}
		}
		return max;
	}
}

