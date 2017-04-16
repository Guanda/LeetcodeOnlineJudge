/*
Given a singly linked list, group all odd nodes together followed by the even nodes. 
Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example:
Given 1->2->3->4->5->NULL,
return 1->3->5->2->4->NULL.

Note:
The relative order inside both the even and odd groups should remain as it was in the input. 
The first node is considered odd, the second node even and so on ...
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class OddEvenList {
	public ListNode oddEvenList(ListNode head) {
		if(head == null || head.next == null || head.next.next == null)
			return head;

		ListNode oddDummyHead = new ListNode(-1);
		ListNode evenDummyHead = new ListNode(-1);
		oddDummyHead.next = head;
		evenDummyHead.next = head.next;
		ListNode currOdd = head;
		ListNode currEven = head.next;

		while(currEven != null && currEven.next != null) {
			currOdd.next = currEven.next;
			currEven.next = currEven.next.next;
			currOdd = currOdd.next;
			currEven = currEven.next;
		}
		//connect odd and even list
		currOdd.next = evenDummyHead.next;

		return oddDummyHead.next;
	}
}