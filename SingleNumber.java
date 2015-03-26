/*
Given an array of integers, every element appears twice except for one. Find that single one.
*/

class SingleNumber
{
	//Method 1: hash table
	public int singleNumber(int[] A)
	{
		Hashtable<Integer, Integer> ht = new Hashtable<Integer, Integer>();
		for(int i = 0; i < A.length; i++)
		{
			if(!ht.containsKey(A[i]))
			{
				ht.put(1, A[i]);
			}
			else
			{
				ht.put(2, A[i]);
			}
		}
		int result = ht.get(1);
		return result;
	}

	//Method 2: bit manipulation, XOR
	//consider every bit, if appears twice, it will return 0 finially, otherwise return 1.
	//so the finial result will be the integer which only appears once.
	public int singleNumber(int[] A)
	{
		int x = 0;
		for(int a : A)
		{
			x = x ^ a;
		}
		return x;
	}
}