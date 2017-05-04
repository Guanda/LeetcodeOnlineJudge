/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
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

class PathSumAllPath {
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> resSet = new ArrayList<>();
		List<Integer> path = new ArrayList<Integer>();
		pathSum(root, sum, path, resSet);
		return resSet;
	}

	private void pathSum(TreeNode root, int sum, List<Integer> path, List<List<Integer>> resSet) {
		if(root == null)
			return;

		path.add(root.val);
		//get to leaf
		if(root.left == null && root.right == null) {
			//found a path
			if(sum == root.val) {
				//Be Careful! This is necessary! Otherwise the path will be changed and the resSet will be changed
				//deep copy
				List<Integer> curPath = new ArrayList<Integer>(path);
				resSet.add(curPath);
			}
		}
		pathSum(root.left, sum - root.val, path, resSet);
		pathSum(root.right, sum - root.val, path, resSet);

		//clear the path elements
		path.remove(path.size() - 1);
	}
}