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

class BTLevelOrderBottomTraversal
{
	public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root)
  {
      //The idea here is create two arraylist, current and parent. 
      //Parent contains all the nodes in the upper level
      //for every node in parent, we add its left and right child to current

      ArrayList<ArrayList<TreeNode>> nodeResult = new ArrayList<ArrayList<TreeNode>>();
      ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

      if(root == null)
          return result;

      ArrayList<TreeNode> current = new ArrayList<TreeNode>();
      current.add(root);

      while(current.size() > 0)
      {
          //add the previous level into the nodeResult
          nodeResult.add(current);

          ArrayList<TreeNode> parent = current;
          current = new ArrayList<TreeNode>();

          for(TreeNode node : parent)
          {
              if(node.left != null)
              {
                  current.add(node.left);
              }
              if(node.right != null)
              {
                  current.add(node.right);
              }
          }
      }

      for(ArrayList<TreeNode> nodeArr : nodeResult)
      {
          ArrayList<Integer> intArr = new ArrayList<Integer>();
          for(TreeNode node : nodeArr)
          {
              intArr.add(node.val);
          }
          result.add(intArr);
      }

      //reverse result
      Collections.reverse(result);
      return result;
  }
}