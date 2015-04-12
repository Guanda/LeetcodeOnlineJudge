/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. 
(ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]

Anaylsis:
	We need a LIFO stack for each level so that it could return nodes in a reverse 
	order as we push in. Also, we need to check whether the level is odd level or even 
	and when we read current level from right to left, we need to push right children 
	and then left child for each node.

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
class BTLevelZigzagTraversal
{
	public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root)
	{
		ArrayList<ArrayList<Integer>> resSet = new ArrayList<ArrayList<Integer>>();
		boolean isOdd = false;
		if(root == null)
			return resSet;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);

		while(!stack.isEmpty())
		{
			ArrayList<Integer> res = new ArrayList<Integer>();
			Stack<TreeNode> stackTmp = new Stack<TreeNode>();

			//go through one level
			while(!stack.isEmpty())
			{
				TreeNode curr = stack.pop();
				res.add(curr.val);
				if(isOdd)
				{
					if(curr.right != null)
						stackTmp.push(curr.right);
					if(curr.left != null)
						stackTmp.push(curr.left);
				}
				else
				{
					if(curr.left != null)
						stackTmp.push(curr.left);
					if(curr.right != null)
						stackTmp.push(curr.right);
				}
			}
			//add result of one level
			resSet.add(res);

			//switch
			isOdd = !isOdd;
			stack = stackTmp;
		}
		return resSet;
	}
}