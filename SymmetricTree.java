/*
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following is not:
    1
   / \
  2   2
   \   \
   3    3
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

class SymmetricTree
{
	//recursive solution
	public boolean isSymmetric(TreeNode root)
	{
		if(root == null)
			return true;

		return isSymmetric(root.left, root.right);
	}

	public boolean isSymmetric(TreeNode a, TreeNode b)
	{
		if(a == null)
			return b == null;

		if(b == null)
			return false;

		if(a.val != b.val)
			return false;

		if(!isSymmetric(a.left, b.right))
			return false;

		if(!isSymmetric(a.right, b.left))
			return false;

		return true;
	}


	//Iteration solution
	public boolean isSymmetricIteration(TreeNode root)
	{
		if(root == null)
			return true;

		LinkedList<TreeNode> l = new LinkedList<TreeNode>();
		LinkedList<TreeNode> r = new LinkedList<TreeNode>();
		l.add(root.left);
		r.add(root.right);

		while(!l.isEmpty() && !r.isEmpty())
		{
			TreeNode tmp1 = l.poll();
			TreeNode tmp2 = r.poll();
			if(tmp1 == null && tmp2 != null || tmp1 != null && tmp2 == null)
				return false;

			if(tmp1 != null)
			{
				if(tmp1.val != tmp2.val)
					return false;
				l.add(tmp1.left);
				l.add(tmp1.right);
				r.add(tmp2.right);
				r.add(tmp2.left);
			}
		}
		return true;
	}
}