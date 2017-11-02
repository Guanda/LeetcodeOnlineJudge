/*
Implement an iterator over a binary search tree (BST). Your iterator will be 
initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) 
memory, where h is the height of the tree.

Analysis:
	The question requires average O(1) time and O(h) memory. So we can not 
	either traverse the tree when next() and hasNext() are called for the 
	least memory usage (O(n) time, O(1) space), nor store the whole tree in 
	an ArrayList for fast access (O(1) time, O(n) space). We need some tradeoff 
	between these two methods.

	The idea is to only store one part of the tree. Whenever a tree node "current" 
	is given, the first returned value when next() is called is the most left number 
	of the tree. However, the next value of next() is in "current"'s right subtree.

	So the algorithm is clear. When the root is given (constructor), we push its 
	left subtree with only left children into the stack. When next() is called, pop 
	the current value and push the right substree into the stack.
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

class BSTIterator {

	private Stack<TreeNode> stack = new Stack<TreeNode>();

	public BSTIterator(TreeNode root) {
		pushToStack(root);
	}

	/** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
     	TreeNode curr = stack.pop();
     	pushToStack(curr.right);
     	return curr.val;
    }

    public void pushToStack(TreeNode root) {
    	while(root != null) {
    		stack.push(root);
    		root = root.left;
    	}
    }
}