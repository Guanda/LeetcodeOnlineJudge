/*
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.

Anaylsis:

Analysis:
Let p be the product of all numbers in the array.

If p > 0, the largest product is p.

If p == 0, there must be at least one 0 in the array. The array should be something like 
[x1, 0, x2, 0, x3...], where x1, x2, x3 is a subarray containing no 0s. So, the largest product 
is maximum of largest product of x1, x2, x3 and 0. We can iterate the array and maintain the 
product of numbers and reset the product to be 1 when an 0 encountered. 

If p < 0, there must be odd number of negative elements.  The array should be something like 
[x1, -y1, x2, -y2, x3...], where x1, x2, x3 is a subarray containing no negative numbers. Since 
the number of negative element is odd in the array, product of x1 and product of [x2, -y2, x3, ...] 
must be positive and could be the potential largest product. Product of x1 could be calculated by 
iterating x1 and product of [x2, -y2, x3, ...] could be calculated by reverse iterating. 
Thus, the largest product must be the product of subarray between 0s (if there are 0s) and the left 
and right subarray of the negative elements. 
*/

class MaximumProductSubarray
{
    public int maxProduct(int[] A) {
        int n = A.length;
        return maxProduct(A, n);
    }
    
    public int maxProduct(int[] A, int n) {
        int maxProd = Integer.MIN_VALUE;
        int product = 1;
        for(int i=0; i <n; i++)
        {
            product = product * A[i];
            maxProd = Math.max(maxProd, product);
            if (A[i] == 0)
                product = 1;
        }
        
        product = 1;
        for(int i=n-1; i >=0; i--)
        {
            product = product * A[i];
            maxProd = Math.max(maxProd, product);
            if (A[i] == 0)
                product = 1;
        }
        
        return maxProd;
    }
}