/*
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and ","" as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:
       1
      / \
     /   \
    0 --- 2
         / \
         \_/
*/
/**
* Definition for undirected graph.
* class UndirectedGraphNode {
*     int label;
*     List<UndirectedGraphNode> neighbors;
*     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
* };
*/

class CloneGraph {
	//BFS
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if(node == null)
            return null;
        
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        UndirectedGraphNode newHead = new UndirectedGraphNode(node.label);
        
        queue.add(node);
        map.put(node, newHead);
        
        while(!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.pop();
            List<UndirectedGraphNode> currNeighbors = curr.neighbors;
            for(UndirectedGraphNode aNeighbors : currNeighbors) {
            	if(!map.containsKey(aNeighbors)) {
            		UndirectedGraphNode copy = new UndirectedGraphNode(aNeighbors.label);
            		map.put(aNeighbors, copy);
            		map.get(curr).neighbors.add(copy);
            		queue.add(aNeighbors);
            	}
            	//针对self-cycle的情况
            	else {
            		map.get(curr).neighbors.add(map.get(aNeighbors));
            	}
            }
        }
        return newHead;
	}


	//DFS, recursive
	public UndirectedGraphNode cloneGraphDFS(UndirectedGraphNode node) {
		if(node == null)
			return null;

		UndirectedGraphNode newHead = new UndirectedGraphNode(node.label);
		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

		map.put(node, newHead);
		DFS(node, map);

		return newHead;
	}

	public void DFS(UndirectedGraphNode node, HashMap<UndirectedGraphNode, UndirectedGraphNode> map) {
		if(node == null)
			return;

		for(UndirectedGraphNode aNeighbors : node.neighbors) {
			if(!map.containsKey(aNeighbors)) {
				UndirectedGraphNode copy = new UndirectedGraphNode(aNeighbors.label);
				map.put(aNeighbors, copy);
				DFS(aNeighbors, map);
			}
			map.get(node).neighbors.add(map.get(aNeighbors));
		}
	}


	//DFS, stack
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if(node == null)
			return null;

		UndirectedGraphNode newHead = new UndirectedGraphNode(node.label);
		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
		LinkedList<UndirectedGraphNode> stack = new LinkedList<UndirectedGraphNode>();

		map.put(node, newHead);
		stack.push(node);

		while(!stack.isEmpty()) {
			UndirectedGraphNode curr = stack.pop();
			for(UndirectedGraphNode aNeighbors : curr.neighbors) {
				if(!map.containsKey(aNeighbors)) {
					stack.push(aNeighbors);
					UndirectedGraphNode copy = new UndirectedGraphNode(aNeighbors.label);
					map.put(aNeighbors, copy);
				}
				map.get(curr).neighbors.add(map.get(aNeighbors));
			}
		}
		return newHead;
	}
}