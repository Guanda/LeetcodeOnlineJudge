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
	point, the only thing we need to do is to move the fast/slow point to the head, and advance 
	both slow and fast points simultaneously 1 step a time; when those two pointers meet 
	again, we get the node where the cycle begins.

			n6-----------n5
	        |            |
  	  n1--- n2---n3--- n4|
	我们仍然可以使用两个指针fast和slow，fast走两步，slow走一步，判断是否有环，当有环重合之后，譬如上面在n5重合了，那么如何得到n2呢？

	首先我们知道，fast每次比slow多走一步，所以重合的时候，fast移动的距离是slot的两倍，我们假设n1到n2距离为a，
	n2到n5距离为b，n5到n2距离为c，fast走动距离为a + b + c + b，而slow为a + b，有方程a + b + c + b = 2 x (a + b)，
	可以知道a = c，所以我们只需要在重合之后，一个指针从n1，而另一个指针从n5，都每次走一步，那么就可以在n2重合了。
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
class LinkedListCycle2 {
	public ListNode detectCycle(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;

		//find the meeting point. This will be loop_size - k steps into the list
		//k is the size between start of loop and head
		while(fast != null && fast.next != null) {
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
		while(slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return fast;
	}
}