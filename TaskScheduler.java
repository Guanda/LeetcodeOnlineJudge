/*
Given a char array representing tasks CPU need to do. It contains capital letters A to Z 
where different letters represent different tasks.Tasks could be done without original order. 
Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be 
at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example 1:
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
Note:
The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].

Analysis:
	Based on the frequency of each task, find out the most frequent one and calculate the time frame. Since
	this calculation need to take care of multiple most frequent ones. So the "count" is the remaining space.
	And it could be most tasks cannot fit into the frame we calculated before, so just use the length of tasks.
	That's why we have Math.max()
*/

class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        int[] frequency = new int[26];
        for(char c : tasks) {
        	frequency[c - 'A']++;
        }

        Arrays.sort(frequency);

        int count = 1;
        int last = frequency[25];
        for(int i = 24; i >= 0; i--) {
        	if(frequency[i] == last) {
        		count++;
        	}
        }

        return Math.max(tasks.length, (frequency[25] - 1) * (n + 1) + count);
    }
}