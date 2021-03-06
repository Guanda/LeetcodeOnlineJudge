/*
Given a board with m by n cells, each cell has an initial state live (1) or dead (0). 
Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the 
following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.

Follow up: 
1. Could you solve it in-place? Remember that the board needs to be updated at the same time: 
   You cannot update some cells first and then use their updated values to update other cells.

2. In this question, we represent the board using a 2D array. In principle, the board is infinite, 
   which would cause problems when the active area encroaches the border of the array. How would you 
   address these problems?

思路:
	最简单的方法是再建一个矩阵保存，不过当inplace解时，如果我们直接根据每个点周围的存活数量来修改当前值，由于矩阵是顺序遍历的，
	这样会影响到下一个点的计算。如何在修改值的同时又保证下一个点的计算不会被影响呢？实际上我们只要将值稍作编码就行了，因为题目
	给出的是一个int矩阵，大有空间可以利用。这里我们假设对于某个点，值的含义为

	0 : 上一轮是0，这一轮过后还是0
	1 : 上一轮是1，这一轮过后还是1
	2 : 上一轮是1，这一轮过后变为0
	3 : 上一轮是0，这一轮过后变为1

	这样，对于一个节点来说，如果它周边的点是1或者2，就说明那个点上一轮是活的。最后，在遍历一遍数组，把我们编码再解回去，
	即0和2都变回0，1和3都变回1，就行了。
*/
class GameOfLife {
	public void gameOfLife(int[][] board) {
		int m = board.length;
		int n = board[0].length;
		if(m == 0 || n == 0)
			return;

		int[] dirX = {-1, 0, 1, 0, -1, 1, 1, -1};
		int[] dirY = {0, -1, 0, 1, -1, -1, 1, 1};

		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				int lives = 0;
				for(int k = 0; k < 8; k++) {
					int ii = i + dirX[k];
					int jj = j + dirY[k];
					if(ii < 0 || ii >= m || jj < 0 || jj >= n) {
						continue;
					}
					if(board[ii][jj] == 1 || board[ii][jj] == 2) {
						lives++;
					}
				}

				//based on the neighbords' alive to update current point, 0 and 1 don't need update
				if(board[i][j] == 0 && lives == 3) {
					board[i][j] = 3;
				}
				else if(board[i][j] == 1 && (lives < 2 || lives > 3)) {
					board[i][j] = 2;
				}
			}
		}

		//decode
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				board[i][j] = board[i][j] % 2;
			}
		}
	}
}