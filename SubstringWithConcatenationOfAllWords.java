/*
You are given a string, s, and a list of words, words, that are all of the same length. 
Find all starting indices of substring(s) in s that is a concatenation of each word in 
words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).

*/

class SubstringWithConcatenationOfAllWords {
	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> result = new ArrayList<Integer>();

		if(s == null || s.length() == 0 || words == null || words.length == 0)
			return result;

		//record the frequency of words
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(String word : words) {
			if(map.containsKey(word)) {
				map.put(word, map.get(word) + 1);
			}
			else {
				map.put(word, 1);
			}
		}

		int len = words[0].length();
		for(int i = 0; i <= s.length() - len * words.length; i++) {
			HashMap<String, Integer> currentMap = new HashMap<String, Integer>();
			int j = i;
			for(; j < i + len * words.length; j = j + len) {
				String sub = s.substring(j, j + len);
				if(map.containsKey(sub)) {
					if(currentMap.containsKey(sub)) {
						currentMap.put(sub, currentMap.get(sub) + 1);
					}
					else {
						currentMap.put(sub, 1);
					}
					
					if(currentMap.get(sub) > map.get(sub))
						break;
				}
				else {
					break;
				}
			}
			if(j == i + len * words.length)
				result.add(i);
		}

		return result;
	}
}