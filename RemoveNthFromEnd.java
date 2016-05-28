/*
Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.

Analysis:

Key point of this problem is to set up 2 pointers, and the distance between them is n.  
So that when the faster pointer reached the end of the linked list, the slower pointer  
point to the node prior to the node that need to be remove.

*/

class RemoveNthFromEnd
{
	public ListNode removeNthFromEnd(ListNode head, int n)
	{
		ListNode slow = head;
		ListNode fast = head;

		if(head.next == null)
			return null;

		//set up the distance between slower pointer and faster pointer
		for(int i = 0; i < n; i++)
		{
			fast = fast.next;
		}

		//case of removeing the head noe(i.e. n is larger than length of the list)
		if(fast == null)
			return head.next;

		while(fast.next != null)
		{
			fast = fast.next;
			slow = slow.next;
		}

		slow.next = slow.next.next;
		return head;
	}
}
