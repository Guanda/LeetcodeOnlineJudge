/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Analysis:
	DP solution, only need to consider 4 variables: buy1, sell1, buy2, sell2

*/
class MaxProfitStock3 {
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
}