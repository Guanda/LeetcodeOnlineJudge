/*
Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. 
For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers 
given primes = [2, 7, 13, 19] of size 4.

Note:
(1) 1 is a super ugly number for any given primes.
(2) The given numbers in primes are in ascending order.
(3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.

*/
class SuperUglyNumber {
	// Faster solution
	public int nthSuperUglyNumberI(int n, int[] primes) {
	    int[] ugly = new int[n];
	    int[] idx = new int[primes.length];

	    ugly[0] = 1;
	    for (int i = 1; i < n; i++) {
	        //find next
	        ugly[i] = Integer.MAX_VALUE;
	        for (int j = 0; j < primes.length; j++)
	            ugly[i] = Math.min(ugly[i], primes[j] * ugly[idx[j]]);
	        
	        //slip duplicate
	        for (int j = 0; j < primes.length; j++) {
	            if (primes[j] * ugly[idx[j]] == ugly[i]) 
	            	idx[j]++;
	        }
	    }

	    return ugly[n - 1];
	}


	// use heap
	public int nthSuperUglyNumber(int n, int[] primes) {
        // write your code here
        PriorityQueue<Long> queue = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();
        for(int i = 0; i < primes.length; i++) {
            queue.add(Long.valueOf(primes[i]));
            set.add(Long.valueOf(primes[i]));
        }
        
        long num = Long.valueOf(1);
        for(int i = 1; i < n; i++) {
            num = queue.poll();
            for(int j = 0; j < primes.length; j++) {
                long curr = Long.valueOf(primes[j]) * num;
                if(!set.contains(curr)) {
                    queue.add(curr);
                    set.add(curr);
                }
            }
        }
        
        return (int)num;
    }
}