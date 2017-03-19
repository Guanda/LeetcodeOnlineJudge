/*
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.

Analysis:
    Method 1:
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

    Method 2:
        Loop through the array, each time remember the max and min value for the previous product, the most important 
        thing is to update the max and min value: we have to compare among max * A[i], min * A[i] as well as A[i], since 
        this is product, a negative * negative could be positive.
*/

class MaximumProductSubarray {
    //Method 1
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int maxProd = Integer.MIN_VALUE;
        int product = 1;
        for(int i=0; i <n; i++) {
            product = product * nums[i];
            maxProd = Math.max(maxProd, product);
            if (nums[i] == 0)
                product = 1;
        }
        
        product = 1;
        for(int i=n-1; i >=0; i--) {
            product = product * nums[i];
            maxProd = Math.max(maxProd, product);
            if (nums[i] == 0)
                product = 1;
        }
        
        return maxProd;
    }

    //Method 2, easier to understand
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max = nums[0];
        int min = nums[0];
        int result = nums[0];
        for(int i = 1; i < nums.length; i++) {
            int tmp = max;
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(tmp * nums[i], min * nums[i]), nums[i]);
            if(max > result) {
                result = max;
            }
        }
        return result;
    }
}