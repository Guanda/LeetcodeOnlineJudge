/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

Anaylsis:
	Find a way to calculate the mid of the list and the length of the list will be changed.
	So we need the method getLength.
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 *
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class SortedListToBST
{
	static ListNode h;

	public TreeNode sortedListToBST(ListNode head)
	{
		if(head == null)
			return null;

		h = head;

		//calculate the length of list
		int count = 0;
		while(head != null)
		{
			head = head.next;
			count++;
		}

		return sortedListToBST(0, count-1);
	}

	public TreeNode sortedListToBST(int start, int end)
	{
		if(start > end)
			return null;

		int mid = (start + end) / 2;

		TreeNode left = sortedListToBST(start, mid - 1);
		TreeNode root = new TreeNode(h.val);
		h = h.next;
		TreeNode right = sortedListToBST(mid + 1, end);

		root.left = left;
		root.right = right;

		return root;
	}
}
