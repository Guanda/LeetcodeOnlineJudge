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

class SortedListToBST {
	// Solution 1: create new array and build BST
    public TreeNode sortedListToBST(ListNode head) {
        ListNode p = head;
        int count = 0;
        while(p != null) {
            p = p.next;
            count++;
        }
        int[] arr = new int[count];
        int i = 0;
        while(head != null) {
            arr[i] = head.val;
            head = head.next;
            i++;
        }
        
        return buildTree(arr, 0, count - 1);
    }
    
    private TreeNode buildTree(int[] arr, int start, int end) {
        if(start > end) {
            return null;
        }
        
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = buildTree(arr, start, mid - 1);
        root.right = buildTree(arr, mid + 1, end);
        
        return root;
    }

    ////////////////////////////////////////////////////////////////////////

    // Solution 2: don't use extra array, process mid in every recursive call
	public TreeNode sortedListToBST(ListNode head) {
	    if(head == null) 
	    	return null;

	    return toBST(head,null);
	}

	public TreeNode toBST(ListNode head, ListNode tail){
	    ListNode slow = head;
	    ListNode fast = head;

	    if(head==tail) 
	    	return null;
	    
	    while(fast != tail && fast.next != tail) {
	        fast = fast.next.next;
	        slow = slow.next;
	    }

	    TreeNode root = new TreeNode(slow.val);
	    root.left = toBST(head,slow);
	    root.right = toBST(slow.next,tail);

	    return root;
	}

    ////////////////////////////////////////////////////////////////////////

    // Solution 3: Use inorder-traversal
	static ListNode h;

	public TreeNode sortedListToBST(ListNode head) {
		if(head == null)
			return null;

		h = head;

		//calculate the length of list
		int count = 0;
		while(head != null) {
			head = head.next;
			count++;
		}

		return sortedListToBST(0, count-1);
	}

	public TreeNode sortedListToBST(int start, int end) {
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
