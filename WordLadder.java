/*
Given two words (start and end), and a dictionary, find the length of shortest transformation 
sequence from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary

For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.

Analysis: BFS

*/

class WordLadder
{
	public int ladderLength(String start, String end, Set<String> dict)
	{
		LinkedList<String> wordQue = new LinkedList<String>();
		LinkedList<Integer> countQue = new LinkedList<Integer>();

		wordQue.add(start);
		countQue.add(1);
		while(!wordQue.isEmpty())
		{
			String analyzing = wordQue.poll();
			int currCount = countQue.poll();
			if(analyzing.equals(end))
				return currCount;

			for(int i = 0; i < analyzing.length(); i++)
			{
				for(char j = 'a'; j < 'z'; j++)
				{
					char[] possibleMatch = analyzing.toCharArray();
					possibleMatch[i] = j;
					String checkMatch = new String(possibleMatch);
					if(dict.contains(checkMatch))
					{
						//once the word is picked in path, we don't need it again
						dict.remove(checkMatch);
						countQue.add(currCount+1);
						wordQue.add(checkMatch);
					}
				}
			}
		}	
		return 0;
	}
}
