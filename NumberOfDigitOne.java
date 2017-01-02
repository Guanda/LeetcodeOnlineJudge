/*
Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

For example:
Given n = 13,
Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

Analysis:
	很显然，我们可以按照位一个一个找，即在个位、十位、百位。。。出现过的1。
	现在直接给出N的一般规律，比如N=abced五位数字的时候，我们分析百位c，有三种情况：

	1）c == 0的时候，比如12013，此时百位出现1的是(比12013要小)：00 100 ~ 00 199， 01 100~01 199，……，11 100~ 11 199，共1200个，
	   显然这个有高位数字决定，并且受当前位数影响；
	2）c == 1的时候，比如12113，此时百位出现1的肯定包括c=0的情况，另外还需要考虑低位的情况即：00100 ~ 00113共114个
		(a % 10 == 1 ? 1 : 0) * (b + 1)
	3）c >= 2的时候，比如12213，此时百位出现1的是：00 100 ~ 00 199， 01 100~01 199，……，11 100~ 11 199，12 100 ~ 12 199，
	   共1300个，这个有高位数字决定，其实是加一，并且乘以当前位数. 
	    (a + 8) / 10 * m
*/
class NumberOfDigitOne {
	public int countDigitOne(int n) {
		int ones = 0;
	    for (long m = 1; m <= n; m *= 10) {
	        long a = n / m, b = n % m;
	        ones += (a + 8) / 10 * m + (a % 10 == 1 ? 1 : 0) * (b + 1);
	    }
	    return ones;
	}
}