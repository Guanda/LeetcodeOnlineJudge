/*
Reverse a singly linked list.

Analysis:
	Method 1: Just one iteration. Move current to connect previous one. 
		  So we need prev, curr, next these three nodes.
		  
	Method 2: recursive solution
*/

class ReverseLinkedList {
	//Method 1
	public ListNode reverseList(ListNode head) {
		if(head == null || head.next == null)
			return head;

        ListNode pre = null;
        while(head != null) {
            ListNode tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        
        return pre;
	}


	//Method 2
	public ListNode reverseList(ListNode head) {
		if(head == null || head.next == null)
			return head;

		ListNode remainingReverse = reverseList(head.next);

		//update the tail as beginning
		ListNode curr = remainingReverse;
		while(curr.next != null) {
			curr = curr.next;
		}

		//assign the head as tail
		curr.next = head;
		head.next = null;

		return remainingReverse;
	}
}
