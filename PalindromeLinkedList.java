/*
Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?

Analysis:
	Method 1: Using Stack 
	Method 2: reverse the second half of linked list and then reverse it back

*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class PalindromeLinkedList
{
	//Method 1: time O(n), spack O(n)
	public boolean isPalindrome(ListNode head)
	{
		if(head == null)
			return true;

		Stack<Integer> stack = new Stack<>();
		ListNode p = head;
		stack.add(p.val);
		while(p.next != null) {
			stack.add(p.next.val);
			p = p.next;
		}

		p = head;
		while(p != null) {
			if(p.val != stack.pop())
				return false;
			p = p.next;
		}

		return true;
	}

	//Method 2: time O(n), space O(1)
	//only reverse half of them is because we don't want to use extra space
	public boolean isPalindrome2(ListNode head)
	{
		if(head == null || head.next == null)
			return true;
		
		ListNode slow = head;
		ListNode fast = head;

		/* Get the middle of the list. Move slow by 1 and
          ast by 2, slow will have the middle node */
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
            slow = slow.next;
		}

		/* fast would become NULL when there are even elements in list. 
           And not NULL for odd elements. We need to skip the middle node 
           for odd case and store it somewhere so that we can restore the
           original list */
        if(fast != null) {
        	slow = slow.next;
        }

        // Now reverse the second half and compare it with first half
        ListNode left = head;
        ListNode right = reverse(slow);

        while(right != null) {
        	if(left.val != right.val)
        		return false;
        	left = left.next;
        	right = right.next;
        }
        return true;

	}

	public ListNode reverse(ListNode p)
	{
		if(p == null || p.next == null) {
			return p;
		}

		//get second node
		ListNode q = p.next;
		//set first node's next to null
		p.next = null;

		ListNode result = reverse(q);
		q.next = p;

		return result;
	}
}