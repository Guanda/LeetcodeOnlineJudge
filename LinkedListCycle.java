/*
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?

Analysis:
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

		while(fast != null && fast.next != null)
		{
			slow = slow.next;
			fast = fast.next.next;

			if(slow == fast)
				return true;
		}
		return false;
	}
}