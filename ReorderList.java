/*
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.

Analysis:
1. Break list in the middle to two lists(use slow and fast pointer)
2. Reverse the order of the second list
3. Merge two lists back together

*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class ReorderList
{
	public void reorderList(ListNode head)
	{
		if(head == null || head.next == null)
			return;

		ListNode slow = head;
		ListNode fast = head;

		//break the list to two lists in the middle
		while(fast != null && fast.next != null && fast.next.next != null)
		{
			slow = slow.next;
			fast = fast.next.next;
		}

		//start the second part
		ListNode second = slow.next;
		//close the first part
		slow.next = null;

		//reverse order for second part
		second = reverseOrder(second);

		ListNode p1 = head;
		ListNode p2 = second;

		//merge two lists here
		while(p2 != null)
		{
			ListNode tmp1 = p1.next;
			ListNode tmp2 = p2.next;

			p1.next = p2;
			p2.next = tmp1;
			p1 = tmp1;
			p2 = tmp2;
		}
	}

	public ListNode reverseOrder(ListNode head)
	{
		if(head == null || head.next == null)
			return head;

		ListNode prev = null;
		ListNode curr = head;
		ListNode next = null;

		while(curr != null)
		{
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev; 
	}
}
