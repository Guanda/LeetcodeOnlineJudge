/*
Reverse a singly linked list.

Analysis:
Method 1: Just one iteration. Move current to connect previous one. 
	  So we need prev, curr, next these three nodes.
	  
Method 2: recursive solution

*/

class ReverseLinkedList
{
	//Method 1
	public ListNode reverseList(ListNode head)
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


	//Method 2
	public ListNode reverseList(ListNode head)
	{
		if(head == null || head.next == null)
			return head;

		ListNode remainingReverse = reverseList(head.next);

		//update the tail as beginning
		ListNode curr = remainingReverse;
		while(curr.next != null)
		{
			curr = curr.next;
		}

		//assign the head as tail
		curr.next = head;
		head.next = null;

		return remainingReverse;
	}
}
