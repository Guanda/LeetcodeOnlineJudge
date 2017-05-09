/*
There are a total of n courses you have to take, labeled from 0 to n - 1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, 
which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:
2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 
you should also have finished course 1. So it is impossible.

Analysis:
	Topological Sorting, Using BFS to check finally if the count(edges) and numCourses are equal
*/
class CourseSchedule {
	//using matrix to process prerequisites, which is not perfect
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		int[][] matrix = new int[numCourses][numCourses];
		int[] indegree = new int[numCourses];

		//initial the prerequisites to matrix
		for(int i = 0; i < prerequisites.length; i++) {
			int ready = prerequisites[i][0];
			int pre = prerequisites[i][1];
			//check duplicate case
			if(matrix[pre][ready] == 0) {
				indegree[ready]++;
			}
			matrix[pre][ready] = 1;
		}

		//ready for BFS, add all nodes which have no indegree to queue
		int count = 0;
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i = 0; i < indegree.length; i++) {
			if(indegree[i] == 0)
				queue.add(i);
		}

		//calculate the count, remove nodes and indegree and also add the new nodes
		while(!queue.isEmpty()) {
			int course = queue.poll();
			count++;
			for(int i = 0; i < numCourses; i++) {
				if(matrix[course][i] != 0) {
					indegree[i]--;
					if(indegree[i] == 0) {
						queue.add(i);
					}
				}
			}
		}
		return count == numCourses;
	}


	//using hashmap or array of list to process prerequisites
	public boolean canFinish(int numCourses, int[][] prerequisites) {
        //process prerequisites
        int[] indegree = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < prerequisites.length; i++) {
            int ready = prerequisites[i][0];
            int pre = prerequisites[i][1];
            
            if(!map.containsKey(pre)) {
                List<Integer> list = new ArrayList<>();
                list.add(ready);
                map.put(pre, list);
            }
            else {
                map.get(pre).add(ready);
            }
            indegree[ready]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        //add all courses which indegree is 0 to queue
        for(int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int count = 0;
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            count++;
            if(map.get(curr) != null) {
                int size = map.get(curr).size();
                for(int i = 0; i < size; i++) {
                    int neighbor = map.get(curr).get(i);
                    indegree[neighbor]--;
                    if(indegree[neighbor] == 0) {
                        queue.offer(neighbor);
                    }
                }   
            }
        }
        
        return count == numCourses;
    }
}