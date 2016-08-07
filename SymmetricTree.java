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
    public boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if(left == null || right == null)
            return left == right;

        if(left.val != right.val)
            return false;

        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }


	//Iteration solution
	//first use two linkedlist or stack to store nodes
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

	//second after re-considering, we can use only one stack to solve this
	public boolean isSymmetricIterationBetter(TreeNode root) {
        if(root == null)
            return true;

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.add(root.left);
        stack.add(root.right);
        while(stack.size() > 1) {
            TreeNode right = stack.pop();
            TreeNode left = stack.pop();

            if(left == null && right == null)
                continue;

            if((left == null && right != null) || (left != null && right == null))
                return false;

            if(left.val != right.val)
                return false;

            stack.add(left.right);
            stack.add(right.left);
            stack.add(left.left);
            stack.add(right.right);
        }

        return true;
	}
}