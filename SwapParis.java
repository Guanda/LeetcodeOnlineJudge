/*
Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.

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

public class SwapPairs
{
	public ListNode swapPairs(ListNode head)
	{
		ListNode pre = new ListNode(0);
		pre.next = head;
		ListNode l1 = pre;
		ListNode l2 = head;

		while(l2 != null && l2.next != null)
		{
			ListNode tmp = l2.next.next;
			l2.next.next = l1.next;
			l1.next = l2.next;
			l2.next = tmp;
			l1 = l2;
			l2 = l1.next;
		}
		return pre.next;
	}
}