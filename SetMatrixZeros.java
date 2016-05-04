/*
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

Analysis:
	Method 1: store the rows and columns has zeros.
	We don't need store every zero cell, we just need to know which row or column has zeros.

	Method 2: 
	We have an entire matrix and thus we can use one row and one column of the matrix to store 
	the zero information. Before we revise the values in that row and column, we need to know 
	whether the original row/column contain zero. If so, we also need to set the row/column to 
	zeros; If not, leave other values as they are.

Note: also in Cracking Code Interview
*/

class SetMatrixZeros
{
	//Method 1, time: O(m*n), space: O(m+n)
	public void setZeros(int[][] matrix)
	{
		Set<Integer> rows = new HashSet<Integer>();
		Set<Integer> columns = new HashSet<Integer>();

		//find zeros
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix[0].length; j++)
			{
				if(matrix[i][j] == 0)
				{
					rows.add(i);
					columns.add(j);
				}
			}
		}

		//set zeros
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix[0].length; j++)
			{
				if(rows.contains(i) || columns.contains(j))
					matrix[i][j] = 0;
			}
		}
	}

	
	//Method 2, time: O(m*n), space: O(1)!!!!
	public void setZeros(int[][] matrix)
	{
		int rowLength = matrix.length;
		if(rowLength == 0)
			return;
		
		int columnLength = matrix[0].length;
		if(columnLength == 0)
			return;

		boolean hasZeroFirstRow = false;
		boolean hasZeroFirstColumn = false;

		//does first row have zeros?
		for(int i = 0; i < columnLength; i++)
		{
			if(matrix[0][i] == 0)
			{
				hasZeroFirstRow = true;
				break;
			}
		}

		//does first column have zeros?
		for(int i = 0; i < rowLength; i++)
		{
			if(matrix[i][0] == 0)
			{
				hasZeroFirstColumn = true;
				break;
			}
		}

		//find zeros and store the info in first row and column
		for(int i = 0; i < matrix.length; i++)
		{
			for(int j = 0; j < matrix[0].length; j++)
			{
				if(matrix[i][j] == 0)
				{
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}

		//set zeros except the first row and column
		for(int i = 1; i < matrix.length; i++)
		{
			for(int j = 1; j < matrix[0].length; j++)
			{
				if(matrix[i][0] == 0 || matrix[0][j] == 0)
					matrix[i][j] = 0;
			}
		}

		//set zeros for first row and column if needed
		if(hasZeroFirstRow)
		{
			for(int i = 0; i < columnLength; i++)
			{
				matrix[0][i] = 0;
			}
		}
		if(hasZeroFirstColumn)
		{
			for(int i = 0; i < rowLength; i++)
			{
				matrix[i][0] = 0;
			}
		}
	}
}
