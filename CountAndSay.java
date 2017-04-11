/*
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.
*/

class CountAndSay {
	public String countAndSay(int n) {
		if(n <= 1)
			return String.valueOf(1);
		else
			return say(countAndSay(n - 1));
	}

	private String say(String s) {
		int i = 0;
		int count = 1;
		StringBuffer sb = new StringBuffer();

		while(i < s.length()) {
			int j = i + 1;

			while(j < s.length() && s.charAt(j) == s.charAt(i)) {
				count++;
				j++;
			}

			sb.append(count);
			sb.append(s.charAt(i));

			i = j;
			count = 1;
		}
		return sb.toString();
	}
}