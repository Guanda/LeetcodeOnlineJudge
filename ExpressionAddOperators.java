/*
Given a string that contains only digits 0-9 and a target value, return all possibilities to 
add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []

Analysis:

This problem has a lot of edge cases to be considered:

1. overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum, we get over it.
2. 0 sequence: because we can't have numbers with multiple digits started with zero, we have to deal with it too.
3. a little trick is that we should save the value that is to be multiplied in the next recursion. (prev value)

*/
class ExpressionAddOperators {
	public List<String> addOperators(String num, int target) {
		List<String> result = new ArrayList<String>();
		if(num == null || num.length() == 0)
			return result;
		dfs(result, "", num, target, 0, 0, 0);
		return result;
	}

	// path: {string} expression generated so far.
    // pos: {int}    current visiting position of num.
    // value:  {long}   cumulative value so far.
    // prev:  {long}   previous operand value.
	public void dfs(List<String> result, String path, String num, int target, int pos, long value, long prev) {
		if(pos == num.length()) {
			if(target == value) {
				result.add(path);
			}
			return;
		}
		for(int i = pos; i < num.length(); i++) {
			if(i != pos && num.charAt(pos) == '0')
				break;
			long cur = Long.parseLong(num.substring(pos, i+1));
			if(pos == 0) {
				dfs(result, path+cur, num, target, i+1, cur, cur);
			}
			else {
				dfs(result, path+'+'+cur, num, target, i+1, value+cur, cur);
				dfs(result, path+'-'+cur, num, target, i+1, value-cur, -cur);
				dfs(result, path+'*'+cur, num, target, i+1, value-prev+prev*cur, prev*cur);
			}
		}
	}
}