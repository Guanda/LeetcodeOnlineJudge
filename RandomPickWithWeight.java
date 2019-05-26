/*
Given an array w of positive integers, where w[i] describes the weight of index i, 
write a function pickIndex which randomly picks an index in proportion to its weight.

Note:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.
Example 1:

Input: 
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]
Example 2:

Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor 
has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with 
a list, even if there aren't any.

Analysis:
	Use prefix sum solution to map the index
*/

class Solution {
    List<Integer> prefixSum = new ArrayList<>();
    int total = 0;
    Random rand = new Random();

    public Solution(int[] w) {
        for(int x : w) {
            total += x;
            prefixSum.add(total);
        }
    }
    
    public int pickIndex() {
        int target = rand.nextInt(total);
        
        // binary search to find the lower index that target < prefixp[x]
        int left = 0;
        int right = prefixSum.size() - 1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(target < prefixSum.get(mid)){
                right = mid;
            }
            else{
                left = mid + 1;
            }
        }
        return left;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */