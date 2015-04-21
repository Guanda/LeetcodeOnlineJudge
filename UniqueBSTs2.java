/*
Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

*/

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; left = null; right = null; }
 * }
 */
class UniqueBSTs2
{
	public List<TreeNode> generateTrees(int n)
	{
		return generateTrees(1, n);
	}

	public List<TreeNode> generateTrees(int begin, int end)
	{
		List<TreeNode> list = new LinkedList<TreeNode>();
		if(begin > end)
		{
			//here we have to add null, otherwise it will give us the [{0}]
			list.add(null);
			return list;
		}

		for(int i = begin; i <= end; i++)
		{
			List<TreeNode> lefts = generateTrees(begin, i-1);
			List<TreeNode> rights = generateTrees(i+1, end);
			for(TreeNode left : lefts)
			{
				for(TreeNode right : rights)
				{
					TreeNode node = new TreeNode(i);
					node.left = left;
					node.right = right;
					list.add(node);
				}
			}
		}
		return list;
	}
}