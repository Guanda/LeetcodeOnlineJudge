/*
Description:

Count the number of prime numbers less than a non-negative number, n

Analysis:
	Sieve of Eratosthenes
	Remove all non-prime integers and then count the rest.
*/

class CountPrimes {
	public int countPrimes(int n) {
		boolean[] primes = new boolean[n];
		for(int i = 2; i < n; i++) {
			primes[i] = true;
		}

		//sieve of eratorsthenes
		for(int i = 2; i*i < n; i++) {
			if(primes[i]) {
				//start from i*i, because all the multiple less than i already been implemented.
				int c = i * i;
				while(c < n) {
					primes[c] = false;
					c = c + i;
				}
			}
		}

		int count = 0;
		for(int i = 2; i < n; i++) {
			if(primes[i])
				count++;
		}
		return count;
	}
}