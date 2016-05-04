/*
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

Corner Cases:
1. Did you consider the case where path = "/../"?
	In this case, you should return "/".
2. Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
	In this case, you should ignore redundant slashes and return "/home/foo".

Analysis
	Method 1. Use stack, when "..", pop and when ".", skip
	Method 2. no extra space needed, use pointer last to keep tracking the splited array

*/

class SimplifyPath
{
	//Method 1
	public String simplifyPath(String path)
	{
		if(path == null || path.charAt(0) != '/')
			return null;

		//split by "/"
		String[] splits = path.split("/");

		//simplify
		Stack stack = new Stack();
		for(String s : splits)
		{
			if(s.equals("..") && !stack.isEmpty())
			{
				stack.pop();
			}
			else if(!s.equals(".") && !s.equals("..") && !s.isEmpty())
			{
				stack.push(s);
			}
		}

		//print new path
		if(stack.isEmpty())
			return "/";
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty())
		{
			sb.insert(0, "/" + stack.pop());
		}
		return sb.toString();
	}


	//Method 2
	public String simplifyPath(String path)
	{
		if(path == null || path.charAt(0) != '/')
			return null;

		//split by "/"
		String[] splits = path.split("/");

		//simplify
		int last = 0;
		for(String s : splits)
		{
			if(s.equals("..") && last > 0)
			{
				last--;
			}
			else if(!s.equals(".") && !s.equals("..") && !s.isEmpty())
			{
				splits[last] = s;
				last++;
			}
		}

		//print new path
		if(last == 0)
			return "/";
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < last; i++)
		{
			sb.append("/" + splits[i]);
		}
		return sb.toString();
	}
}
