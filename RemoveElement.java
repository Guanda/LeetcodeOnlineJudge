/*
Given an array and a value, remove all instances of that value in place and return the new length.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

*/

class RemoveElement
{
	public int removeElement(int[] A, int elem)
	{
		int count = 0;
		for(int i = 0; i < A.length; i++)
		{
			if(A[i] == elem)
				count++;
			//remember we need remove the element, so move the good element one by one
			else if(count > 0)
				A[i - count] = A[i];
		}
		return A.length - count;
	}
}