/*
Given a non-empty special binary tree consisting of nodes with the non-negative value, 
where each node in this tree has exactly two or zero sub-node. If the node has two 
sub-nodes, then this node's value is the smaller value among its two sub-nodes.

Given such a binary tree, you need to output the second minimum value in the set made 
of all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:

Input: 
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.
 

Example 2:

Input: 
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.

Analysis:
	Once we find the node value which is greater than root.val, we know all the values
	in subtree of that node are at least node.val, so we don't need to sear in subtree
*/

class Solution {
    int min;
    long result = Long.MAX_VALUE;
    
    public int findSecondMinimumValue(TreeNode root) {
        min = root.val;
        dfs(root);
        return result < Long.MAX_VALUE ? (int)result : -1;
    }
    
    void dfs(TreeNode node){
        if(node != null){
            if(min < node.val && node.val < result){
                result = node.val;
            }
            else if(min == node.val){
                dfs(node.left);
                dfs(node.right);
            }
        }
    }
}