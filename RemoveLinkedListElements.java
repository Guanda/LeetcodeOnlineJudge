/*
Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5

Analysis:
	1. normal iteration, use two pointer
	2. recursive
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class RemoveLinkedListElements
{
	//Method 1
	public ListNode removeElements(ListNode head, int val)
	{
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode pre = dummy;
		ListNode node = head;

		while(node != null)
		{
			if(node.val == val)
			{
				pre.next = node.next;
				node = node.next;
			}
			else
			{
				pre = pre.next;
				node = node.next;
			}
		}
		return dummy.next;
	}

	//Method 2
	public ListNode removeElements(ListNode head, int val)
	{
		if(head == null)
			return null;

		head.next = removeElements(head.next, val);
		return head.val == val ? head.next : head;
	}
}
