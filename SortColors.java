/*
Given an array with n objects colored red, white or blue, sort them so that objects of the same 
color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

Analysis
	A rather straight forward solution is a two-pass algorithm using counting sort.
	1) Iterate the array counting number of 0's, 1's, and 2's
	2) Overwrite array with total number of 0's, then 1's and followed by 2's.

*/

class sortColors
{
	private static final int RED = 0;
	private static final int WHITE = 1;
	private static final int BLUE = 2;

	public void sortColors(int[] A)
	{
		//one pass for counting
		int[] count = new int[3];
		for(int color : A)
		{
			count[color]++;
		}

		//find the index of new array where white start
		count[WHITE] = count[WHITE] + count[RED];

		//fill up the array
		int i = 0;
		for(; i < count[RED]; i++)
			A[i] = RED;

		for(; i < count[WHITE]; i++)
			A[i] = WHITE;

		for(; i < A.length; i++)
			A[i] = BLUE;
	}
}
