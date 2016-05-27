/*
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

Anaylsis:
	Backtracking.

*/

class RestoreIpAddresses
{
	public List<String> restoreIpAddresses(String s)
	{
		List<String> res = new ArrayList<String>();

		//narrow the process when the string's length is not a possible ip address
		if(s.length() < 4 || s.length() > 12)
			return res;

		helper(s, "", res, 0);
		return res;
	}

	public void helper(String s, String tmp, List<String> res, int count)
	{
		//last section, also stop case
		if(count == 3 && isValid(s))
		{
			res.add(tmp + s);
			return;
		}

		for(int i = 1; i < 4 && i < s.length(); i++)
		{
			String substr = s.substring(0, i);
			if(isValid(substr))
			{
				helper(s.substring(i), tmp+substr+'.', res, count+1);
			}
		}
	}

	public boolean isValid(String s)
	{
		if(s.charAt(0) == '0')
			return s.equals("0");
		int num = Integer.parseInt(s);
		return num <= 255 && num > 0;
	}
}
