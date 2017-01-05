/*
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. 
So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3
[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:
add(1)
add(2)
findMedian() -> 1.5
add(3) 
findMedian() -> 2

Analysis:
	I keep two heaps (or priority queues):

	Max-heap has the smaller half of the numbers.
	Min-heap has the larger half of the numbers.
	This gives me direct access to the one or two middle values (they're the tops of the heaps), 
	so getting the median takes O(1) time. And adding a number takes O(log n) time.

	Using larger integer types also prevents an overflow error when taking the mean of the two middle numbers.
*/
public class MedianFinder {

	private Queue<Integer> maxHeap = new PriorityQueue(new Comparator<Integer>(){
	   	@Override
	   	public int compare(Integer i1, Integer i2){
	       return Integer.compare(i2, i1);
	   	}
	});

	private Queue<Integer> minHeap = new PriorityQueue(new Comparator<Integer>(){
	   	@Override
	   	public int compare(Integer i1, Integer i2){
	       return Integer.compare(i1, i2);
	   	}
	});

    // Adds a number into the data structure.
    public void addNum(int num) {
        minHeap.offer(num);
        maxHeap.offer(minHeap.poll());

        //if(maxHeap.size() > minHeap.size())
	    if(maxHeap.size() - minHeap.size() == 1){
	        minHeap.offer(maxHeap.poll());
	    }
    }

    // Returns the median of current data stream
    public double findMedian() {
    	//be careful about overflow
		return minHeap.size() > maxHeap.size() ? (double)minHeap.peek() : ((long) (minHeap.peek() + maxHeap.peek()))/2.0;
    }
};

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();