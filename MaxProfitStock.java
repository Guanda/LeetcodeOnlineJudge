/*
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one 
share of the stock), design an algorithm to find the maximum profit.

Anaylsis:
	Make sure the sell date is later than when you buy it.
	it's the only thing you need to take care
*/

class MaxProfitStock {
	public int maxProfit(int[] prices) {
		if(prices.length == 0)
			return 0;

		int low = prices[0];
		int profit = 0;
		for(int i = 0; i < prices.length; i++) {
			low = Math.min(prices[i], low);
			profit = Math.max(profit, prices[i] - low);
		}
		return profit;
	}
}