/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Analysis:
	Method 1: DP solution, only need to consider 4 variables: buy1, sell1, buy2, sell2
	Method 2: Since we have mostly 2 transactions, we can suppose that two one is before day i, and one is after day i.

*/
class MaxProfitStock3 {
	//Method 1:
	public int maxProfit(int[] prices) {
		int buy1 = Integer.MIN_VALUE;
		int buy2 = Integer.MIN_VALUE;
		int sell1 = 0;
		int sell2 = 0;
		for(int i = 0; i < prices.length; i++) {
			sell2 = Math.max(sell2, buy2 + prices[i]); // The maximum if we've just sold 2nd stock so far.
			buy2 = Math.max(buy2, sell1 - prices[i]);  // The maximum if we've just buy  2nd stock so far.
			sell1 = Math.max(sell1, buy1 + prices[i]); // The maximum if we've just sold 1st stock so far.
			buy1 = Math.max(buy1, -prices[i]);		   // The maximum if we've just buy  1st stock so far.	
		}
		return sell2;
	}

	//Method 2:
	public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int[] left = new int[prices.length];
        int[] right = new int[prices.length];

        // DP from left to right;
        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(prices[i], min);
            left[i] = Math.max(left[i - 1], prices[i] - min);
        }

        //DP from right to left;
        right[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(prices[i], max);
            right[i] = Math.max(right[i + 1], max - prices[i]);
        }

        int profit = 0;
        for (int i = 0; i < prices.length; i++){
            profit = Math.max(left[i] + right[i], profit);  
        }

        return profit;
    }
}