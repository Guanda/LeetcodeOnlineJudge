/*
There are a row of n houses, each house can be painted with one of the three colors: 
red, blue or green. The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the 
cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Notice
All costs are positive integers.

Example
Given costs = [[14,2,11],[11,14,5],[14,3,10]] return 10
house 0 is blue, house 1 is green, house 2 is blue, 2 + 5 + 3 = 10
*/
class PaintHouse {
	/**
     * @param costs n x 3 cost matrix
     * @return an integer, the minimum cost to paint all houses
     */
    public int minCost(int[][] costs) {
        // Write your code here
        if(costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        
        for(int i = 1; i < costs.length; i++) {
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
        }
        
        int n = costs.length - 1;
        
        return Math.min(costs[n][0], Math.min(costs[n][1], costs[n][2]));
    }

    // same solution, different code
    public int minCost(int[][] costs) {
        if(costs.length == 0)
            return 0;
        int prevR = costs[0][0];
        int prevB = costs[0][1];
        int prevG = costs[0][2];
        for(int i = 1; i < costs.length; i++){
            int currR = Math.min(prevB, prevG) + costs[i][0];
            int currB = Math.min(prevR, prevG) + costs[i][1];
            int currG = Math.min(prevR, prevB) + costs[i][2];
            prevR = currR;
            prevB = currB;
            prevG = currG;
        }
        return Math.min(Math.min(prevR, prevB), prevG);
    }
}