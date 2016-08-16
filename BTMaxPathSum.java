/*
 Given a binary tree, find the maximum path sum.

 For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along
 the parent-child connections. The path does not need to go through the root.

 For example:
 Given the below binary tree,

   1
  / \
 2   3

 Return 6.

Analysis:
For each node there can be four ways that the max path goes through the node:
1. Node only
2. Max path through Left Child + Node
3. Max path through Right Child + Node
4. Max path through Left Child + Node + Max path through Right Child

The idea is to keep trace of four paths and pick up the max one in the end. An important thing to note is,
root of every subtree need to return maximum path sum such that at most one child of root is involved. This
is needed for parent function call. In below code, this sum is stored in ‘max_single’ and returned by the
recursive function.

 */

public class BTMaxPathSum {
    int result;

    public int maxPathSum(TreeNode root) {
        result = Integer.MIN_VALUE;
        maxPathSumHelper(root);

        return result;
    }

    public int maxPathSumHelper(TreeNode node) {
        if(node == null)
            return 0;

        int left = maxPathSumHelper(node.left);
        int right = maxPathSumHelper(node.right);

        //compare case1 and case2,3
        int max1 = Math.max(Math.max(left, right) + node.val, node.val);

        //compare max1 and case4
        int max2 = Math.max(left + right + node.val, max1);

        result = Math.max(max2, result);

        //remember here we need to return max1
        return max1;
    }
}