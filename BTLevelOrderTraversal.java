/*
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
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

class BTLevelOrderTraversal
{
	public List<List<Integer>> levelOrder(TreeNode root)
  {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null)
      return res;

    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);

    while(!q.isEmpty()) {
      int levelSize = q.size();
      List<Integer> currLevel = new ArrayList<>();

      for(int i = 0; i < levelSize; i++) {
        TreeNode currNode = q.poll();
        currLevel.add(currNode.val);
        if (currNode.left != null)
          q.add(currNode.left);
        if (currNode.right != null)
          q.add(currNode.right);
      }
      
      res.add(currLevel);
    }
    return res;
  }

  //Method 2: DFS
  public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        levelOrderHelper(root, results, 0);
        return results;
    }

    public void levelOrderHelper(TreeNode root, List<List<Integer>> results, int height) {
        if(root == null)
            return;

        if(height >= results.size()) {
            results.add(new ArrayList<Integer>());
        }

        results.get(height).add(root.val);
        levelOrderHelper(root.left, results, height + 1);
        levelOrderHelper(root.right, results, height + 1);
    }
}