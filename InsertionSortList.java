/*
Sort a linked list using insertion sort.
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class InsertionSortList {
	public ListNode insertionSortList(ListNode head) {
		if(head == null)
			return null;
		ListNode dummy = new ListNode(0);
		ListNode prev = dummy;
		ListNode curr = head;

		while(curr != null) {
			ListNode next = curr.next;
			prev = dummy;
			while(prev.next != null && prev.next.val <= curr.val) {
				prev = prev.next;
			}
			curr.next = prev.next;
			prev.next = curr;
			curr = next;
		}
		return dummy.next;
	}
}