/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Follow up:
Can you solve it without using extra space?

Analysis:

	Solution: this is a follow-up question of Linked List Cycle; first find out the 
	meeting point by advancing fast pointer 2 steps a time and advancing slow pointer 
	1 step a time; It is to be noted that the distance between the meeting point and 
	the node where the cycle begins is the same as the distance between the head of the 
	linked list and the node where the cycle begins. Thus, after finding out the meeting 
	point, the only thing we need to do is to move the fast point to the head, and advance 
	both slow and fast points simultaneously 1 step a time; when those two pointers meet 
	again, we get the node where the cycle begins.

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
class LinkedListCycle2
{
	public ListNode detectCycle(ListNode head)
	{
		ListNode slow = head;
		ListNode fast = head;

		//find the meeting point. This will be loop_size - k steps into the list
		//k is the size between start of loop and head
		while(fast != null && fast.next != null)
		{
			slow = slow.next;
			fast = fast.next.next;
			if(slow == fast)
				break;
		}

		//no loop case
		if(fast == null || fast.next == null)
			return null;

		//move slow to head and make slow and fast move both 1 step, until they meet
		slow = head;
		while(slow != fast)
		{
			slow = slow.next;
			fast = fast.next;
		}
		return fast;
	}
}