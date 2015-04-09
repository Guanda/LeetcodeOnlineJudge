/*
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6

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
class FlattenBT
{
	//Method 1: recursive
	//insert left to root.right, before right
    public void flatten(TreeNode root) {
        helper(root);
    }
    
    public TreeNode helper(TreeNode root) {
		if(root == null)
			return root;

		TreeNode rtree = root.right;
		if(root.left != null)
		{
			root.right = root.left;
			root.left = null;
			root = helper(root.right);
		}

		if(rtree != null)
		{
			root.right = rtree;
			root = helper(root.right);
		}
		return root;
    }


    //Method 2: iteration with stack
    public void flatten2(TreeNode root)
    {
    	Stack<TreeNode> stack = new Stack<TreeNode>();
    	TreeNode curr = root;
    	while(curr != null)
    	{
    		if（curr.left != null)
			{
				if(curr.right != null)
					stack.push(curr.right);	

				curr.right = curr.left;
				curr.left = null;		
			}
    		
    		if(curr.right == null && !stack.isEmpty())
    		{
    			curr.right = stack.pop();
    		}
    		curr = curr.right;
    	}
    }


    //Method 3: iteration without stack
    //Each time when we prune a right subtree, we use while-loop 
    //to find the right-most leaf of the current left subtree, and 
    //append the subtree there.
    public void flatten3(TreeNode root)
    {
    	TreeNode curr = root;
    	while(curr != null)
    	{
    		if(curr.left != null)
    		{
    			if(curr.right != null)
    			{
    				TreeNode next = curr.left;
    				while(next.right != null)
    				{
    					next = next.right;
    				}
    				next.right = curr.right;
    			}
    			curr.right = curr.left;
    			curr.left = null;
    		}
    		curr = curr.right;	
    	}
    }
}