/*
Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].

Analysis:
	Method 1: recursive, left->right->root
	Method 2: iteration, stack, iterately add val to result list from beginning

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

class BTPostorderTraversal {
	//Method 1:
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		postorderTraversal(root, result);
		return result;
	}

	public void postorderTraversal(TreeNode root, List<Integer> result) {
		if(root == null)
			return;
		postorderTraversal(root.left, result);
		postorderTraversal(root.right, result);
		result.add(root.val);
	}

	//Method 2:
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		if(root == null)
			return result;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while(!stack.isEmpty()) {
			TreeNode curr = stack.pop();
			result.add(0, curr.val);

			if(curr.left != null)
				stack.push(curr.left);
			if(curr.right != null)
				stack.push(curr.right);
		}
		return result;
	}
}