/*
Implement int sqrt(int x).

*/

class Sqrt {
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
}