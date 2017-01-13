/*
Given a binary tree where all the right nodes are either leaf nodes with a sibling 
(a left node that shares the same parent node) 
or empty, flip it upside down and turn it into a tree where the original right nodes 
turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5

return the root of the binary tree [4,5,2,#,#,3,1].
    4
   / \
  5   2
     / \
    3   1

Analysis:
The key point is after upside down, the original left child's left is its right sibling and right is its parent

*/

class BTUpsideDown
{
	public TreeNode upsideDownBT(TreeNode root)
	{
		if(root == null)
			return root;

		TreeNode parent = root;
		TreeNode left = root.left;
		TreeNode right = root.right;
		if(left != null)
		{
			TreeNode newRoot = upsideDownBT(left);
			left.left = right;
			left.right = parent;
			return newRoot;
		}
		return root;
	}
}