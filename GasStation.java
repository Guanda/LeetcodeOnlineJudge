/*
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to 
its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.

Analysis:
	The idea is: consider the case that, if started at station i, and when goes to the station j, 
	there is not enough gas to go the j+1 station. What happened now? For the brutal force method, 
	we go back to the sataion i+1 and do the same thing. But, actually, if the accumutive gas cannot 
	make it from j to j+1, then the stations from i to j are all not the start station. That is 
	because, (1)the tank is unlimited, every time arrive to the station, the tank will fuel the max 
	gas here, and comsume the cost to go to the next. (2)There can not be negative tank when arriving 
	a station, at least the tank is empty. So, if i to j cannot go to j+1, then i+1 to j still cannot 
	go to j+1... In this way, the next starting station we will try is not i+1, but the j+1. And after 
	a single loop from i to j, we can find the result!

	Summary:
	1. If car starts at A and can not reach B. Any station between A and B
	   can not reach B.(B is the first station that A can not reach.)
	2. If the total number of gas is bigger than the total number of cost. There must be a solution.
*/
class GasStation {
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int sum = 0;
		int total = 0;
		int j = -1;
		for(int i = 0; i < gas.length; i++) {
			sum += gas[i] - cost[i];
			total += gas[i] - cost[i];
			if(sum < 0) {
				j = i;
				sum = 0;
			}
		}

		if(total < 0)
			return -1;
		else
			return j+1;
	}
}