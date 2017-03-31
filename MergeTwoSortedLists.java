/*
	Merge two sorted linked lists and return it as a new list. 
	The new list should be made by splicing together the nodes of 
	the first two lists.
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

class MergeTwoSortedLists {
	public ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
		ListNode tmp = new ListNode(0);
		ListNode prev = tmp;

		while(l1 != null && l2 != null) {
			if(l1.val > l2.val) {
				prev.next = l2;
				l2 = l2.next;
			}
			else {
				prev.next = l1;
				l1 = l1.next;
			}
			prev = prev.next;
		}
		if(l1 != null)
			prev.next = l1;
		else if(l2 != null)
			prev.next = l2;

		return tmp.next;
	}
}