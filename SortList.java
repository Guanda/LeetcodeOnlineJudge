/*
Sort a linked list in O(nlog n) time using constant space complexity.

Analysis:
	use Merge sort!
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class SortList
{
	public ListNode sortList(ListNode head)
	{
		if(head == null || head.next == null)
			return head;

		//count total number of elements
		int count = 0;
		ListNode p = head;
		while(p != null)
		{
			count++;
			p = p.next;
		}

		//break up to two list
		int middle = count / 2;
		ListNode left = head;
		ListNode right = null;
		ListNode tmp = head;
		int countHalf = 0;
		while(tmp != null)
		{
			countHalf++;
			ListNode next = tmp.next;
			if(countHalf == middle)
			{
				tmp.next = null;
				right = next;
			}
			tmp = next;
		}

		ListNode h1 = sortList(left);
		ListNode h2 = sortList(right);

		//merge two lists
		ListNode merged = merge(h1, h2);

		return merged;
	}

	public ListNode merge(ListNode left, ListNode right)
	{
		ListNode p1 = left;
		ListNode p2 = right;

		ListNode fakeHead = new ListNode(0);
		ListNode pNew = fakeHead;

		while(p1 != null || p2 != null)
		{
			if(p1 == null)
			{
				pNew.next = new ListNode(p2.val);
				p2 = p2.next;
				pNew = pNew.next;
			}
			else if(p2 == null)
			{
				pNew.next = new ListNode(p1.val);
				p1 = p1.next;
				pNew = pNew.next;
			}
			else
			{
				if(p1.val < p2.val)
				{
					pNew.next = new ListNode(p1.val);
					p1 = p1.next;
					pNew = pNew.next;
				}
				else if(p1.val == p2.val)
				{
					pNew.next = new ListNode(p1.val);
					pNew.next.next = new ListNode(p2.val);
					pNew = pNew.next.next;
					p1 = p1.next;
					p2 = p2.next;
				}
				else
				{
					pNew.next = new ListNode(p2.val);
					p2 = p2.next;
					pNew = pNew.next;
				}
			}
		}
		return fakeHead.next;
	}
}
