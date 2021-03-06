/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Analysis:
	Use PriorityQueue
	Time complexity: n*log(k)
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class MergeKSortedLists {
	public ListNode mergeKLists(ListNode[] lists) {
		if(lists == null || lists.length == 0)
			return null;

		PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
			@Override
			public int compare(ListNode a, ListNode b) {
				return a.val - b.val;
			}
		});

		for(ListNode list : lists) {
			if(list != null)
				queue.add(list);
		}

		ListNode head = new ListNode(0);
		ListNode p = head;
		while(queue.size() > 0) {
			ListNode tmp = queue.poll();
			p.next = tmp;

			//keep adding next node in list
			if(tmp.next != null)
				queue.add(tmp.next);

			p = p.next;
		}

		return head.next;
	}
}
