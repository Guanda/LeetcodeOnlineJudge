/*
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

Analysis:

Method 1: 
	Use in-order traversal, find the first and second mistake element. O(n) space

Method 2:
	Morris Traversal. O(1) space
	referrence: http://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class RecoverBST {
	/************
	** Method 1 *
	*************/
	TreeNode first = null;
	TreeNode second = null;
	//The reason for this initialization is to avoid null pointer exception in the first comparison 
	//when prevElement has not been initialized
	TreeNode prev = new TreeNode(Integer.MIN_VALUE);

	public void recoverBST(TreeNode root) {
		traversal(root);

		//swap first and second
		int tmp = first.val;
		first.val = second.val;
		second.val = tmp;
	}

	public void traversal(TreeNode root) {
		if(root == null)
			return;

		traversal(root.left);

		if(first == null && prev.val >= root.val) {
			first = prev;
		}

		if(first != null && prev.val >= root.val) {
			second = root;
		}

		prev = root;

		traversal(root.right);
	}


	/************
	** Method 2 *
	*************/
	public void recoverBSTMorris (TreeNode root) {
		TreeNode first = null;
		TreeNode second = null;
		TreeNode pre = null;

		// Morris Traversal
		TreeNode temp = null;
		while(root != null) {
			if(root.left != null) {
				// connect threading for root
				temp = root.left;
				while(temp.right!=null && temp.right != root)
					temp = temp.right;
				// the threading already exists
				if(temp.right!=null){
					//compare
				    if(pre!=null && pre.val > root.val){
				        if(first==null){
				        	first = pre;
				        	second = root;
				        }
				        else{
				        	second = root;
				        }
				    }
				    pre = root;
				    
					temp.right = null;
					root = root.right;
				}else{
					// construct the threading
					temp.right = root;
					root = root.left;
				}
			}
			else {
				//compare
			    if(pre!=null && pre.val > root.val){
			        if(first==null){
			        	first = pre;
			        	second = root;
			        }
			        else{
			        	second = root;
			        }
			    }
			    pre = root;
			    root = root.right;
			}
		}
		// swap two node values;
		if(first!= null && second != null){
		    int t = first.val;
		    first.val = second.val;
		    second.val = t;
		}
	}
}