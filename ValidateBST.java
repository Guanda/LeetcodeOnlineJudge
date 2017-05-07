/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:
    2
   / \
  1   3
Binary tree [2,1,3], return true.
Example 2:
    1
   / \
  2   3
Binary tree [1,2,3], return false.
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class ValidateBST {
	public boolean isValidBST(TreeNode root) {
		//have to use long instead of int
		//because the corner case could be max or min integer which can not fit in equal case
		return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	private boolean helper(TreeNode root, long min, long max) {
		if(root == null) {
			return true;
		}

		if(root.val >= max || root.val <= min) {
			return false;
		}

		return helper(root.left, min, root.val) && helper(root.right, root.val, max);
	}
}