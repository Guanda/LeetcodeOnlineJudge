/*
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

Anaylsis:
  DP
	A BST can be destruct to root, left subtree and right subtree.
	If the root is fixed, every combination of unique left/right subtrees forms a unique BST.
	Let a[n] = number of unique BST's given vaule 1 .. n then
	a[n] = a[0] * a[n-1]  // put 1 at root, 2..n right
		 + a[1] * a[n-2]  // put 2 at root, 1 left, 3..n right
		 + ...
		 + a[n-1] * a[0]  // put n at root, 1..n-1 left

*/

class UniqueBSTs
{
	public int numTrees(int n)
	{
		if(n < 0)
			return 0;

		int[] count = new int[n+1];
		count[0] = 1; // empty tree only 1 BST.
		count[1] = 1;

		for(int i = 2; i <= n; i++)
		{
			for(int j = 0; j <= i-1; j++)
			{
				count[i] += count[j] * count[i-1-j];
			}
		}
		return count[n];
	}
}