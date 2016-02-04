/*
Implement int sqrt(int x).

Analysis:
	1. Use Newton Method
	2. Use binary search

*/

class Sqrt {
	//newton method
	public int sqrt(int n) {
		if(n == 0)
			return 0;
		double last = 0.0;
		double res = 1.0;
		while(last != res) {
			last = res;
			res = (res + n / res) / 2;
		}
		return (int)res;
	}

	//binary search
	public int sqrt2(int n) {
		long i = 0;
		long j = n / 2 + 1;
		while(i <= j) {
			long mid = (i + j) / 2;
			if(mid * mid == n)
				return (int)mid;
			else if(mid * mid < n) {
				i = mid + 1;
			}
			else {
				j = mid - 1;
			}
		}
		return (int)j;
	}
}