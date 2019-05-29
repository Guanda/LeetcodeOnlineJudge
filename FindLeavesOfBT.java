/*
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, 
repeat until the tree is empty.

Example:

Input: [1,2,3,4,5]
  
          1
         / \
        2   3
       / \     
      4   5    

Output: [[4,5,3],[2],[1]]
 

Explanation:

1. Removing the leaves [4,5,3] would result in this tree:

          1
         / 
        2          
2. Now removing the leaf [2] would result in this tree:

          1          
3. Now removing the leaf [1] would result in the empty tree:

          []    

Analysis:
	DFS, bottom up. The key is: The height of a node is also the its index in the result list(results)
	For example, leaves, whose heights are 0, are stored in res[0]. Once we find the height of a node, 
	we can put it directly into the result.
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
class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        height(root, results);
        return results;
    }
    
    int height(TreeNode node, List<List<Integer>> results){
        if(node == null)
            return -1;
        int level = 1 + Math.max(height(node.left, results), height(node.right, results));
        if(results.size() < level + 1){
            results.add(new ArrayList<Integer>());
        }
        results.get(level).add(node.val);
        return level;
    }
}