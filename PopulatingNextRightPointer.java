/*
Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL

Anaylsis:
	Because the extra space should be constant, the DFS/BFS will not properiate here.
	We consider just go through the whole tree level by leve based on the next pointer.
*/

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
class PopulatingNextRightPointer
{
	//with two loop
	public void connect(TreeLinkNode root)
	{
		TreeLinkNode first = root;
		while(first != null)
		{
			TreeLinkNode curr = first;
			while(curr != null)
			{
				if(curr.left != null)
					curr.left.next = curr.right;

				if(curr.right != null && curr.next != null)
					curr.right.next = curr.next.left;

				curr = curr.next;
			}
			//move to next level
			first = first.left;
		}
	}


	//with only one single loop
	public void connect2(TreeLinkNode root)
	{
		TreeLinkNode first = root;
		TreeLinkNode curr = root;
		while(curr != null)
		{
			if(curr.left == null && curr.right == null)
				break;

			if(curr.left != null)
				curr.left.next = curr.right;

			if(curr.next != null)
			{
				if(curr.right != null)
					curr.right.next = curr.next.left;

				curr = curr.next;
			}
			else
			{
				curr = first.left;
				first = curr;
			}
		}
	}
}