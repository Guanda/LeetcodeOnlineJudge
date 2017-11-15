/*
Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].

Anaylsis:
	1. recursive, inorder: left, root, right
	2. iteration, using stack

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

class BTInorderTraversal {
	//Method 1: recursive
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		inorderTraversal(root, result);
		return result;
	}

	public void inorderTraversal(TreeNode root, List<Integer> result) {
		if(root == null)
			return;
		inorderTraversal(root.left, result);
		result.add(root.val);
		inorderTraversal(root.right, result);
	}

	//Method 2: iteration
	public List<Integer> inorderTraversal2(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		if(root == null)
			return result;

		Stack<TreeNode> stack = new Stack<TreeNode>();

		//define a pointer to track nodes
		TreeNode p = root;

		while(!stack.isEmpty() || p != null) {
			//if it is not null, push to stack and go down to the left
			while(p != null) {
				stack.push(p);
				p = p.left;
			}
			//if no left child, pop stack, process the node then let p point to the right
			TreeNode t = stack.pop();
			result.add(t.val);
			p = t.right;
		}
		return result;
	}
}