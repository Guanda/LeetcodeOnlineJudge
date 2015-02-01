/*
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons).

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].


Analysis:
1. Use recursive method to solve this problem.
2. Normal iteration.

*/

public class LetterCombinations
{
	private HashMap<Character, String> map = new HashMap<Character, String>();

	private void setup()
	{
		map.put('0', "");
		map.put('1', "");
		map.put('2', "abc");
		map.put('3', "def");
		map.put('4', "ghi");
		map.put('5', "jkl");
		map.put('6', "mno");
		map.put('7', "pqrs");
		map.put('8', "tuv");
		map.put('9', "wxyz");
	}

	//Method 1: recursion
	public ArrayList<String> letterCombinations(String digits)
	{
		setup();
		ArrayList<String> result = new ArrayList<String>();
		recursion(digits, 0, new String(), result);
		return result;
	}

	private void recursion(String digits, int start, String res, ArrayList<String> result)
	{
		if(res.length() > digits.length())
			return;
		else if(res.length() == digits.length())
			result.add(res);
		else
		{
			for(int i = start; i < digits.length(); i++)
			{
				String letters = map.get(digits.charAt(i));
				for(int j = 0; j < letters.length(); j++)
				{
					recursion(digits, i + 1, res + letters.charAt(j), result);
				}
			}
		}
	}


	//Method 2:
    public ArrayList<String> letterCombinations(String digits) 
    {
	    setup();
	    ArrayList<String> res = new ArrayList<String>();
	    ArrayList<String> preres = new ArrayList<String>();
	    res.add("");

	    for(int i=0;i<digits.length();i++)
	    {
	        for(String str: res)
	        {
	            String letters = map.get(digits.charAt(i));
	            for(int j=0;j<letters.length();j++)
	                preres.add(str+letters.charAt(j));
	        }
	        res = preres;
	        preres = new ArrayList<String>();
	    }      
	    return res;
    }
}

