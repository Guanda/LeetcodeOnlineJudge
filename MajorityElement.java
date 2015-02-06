/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Analysis:
1. Sort array and find the median.

2. Use hashmap.

3. One loop:
Set the first element of the array as the majority  and count=1, and go to the next element,
~if the next element is same with the majority, set the count +1 and if the count>n/2, return majority;
~if different, set the count-1, if the count=0, set next element as the majority and count be 1.
~return the majority.

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

	//Method 3: count as majority
	public int majorityElement3(int[] num)
	{
		int count = 0;
		int result = num[0];
		for(int i = 0; i < num.length; i++)
		{
			if(count == 0)
			{
				result = num[i];
				count = 1;
				continue;
			}

			if(num[i] != result)
			{
				count--;
			}
			else
			{
				count++;
				if(count > num.length / 2)
					return result;
			}
		}
		return result;
	}
}