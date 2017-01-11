/*
You are given coins of different denominations and a total amount of money amount. Write a function to 
compute the fewest number of coins that you need to make up that amount. If that amount of money cannot 
be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin.

Analysis:
	This is a very classic dynamic programming algorithm. However, for someone not familiar with the concept, 
	it can be tricky. Here we tackle the problem recursively, for each coin, if I take that coin into account, 
	then the fewest number of coins we can get is 1+coinChange(amount-that_coin_value). So for all the coins, we 
	return the smallest number as min(1+coinChange(amount-coin1_value), 1+coinChange(amount-coin2_value, ......).

	As we can see it is recursive, the solution is as below, this solution of upper time complexity O(c^n) where c 
	is number of different denominations and n is the amount given, which is exponential:
*/
class CoinChange {
	//Recursive
	public int coinChange(int[] coins, int amount) {
        if(amount==0)
            return 0;
        int n = amount+1;
        for(int coin : coins) {
            int curr = 0;
            if (amount >= coin) {
                int next = coinChange(coins, amount-coin);
                if(next >= 0)
                    curr = 1+next;
            }
            if(curr > 0)
                n = Math.min(n,curr);
        }
        int finalCount = (n==amount+1) ? -1 : n;
        return finalCount;
    }


    //DP
    Map<Integer,Integer> amountDict = new HashMap<Integer,Integer>();
    public int coinChange(int[] coins, int amount) {
        if(amount==0)
            return 0;
        if(amountDict.containsKey(amount))
            return amountDict.get(amount);
        int n = amount+1;
        for(int coin : coins) {
            int curr = 0;
            if (amount >= coin) {
                int next = coinChange(coins, amount-coin);
                if(next >= 0)
                    curr = 1+next;
            }
            if(curr > 0)
                n = Math.min(n,curr);
        }
        int finalCount = (n==amount+1) ? -1 : n;
        amountDict.put(amount,finalCount);
        return finalCount;
    }


    //better DP, dp[i] is holding the min coins that consist number i
    public int coinChange(int[] coins, int amount) {
    	if(coins == null || coins.length == 0)
    		return -1;
    	if(amount <= 0)
    		return 0;

    	int[] dp = new int[amount+1];
    	for(int i = 1; i < dp.length; i++) {
    		dp[i] = amount + 1;
    	}

    	for(int coin : coins) {
    		for(int i = coin; i <= amount; i++) {
    			dp[i] = Math.min(dp[i], dp[i-coin] + 1);
    		}
    	}
    	return dp[amount] > amount ? -1 : dp[amount];
    }
}