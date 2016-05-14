/*
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.

Analysis:
Should consider the k>length case, so make the k=k%length
Before cut, make it a loop

*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class RotateList
{
	public ListNode rotateRight(ListNode head, int k)
	{
		if(head == null || k == 0)
            		return head;

		//find the length of list
		//len start from 1 because curr.next!=null
		int len = 1;
		ListNode curr = head;
		while(curr.next != null)
		{
			len++;
			curr = curr.next;
		}
		//make it a loop
		curr.next = head;

		ListNode newHead = curr;

		//find the n-to-the-end, where is also the cur point
		//consider the case that k>len
		k = len - k % len;
		while(k > 0)
		{
			newHead = newHead.next;
			k--;
		}
		head = newHead.next;
		newHead.next = null;

		return head;
	}
}
