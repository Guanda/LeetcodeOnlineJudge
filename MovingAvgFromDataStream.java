/*
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

For example,
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
*/

class MovingAvgFromDataStream {
    int windowSize;
    Queue<Integer> nums;
    double sum;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        windowSize = size;
        nums = new LinkedList<>();
        sum = 0;
    }
    
    public double next(int val) {
        int size = nums.size();
        if(size == windowSize) {
            sum = sum - nums.poll();
        }
        sum += val;
        nums.add(val);
        return sum / nums.size();
    }
}