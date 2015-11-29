/*
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest 
frequently? How would you optimize the kthSmallest routine?

Hint:

Try to utilize the property of a BST.
What if you could modify the BST node's structure?
The optimal runtime complexity is O(height of BST).

Analysis:
	3 methods here.

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
public class KthSmallestInBST
{
	//inorder traverse, time: O(n), space O(n)
	public int kthSmallest(TreeNode root, int k)
	{
		int result = 0;
		Stack<TreeNode> stack = new Stack<>();
		TreeNode p = root;

		while(p != null || !stack.isEmpty()) {
			if(p != null) {
				stack.push(p);
				p = p.left;
			}
			else {
				TreeNode t = stack.pop();
				k--;
				if(k == 0) {
					result = t.val;
					break;
				}
				p = t.right;
			}
		}
		return result;
	}


	//get size of BST and recursively find out kth element
	//time: O(nlogn), space: O(1). size BST is time O(n)
	public int kthSmallest2(TreeNode root, int k)
	{
		int leftNodeSize = size(root.left);
		if(k == leftNodeSize + 1) {
			return root.val;
		}
		else if(k < leftNodeSize + 1) {
			return kthSmallest2(root.left, k);
		}
		else {
			return kthSmallest2(root.right, k - leftNodeSize - 1);
		}
	}

	private int size(TreeNode root)
	{
		if(root == null)
			return 0;

		return size(root.left) + size(root.right) + 1;
	}


	//if we can modify the tree node's structure, add size N to it
	//the search only takes O(logN)
	public class TreeNode {
	    int val;
	    //number of nodes in subtree (including itself)
	    int n;  
	    TreeNode left, right;
	    TreeNode(int x) { 
	    	val = x; 
	    }

	    public int size(TreeNode root)
		{
			if(root == null)
				return 0;

			return root.n;
		}

		public void insert(int val)
		{
			p = insert(p, val);
		}

		private TreeNode insert(TreeNode p, int val)
		{
			if(p == null) {
				return new TreeNode(val);
			}
			if(val < p.val) {
				p.left = insert(p.left, val);
			}
			else if(val > p.val) {
				p.right = insert(p.right, val);
			}
			else {
				p.val = val;
			}

			p.n = 1 + size(p.left) + size(p.right);

			return p;
		}
	}
}