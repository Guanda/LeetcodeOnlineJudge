/*
Invert a binary tree.
     4
   /   \
  2     7
 / \   / \
1   3 6   9

to

     4
   /   \
  7     2
 / \   / \
9   6 3   1

Analysis: recursive, remember the null case for root, otherwise it will return null pointer exception
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
class InvertBinaryTree {
	public TreeNode invertTree(TreeNode root) {
		if(root == null)
		    return root;

		TreeNode tmp = root.left;
		root.left = root.right;
		root.right = tmp;

		if(root.left != null)
		    invertTree(root.left);
		if(root.right != null)
		    invertTree(root.right);

		return root;
    }
}