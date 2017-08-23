/*
You have a number of envelopes with widths and heights given as a pair of integers (w, h). 
One envelope can fit into another if and only if both the width and height of one envelope is greater 
than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Example:
Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll 
is 3 ([2,3] => [5,4] => [6,7]).

Analysis:
	step 1: Sort the array. Ascend on width and descend on height if width are same.
	step 2: Find the longest increasing subsequence based on height.
	For the second step, there are two different solutions.
	1. Time O(n^2)
	2. Time O(nlogn)
	reference: Longest Increasing Subsequence problem 
*/

class RussianDollEnvelopes {
	//Solution 1: this is not the best solution
	public int maxEnvelopes(int[][] envelopes) {
		if(envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length != 2)
        	return 0;

        Arrays.sort(envelopes, new Comparator<int[]>(){
	        public int compare(int[] arr1, int[] arr2){
	            if(arr1[0] == arr2[0])
	                return arr2[1] - arr1[1];
	            else
	                return arr1[0] - arr2[0];
	        } 
	    });

	    int dp[] = new int[envelopes.length];
	    int result = 0;
	    for (int i = 0; i < envelopes.length; i++){
	        dp[i] = 1;
	        for (int j = 0; j < i; j++){
	            if (envelopes[i][1] > envelopes[j][1] && dp[i] < dp[j] + 1){
	                dp[i] = Math.max(dp[i], 1 + dp[j]);    
	            }
	        }
	        result = Math.max(result, dp[i]);
	    }
	    return result;
	}


	//Solution 2:
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0 || envelopes[0].length != 2) {
            return 0;
        }
        
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] arr1, int[] arr2) {
                if(arr1[0] == arr2[0]) {
                    return arr2[1] - arr1[1];
                }
                else {
                    return arr1[0] - arr2[0];
                }
            }
        });
        
        int[] tails = new int[envelopes.length];
        int size = 0;
        for(int[] envelope : envelopes) {
            int i = 0, j = size;
            //binary search
            while(i < j) {
                int mid = (i + j) / 2;
                if(tails[mid] < envelope[1]) {
                    i = mid + 1;
                }
                else {
                    j = mid;
                }
            }
            
            tails[i] = envelope[1];
            if(i == size) {
                size++;
            }
        }
        
        return size;
    }
}