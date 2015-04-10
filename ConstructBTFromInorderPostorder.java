/*
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

in-order:   4 2 5  (1)  6 7 3 8
post-order: 4 5 2  6 7 8 3  (1)

Anaylsis:
	From the post-order array, we know that last element is the root. We can 
	find the root in in-order array. Then we can identify the left and right 
	sub-trees of the root from in-order array.

	The point is after new root found, how to decide the new postStart, follow 
	the example to find out where the new left tree ends in postorder.
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
class ConstructBTFromInorderPostorder
{
	public TreeNode buildTree(int[] inorder, int[] postorder)
	{
		int inStart = 0;
		int inEnd = inorder.length - 1;
		int postStart = 0;
		int postEnd = postorder.length - 1;

		return buildTree(inorder, inStart, inEnd, postorder, postStart, postEnd);
	}

	public TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd)
	{
		if(inStart > inEnd || postStart > postEnd)
			return null;

		TreeNode root = new TreeNode(postorder[postEnd]);

		int k = 0;
		for(int i = 0; i < inorder.length; i++)
		{
			if(inorder[i] == postorder[postEnd])
				k = i;
		}

		root.left = buildTree(inorder, inStart, k-1, postorder, postStart, postStart + k - (inStart + 1));
		root.right = buildTree(inorder, k+1, inEnd, postorder, postStart + k - inStart, postEnd-1);

		return root;
	}
}