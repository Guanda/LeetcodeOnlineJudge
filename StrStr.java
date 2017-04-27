/*
Implement strStr().
Returns the index of the first occurrence of needle in haystack, 
or -1 if needle is not part of haystack.

(Check if a string existing in another string)

Analysis:
	Time complexity O(m*n), because substring
	(better solution: Rabin-Karp)
*/

class StrStr() {
	//Method 1: native
	public int strStr(String haystack, String needle) {
		if(haystack == null || needle == null)
			return -1;

		int start = 0;
		int len = needle.length();

		while(start + len <= haystack.length()) {
			if(haystack.substring(start, start + len).equals(needle)) {
				return start;
			}
			start++;
		}
		return -1;
	}


	//Method 2: Rabin-Karp
	public int BASE = 1000000; //hash function base size, for mod purpose
	public int strStr(String source, String target) {
		if(source == null || target == null) {
			return -1;
		}

		int len = target.length();
		if(len == 0) {
			return 0;
		}

		//31^len, 为后面补上一个char之后减去前面的char
		int power = 1;
		for(int i = 0; i < len; i++) {
			power = (power * 31) % BASE;
		}

		int targetCode = 0;
		for(int i = 0; i < len; i++) {
			targetCode = (targetCode * 31 + target.charAt(i)) % BASE;
		}

		int hashCode = 0;
		for(int i = 0; i < source.length(); i++) {
			//abc + d
			hashCode = (hashCode * 31 + source.charAt(i)) % BASE;

			//如果不够target长度的字符，继续计算，不需要减去前面的字符
			if(i < len - 1) {
				continue;
			}

			//i = len 的情况不需要加减, 但仍然需要最后的double check步骤， 所以不能直接continue

			//abcd - a
			if(i >= len) {
				hashCode = (hashCode - source.charAt(i-len) * power) % BASE;
				if(hashCode < 0) {
					hashCode += BASE;
				}
			}

			//double check the string
			if(hashCode == targetCode) {
				if(source.substring(i-len+1, i+1).equals(target)) {
					return i - len + 1;
				}
			}
		}
		return -1;
	}
}