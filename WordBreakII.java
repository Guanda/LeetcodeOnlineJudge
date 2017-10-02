/*
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence 
where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
*/

class WordBreakII {

	HashMap<String, List<String>> map = new HashMap<String, List<String>>();

	public List<String> wordBreak(String s, Set<String> wordDict) {
		List<String> res = new ArrayList<String>();
		if(s == null || s.length() == 0)
			return res;

		if(map.containsKey(s))
			return map.get(s);

		if(wordDict.contains(s))
			res.add(s);

		for(int i = 1; i < s.length(); i++) {
			String t = s.substring(0, i);
			if(wordDict.contains(t)) {
				List<String> tmp = wordBreak(s.substring(i), wordDict);
				if(tmp.size() != 0) {
					for(int j = 0; j < tmp.size(); j++) {
						res.add(t + " " + tmp.get(j));
					}
				}
			}
		}
		map.put(s, res);

		return res;
	}

	// The version without DFS memorization
	public List<String> wordBreakTLE(String s, Set<String> wordDict) {
		List<String> res = new ArrayList<String>();
		if(s == null || s.length() == 0) {
			return res;
		}

		if(wordDict.contains(s)) {
			res.add(s);
		}

		for(int i = 1; i < s.length(); i++) {
			String t = s.substring(0, i);
			if(wordDict.contains(t)) {
				List<String> tmp = wordBreakTLE(s.substring(i), wordDict);
				if(tmp.size() != 0) {
					for(int j = 0; j < tmp.size(); j++) {
						res.add(t + " " + tmp.get(j));
					}
				}
			}
		}

		return res;
	}
}