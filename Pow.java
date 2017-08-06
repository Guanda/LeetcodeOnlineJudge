/*
Implement pow(x, n).

Notice
You don't need to care about the precision of your answer, it's acceptable if the expected answer 
and your answer 's difference is smaller than 1e-3.

Analysis:
	1. time O(n)
	2. Using binary search, time O(logn)
*/

//Solution 1: time O(n)
public class Solution {
    /**
     * @param x the base number
     * @param n the power number
     * @return the result
     */
    public double myPow(double x, int n) {
        // Write your code here
        if(n == 0 || x == 1.0) {
            return 1.0;
        }
        
        double result = 1.0;
        boolean isNegative = n < 0;

        for(int i = 1; i <= Math.abs(n); i++) {
            result = result * x;
        }
        
        if(isNegative) {
            result = 1.0 / result;
        }
        
        return result;
    }
}

//Solution 2: timw O(logn)
public class Solution {
    /**
     * @param x the base number
     * @param n the power number
     * @return the result
     */
    public double myPow(double x, int n) {
        // Write your code here
        if(x == 1.0 || n == 0) {
            return 1.0;
        }
        
        if(n == Integer.MIN_VALUE){
            x = x * x;
            n = n/2;
        }
        
        boolean isNegative = false;
        if(n < 0) {
            n = -n;
            x = 1 / x;
        }
        
        return n % 2 == 0 ? myPow(x*x, n / 2) : x * myPow(x*x, n / 2);
    }
}