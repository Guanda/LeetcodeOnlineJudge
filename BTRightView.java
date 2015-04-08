/*
Given a binary tree, imagine yourself standing on the right side of it, return the 
values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].

Analysis: 
	BFS, triverse tree level by level, always return right most node of each level.

*/

class BTRightView
{
	public List<Integer> rightSideView(TreeNode root)
	{
		List<Integer> result = new List<Integer>();
		if(root == null)
			return result;

		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		int count = 1;
		int tmp = 0;
		queue.addLast(root);
		while(!queue.isEmpty())
		{
			tmp = 0;
			for(int i = 0; i < count; i++)
			{
				TreeNode tmpNode = queue.removeFirst();
				if(i == count-1)
					result.add(tmpNode.val);
				if(tmpNode.left != null)
				{
					queue.addLast(tmpNode.left);
					tmp++;
				}
				if(tmpNode.right != null)
				{
					queue.addLast(tmpNode.right);
					tmp++;
				}
			}
			count = tmp;
		}
		return result;
	}
}