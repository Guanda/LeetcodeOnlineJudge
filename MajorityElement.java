/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Analysis:
1. Sort array and find the median.
2. Use hashmap.

*/

public class MajorityElement
{
	//Method 1: sort array and find median
	public int majorityElement(int[] num)
	{
		Arrays.sort(num);
		int size = num.length;
		return num[size / 2 + 1];
	}

	//Method 2: hashmap
	public int majorityElement2(int[] num)
	{
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		int counter = num.length / 2;
		for(int i : num)
		{
			if(hm.containsKey(i))
			{
				hm.put(i, hm.get(i) + 1);
			}
			else
			{
				hm.put(i, 1);
			}
			if(hm.get(i) > counter)
				return i;
		}
		return -1;
	}
}