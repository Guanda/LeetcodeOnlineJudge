/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.

Analysis:
	Binary Search.
	First search for the row that may contain the target.
	Second search for the target in that row.
*/

class Search2DMatrix {
	public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        //first binary search to find out the row where target may be existing
        int row = 0;
        int startR = 0, endR = matrix.length - 1;
        while(startR + 1 < endR) {
            int midR = startR + (endR - startR) / 2;
            if(matrix[midR][0] == target) {
                return true;
            }
            else if(matrix[midR][0] < target) {
                startR = midR;
            }
            else {
                endR = midR;
            }
        }
        if(matrix[endR][0] <= target) {
            row = endR;
        }
        else if(matrix[startR][0] <= target) {
            row = startR;
        }
        else {
            return false;
        }
        
        //after above binary search, the target has to be "row"
        //do another binary search
        int startC = 0, endC = matrix[0].length - 1;
        while(startC + 1 < endC) {
            int midC = startC + (endC - startC) / 2;
            if(matrix[row][midC] == target) {
                return true;
            }
            else if(matrix[row][midC] < target) {
                startC = midC;
            }
            else {
                endC = midC;
            }
        }
        if(matrix[row][startC] == target) {
            return true;
        }
        else if(matrix[row][endC] == target) {
            return true;
        }
        
        return false;
	}
}