/*
Given an array of words and a length L, format the text such that each line 
has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words 
as you can in each line. Pad extra spaces ' ' when necessary so that each line 
has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the 
number of spaces on a line do not divide evenly between words, the empty slots 
on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is 
inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

Analysis:
	Find the last word in each line and do middle justified or left justified
*/

class TextJustification {
	public List<String> fullJustify(String[] words, int maxWidth) {
		List<String> result = new ArrayList<String>();
		if(words == null || words.length == 0 || maxWidth < 0)
			return result;

		if(maxWidth == 0) {
			result.add("");
			return result;
		}

		int start = 0;
		while(start < words.length) {
			int count = words[start].length();
			int last = start + 1;
			//find the last word in each line
			while(last < words.length) {
				if(words[last].length() + count + 1 > maxWidth)
					break;
				count += words[last].length() + 1;
				last++;
			}

			StringBuilder builder = new StringBuilder();
			//the diff is how many spack bucket in each line, in example, it is 2 which is 3-0-1.
			int diff = last - start - 1;
			//if last line or number of words in the line is 1, left-justified
			if(last == words.length || diff == 0) {
				for(int i = start; i < last; i++) {
					builder.append(words[i] + " ");
				}
				//delete the last space
				builder.deleteCharAt(builder.length() - 1);
				for(int i = builder.length(); i < maxWidth; i++) {
					builder.append(" ");
				}
			}
			//middle justified 
			else {
				int avgSpace = (maxWidth - count) / diff;
				int moreSpace = (maxWidth - count) % diff;
				for(int i = start; i < last; i++) {
				    builder.append(words[i]);
                    if (i < last - 1) {
                        for (int j = 0; j <= (avgSpace + ((i - start) < moreSpace ? 1 : 0)); j++) {
                            builder.append(" ");
                        }
                    }
				}
			}
			result.add(builder.toString());
			start = last;
		}
		return result;
	}
}
