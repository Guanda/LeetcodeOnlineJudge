/*
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

Anaylsis:
	Same as the inorder and postorder problem. The first integer in preorder
	is the root value.

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
class ConstructBTFromPreorderInorder
{
	public TreeNode buildTree(int[] preorder, int[] inorder)
	{
		int startPreorder = 0;
		int endPreorder = preorder.length - 1;
		int startInorder = 0;
		int endInorder = inorder.length - 1;

		return buildTree(preorder, startPreorder, endPreorder, inorder, startInorder, endInorder);
	}

	public TreeNode buildTree(int[] preorder, int startPreorder, int endPreorder, int[] inorder, int startInorder, int endInorder)
	{
		if(startPreorder > endPreorder || startInorder > endInorder)
			return null;

		TreeNode root = new TreeNode(preorder[startPreorder]);

		int k = 0;
		for(int i = 0; i < inorder.length; i++)
		{
			if(inorder[i] == preorder[startPreorder])
			{
				k = i;
			}
		}

		root.left = buildTree(preorder, startPreorder+1, startPreorder+k-startInorder, inorder, startInorder, k-1);
		root.right = buildTree(preorder, startPreorder+k-startInorder+1, endPreorder, inorder, k+1, endInorder);

		return root;
	}
}