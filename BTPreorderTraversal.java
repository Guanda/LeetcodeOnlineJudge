/*
Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?

Analysis:
	Method 1: recursive, root->left->right
	Method 2: iteration, stack

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
class BTPreorderTraversal {
	//Method 1
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		preorderTraversal(root, result);
		return result;
	}

	public void preorderTraversal(TreeNode root, List<Integer> result) {
		if(root == null)
			return;
		result.add(root.val);
		preorderTraversal(root.left, result);
		preorderTraversal(root.right, result);
	}


	//Method 2
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		if(root == null)
			return result;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while(!stack.isEmpty()) {
			TreeNode curr = stack.pop();
			result.add(curr.val);

			//push right first, because we need pop left first
			if(curr.right != null)
				stack.push(curr.right);
			if(curr.left != null)
				stack.push(curr.left);
		}
		return result;
	}
}