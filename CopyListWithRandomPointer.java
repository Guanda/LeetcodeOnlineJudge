/*
A linked list is given such that each node contains an additional random pointer which could point 
to any node in the list or null.

Return a deep copy of the list.

Analysis:

	Method 1:
	Use hashmap to store the connection between node and copy node

	Method 2:
	The idea is to associate the original node with its copy node in a single linked list. In this way, 
	we don't need extra space to keep track of the new nodes.

*/
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
class CopyListWithRandomPointer {
	//Method 1:
	public RandomListNode copyRandomList(RandomListNode head) {
		HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();

		//loop 1, copy all the nodes
		RandomListNode node = head;
		while(node != null) {
			map.put(node, new RandomListNode(node.label));
			node = node.next;
		}

		//loop 2, assign next and random pointer
		node = head;
		while(node != null) {
			map.get(node).next = map.get(node.next);
			map.get(node).random = map.get(node.random);
			node = node.next;
		}

		return map.get(head);
	}

	//Method 2:
	public RandomListNode copyRandomListBetter(RandomListNode head) {
		RandomListNode iter = head;

		// First round: make copy of each node,
		// and link them together side-by-side in a single list.
		while (iter != null) {
			RandomListNode next = iter.next;

			RandomListNode copy = new RandomListNode(iter.label);
			iter.next = copy;
			copy.next = next;

			iter = next;
		}

		// Second round: assign random pointers for the copy nodes.
		iter = head;
		while (iter != null) {
			if (iter.random != null) {
				iter.next.random = iter.random.next;
			}
			iter = iter.next.next;
		}

		// Third round: restore the original list, and extract the copy list.
		iter = head;
		RandomListNode pseudoHead = new RandomListNode(0);
		RandomListNode copy, copyIter = pseudoHead;

		while (iter != null) {
			RandomListNode next = iter.next.next;

			// extract the copy
			copy = iter.next;
			copyIter.next = copy;
			copyIter = copy;

			// restore the original list
			iter.next = next;

			iter = next;
		}

		return pseudoHead.next;
	}
}