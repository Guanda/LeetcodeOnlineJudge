/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.

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
class SumRootToLeafNums
{
	int sum = 0;
	public int sumNums(TreeNode root)
	{
		if(root == null)
			return 0;

		int curr = 0;
		dfs(root, curr);

		return sum;
	}

	public void dfs(TreeNode root, int curr)
	{
		if(root == null)
			return;

		//keep tracking the curr value
		curr = curr * 10 + root.val;
		
		//when it reach the leaf, then add up to the sum
		if(root.left == null && root.right == null)
		{
			sum += curr;
			return;
		}

		dfs(root.left, curr);
		dfs(root.right, curr);

		return;
	}
}