/*
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

    4
   / \
  2   5
 / \
1   3

Output: [4,3]
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Analysis:
	The idea is to compare the predecessors and successors of the closest node to the target, we can 
	use two stacks to track the predecessors and successors, then like what we do in merge sort, we 
	compare and pick the closest one to the target and put it to the result list.

	As we know, inorder traversal gives us sorted predecessors, whereas reverse-inorder traversal gives 
	us sorted successors.

	We can use iterative inorder traversal rather than recursion, but to keep the code clean, here is 
	the recursion version.
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
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<>();
        
        // Use s1 to keep predecessors
        Stack<Integer> s1 = new Stack<>();
        // Use s2 to keep successors
        Stack<Integer> s2 = new Stack<>();
        
        inorder(root, target, false, s1);
        inorder(root, target, true, s2);
        
        while(k-- > 0) {
            if(s1.isEmpty()){
                result.add(s2.pop());
            }
            else if(s2.isEmpty()){
                result.add(s1.pop());
            }
            else if(Math.abs(s1.peek() - target) < Math.abs(s2.peek() - target)){
                result.add(s1.pop());
            }
            else {
                result.add(s2.pop());
            }
        }
        return result;
    }
    
    void inorder(TreeNode root, double target, boolean reverse, Stack<Integer> stack){
        if(root == null)
            return;
        
        inorder(reverse ? root.right : root.left, target, reverse, stack);
        // early terminate, no need to traverse the whole tree
        if((reverse && root.val <= target) || (!reverse && root.val > target))
            return;
        stack.push(root.val);
        inorder(reverse ? root.left : root.right, target, reverse, stack);
    }
}