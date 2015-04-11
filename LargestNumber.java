/*
Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.

Anaylsis:
	Using comparator to compare two string's combination

*/

class LargestNumber
{
	public String largestNumber(int[] num)
	{
		if(num == null || num.length == 0)
			return "";

		String[] array = new String[num.length];
		for(int i = 0; i < num.length; i++)
		{
			array[i] = String.valueOf(num[i]);
		}

		Arrays.sort(array, comparator);

		String result = "";
		for(String s : array)
		{
			//make the later one is before the previous one, largest number
			result = s + result;
		}

		int j = 0;
		while(j < array.length - 1)
		{
			if(result.charAt(j) != '0')
				break;

			j++;
		}

		return result.substring(j);
	}

	public static Comparator<String> comparator = new Comparator<String>()
	{
		@override
		public int compare(String s1, String s2)
		{
			String comb1 = s1 + s2;
			String comb2 = s2 + s1;

			//ascending order
			return comb1.compareTo(comb2);
		}
	};
}