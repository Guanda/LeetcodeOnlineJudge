/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Analysis:

The general idea is DP, while I had to add a "quickSolve" function to tackle some corner cases to avoid TLE.
DP: t(i,j) is the max profit for up to i transactions by time j (0<=i<=K, 0<=j<=T).

Because buy and sell prices may not be the same, when k>len/2, that means we can do as many transactions as we want. 
So, in case k>len/2, this problem is same to Best Time to Buy and Sell Stock II.

t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax) gives us the maximum price when we sell at this price;
tmpMax = Math.max(tmpMax, t[i-1][j-1] - prices[j]) gives us the value when we buy at this price and leave 
this value for prices[j+1].

*/
class MaxProfitStock4 {
	public int maxProfit(int k, int[] prices) {
		int len = prices.length;
		if(k >= len / 2) {
			return quickSolve(prices);
		}

		int[][] profit = new int[k+1][len];
		for(int i = 1; i <= k; i++) {
			int tmpMax = -prices[0];
			for(int j = 1; j < len; j++) {
				profit[i][j] = Math.max(profit[i][j-1], prices[j] + tmpMax);
				tmpMax = Math.max(tmpMax, profit[i-1][j-1] - prices[j]);
			}
		}
		return profit[k][len-1];
	}

	public int quickSolve(int[] prices) {
		int len = prices.length, profit = 0;

        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];

        return profit;
	}
}