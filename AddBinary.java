/*
Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
*/

class AddBinary
{
	public String addBinary(String a, String b)
	{
		if(a == null || a.length() == 0)
			return b;
		if(b == null || b.length() == 0)
			return a;

		StringBuilder sb = new StringBuilder();
		int carry = 0;
		int lastA = a.length() -1;
		int lastB = b.length() -1;

		while(lastA >= 0 || lastB >= 0 || carry > 0)
		{
			int num1 = 0;
			int num2 = 0;
			if(lastA >= 0)
				num1 = a.charAt(lastA--) - '0';
			if(lastB >= 0)
				num2 = b.charAt(lastB--) - '0';

			int current = (num1 + num2 + carry) % 2;
			carry = (num1 + num2 + carry) / 2;

			//every time insert start from position 0 to make sure the sequence correct
			sb.insert(0, current);
		}
		return sb.toString();
	}
}