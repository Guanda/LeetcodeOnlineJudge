/*
All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, 
for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify 
repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur 
more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].

Anaylsis:
	Create a HashMap<bit representation of substring, appeared times>. For each 10 letter 
	window, calculate its bit representation as the key. Then slide the window from left 
	to right, if the key shows up exactly twice, append the substring to the result.

	For A, C, G, T, represent 2 base 0,1,2,3 which are all based on 2 bits. So for 10 letters, 
	will take 20 bits. Make sure every time operation shift left 2.
*/

class RepeatedDNASequences{
	public List<String> findRepeatedDnaSequences(String s){	
		List<String> result = new ArrayList<String>();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		for(int i = 0; i <= s.length() - 10; i++){
			String sub = s.substring(i, i+10);
			int key = getKey(sub);
			if(map.containsKey(key)){
				map.put(key, map.get(key)+1);
				if(map.get(key) == 2)
					result.add(sub);
			}
			else{
				map.put(key, 1);
			}
		}
		return result;
	}

	public int getKey(String s){
		int result = 0;
		for(int i = s.length()-1; i >= 0; i--){
			int b = 0;
			switch (s.charAt(i)){
				case 'A':
				b |= 0;
				break;

				case 'C':
				b |= 1;
				break;

				case 'G':
				b |= 2;
				break;

				case 'T':
				b |= 3;
				break;
			}
			result = result | b;
			result = result << 2;
		}
		return result;
	}

	// Solution 2: easier to understand. Use the return that hashset cannot add again
    public List<String> findRepeatedDnaSequences(String s) {
        Set seen = new HashSet(), repeated = new HashSet();
        for (int i = 0; i + 9 < s.length(); i++) {
            String ten = s.substring(i, i + 10);
            if (!seen.add(ten))
                repeated.add(ten);
        }
        return new ArrayList(repeated);
    }
}
