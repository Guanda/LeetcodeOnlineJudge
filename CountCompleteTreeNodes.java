/*
Given a complete binary tree, count the number of nodes.

In a complete binary tree every level, except possibly the last, is completely filled, 
and all nodes in the last level are as far left as possible. It can have between 1 and 
2^h nodes inclusive at the last level h.

Analysis:
	Time Complexity: O((logn)^2)

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
class CountCompleteTreeNodes {
	public int height(TreeNode root) {
		return root == null ? -1 : height(root.left) + 1;
	}

	public int countNodes(TreeNode root) {
		int count = 0;
		int h = height(root);
		while(root != null) {
			if(height(root.right) == h - 1) {
				count += 1 << h;
				root = root.right;
			}
			else {
				count += 1 << h-1;
				root = root.left;
			}
			h--;
		}
		return count;
	}
}