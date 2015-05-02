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
class InsertionSortList
{
	public ListNode insertionSortList(ListNode head)
	{
		if(head == null)
			return null;
		ListNode helper = new ListNode(0);
		ListNode prev = helper;
		ListNode curr = head;

		while(curr != null)
		{
			ListNode next = curr.next;
			prev = helper;
			while(prev.next != null && prev.next.val <= curr.val)
			{
				prev = prev.next;
			}
			curr.next = prev.next;
			prev.next = curr;
			curr = next;
		}
		return helper.next;
	}
}