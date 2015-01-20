/*
Determine whether an integer is a palindrome. Do this without extra space.

Some hints:
Could negative integers be palindromes? (ie, -1). I assume it is positive here.

If you are thinking of converting the integer to string, note the restriction of using extra space.

You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", 
you know that the reversed integer might overflow. How would you handle such case?

Analysis:

Consider that start from the first and last digit. We can get the first digit when we divide it in a 
loop. Then if they are the same, chopping them.

*/

class IsPalindrome
{
	public boolean isPalindrome(int x)
	{
		if(x < 0)
			return false;

		//figure out the 10s number of x
		int div = 1;
		while(x / div >= 10)
		{
			div = div * 10;
		}

		while(x != 0)
		{
			int l = x / div;
			int r = x % 10;
			if(l != r)
				return false;
			x = (x % div) / 10;
			div = div / 100;
		}

		return true;
	}
}