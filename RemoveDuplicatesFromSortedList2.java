/*
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.

Analysis:
Remember the special case that the head node has duplicates.

*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class RemoveDuplicatesFromSortedList2
{
	public ListNode deleteDuplicates(ListNode head)
	{
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode prev = dummy;
		ListNode curr = head;

		while(curr != null && curr.next != null)
		{
			if(curr.next.val != curr.val)
			{
				prev = curr;
			}
			else
			{
				while(curr.next != null && curr.next.val == prev.next.val)
				{
					curr = curr.next;
				}
				prev.next = curr.next;
			}
			curr = curr.next;
		}
		return dummy.next;
	}
}