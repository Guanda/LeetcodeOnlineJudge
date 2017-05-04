/*
Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]

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
public class BinaryTreePaths {
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> result = new ArrayList<>();
		if(root == null) {
			return result;
		}
		findPaths(root, result, root.val + "");
		return result;
	}

	private void findPaths(TreeNode root, List<String> result, String cur) {
		if(root.left == null && root.right == null) {
			result.add(cur);
			return;
		}
		if(root.left != null) {
			findPaths(root.left, result, cur + "->" + root.left.val);
		}
		if(root.right != null) {
			findPaths(root.right, result, cur + "->" + root.right.val);
		}
	}


	//no private method need:

	//递归定义：求出从root出发的所有路径并返回
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();

        //递归的出口
        if(root == null) {
            return paths;
        }
        
        if(root.left == null && root.right == null) {
            paths.add(root.val + "");
            return paths;
        }
        
        //递归的拆解
        // divide & conquer
        List<String> leftPaths = binaryTreePaths(root.left);
        List<String> rightPaths = binaryTreePaths(root.right);
        
        //merge
        for(String path : leftPaths) {
            paths.add(root.val + "->" + path);
        }
        for(String path : rightPaths) {
            paths.add(root.val + "->" + path);
        }
        
        return paths;
    }
}