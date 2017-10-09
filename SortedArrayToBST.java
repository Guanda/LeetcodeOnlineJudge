/*
Given an array where elements are sorted in ascending order, convert it to a height balanced BST

Anaylsis:
	Easy recursive.
*/

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class SortedArrayToBST {
	public TreeNode sortedArrayToBST(int[] num) {
		if(num.length == 0)
			return null;

		return sortedArrayToBST(num, 0, num.length-1);
	}

	public TreeNode sortedArrayToBST(int[] num, int start, int end) {
		if(start > end)
			return null;

		int mid = (start + end) / 2;
		TreeNode root = new TreeNode(num[mid]);
		root.left = sortedArrayToBST(num, start, mid-1);
		root.right = sortedArrayToBST(num, mid+1, end);

		return root;
	}
}