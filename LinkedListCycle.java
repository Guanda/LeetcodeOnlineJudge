/*
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?

Anaylsis:
The idea is set two point, one move slow the other move fast, and if there is a cycle,
the slow and fast will meet finally, otherwise the next will be null, no loop;

*/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
class LinkedListCycle
{
	public boolean hasCycle(ListNode head)
	{
		if(head == null)
			return false;

		ListNode slow = head;
		ListNode fast = head;

		while(true)
		{
			slow = slow.next;
			if(fast.next != null)
			{
				fast = fast.next.next;
			}
			else
			{
				return false;
			}

			if(slow == null || fast == null)
				return false;

			if(slow == fast)
				return true;
		}
	}
}