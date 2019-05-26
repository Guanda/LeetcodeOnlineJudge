/*
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note:

You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Example 1:

Input: 1
Output: []
Example 2:

Input: 37
Output:[]
Example 3:

Input: 12
Output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
Example 4:

Input: 32
Output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]

Analysis: Classic DFS problem, similar to Combination.
*/

class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> results = new ArrayList<>();
        helper(results, new ArrayList<Integer>(), n, 2);
        return results;
    }
    
    void helper(List<List<Integer>> results, List<Integer> list, int n, int factor){
        if(n <= 1){
            if(list.size() > 1){
                results.add(new ArrayList<Integer>(list));
            }
            return;
        }
        
        for(int i = factor; i <= n; i++){
            if(n % i == 0){
                list.add(i);
                helper(results, list, n/i, i);
                list.remove(list.size() - 1);
            }
        }
    }
}