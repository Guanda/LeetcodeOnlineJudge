/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a 
letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.

Notes:
You may assume pattern contains only lowercase letters, and str contains 
lowercase letters separated by a single space.

Analysis:
	Using hashmap to store each word and pattern indicater

*/

public class WordPattern
{
	//Method 1
	public boolean wordPattern(String pattern, String str)
	{
		String[] words = str.split(" ");
		if(words.length != pattern.length())
			return false;

		HashMap<Character, String> map = new HashMap<>();
		Set<String> set = new HashSet<>();

		int i = 0;
		for(String word : words) {
			char c = pattern.charAt(i);
			// 如果该字符产生过映射
			if(map.containsKey(c)) {
				// 且映射的字符串和当前字符串不一样
				if(!word.equals(map.get(c)))
					return false;
			}
			else {
				// 如果该字符没有产生过映射
				// 如果当前字符串已经被映射过了
				if(set.contains(word))
					return false;

				// 否则新加一组映射
				map.put(c, word);
				set.add(word);
			}
			i++;
		}
		return true;
	}

	//Method 2, but not easy to understand
	public boolean wordPattern2(String pattern, String str)
	{
		String[] words = str.split(" ");
		if(words.length != pattern.length())
			return false;

		HashMap map = new HashMap();
		for(int i = 0; i < words.length; i++) {
			if(!Objects.equals(map.put(pattern.charAt(i), i), map.put(words[i], i))) {
				return false;
			}
		}
		return true;
	}
}