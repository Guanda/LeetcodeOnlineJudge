/*
Given a n x n matrix where each of the rows and columns are sorted in ascending order, 
find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:
matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,
return 13.

Analysis:
	Solution 1 : Heap
	Here is the step of my solution:

	Build a minHeap of elements from the first row.
	Do the following operations k-1 times :
	Every time when you poll out the root(Top Element in Heap), you need to know the row number and column 
	number of that element(so we can create a tuple class here), replace that root with the next element 
	from the same column.

*/
class KthSmallestElementInSortedMatrix {
	public int kthSmallest(int[][] matrix, int k) {
		int n = matrix.length;
		PriorityQueue<Tuple> queue = new PriorityQueue<>();
		//push first row to queue
		for(int i = 0; i < n; i++) {
			queue.offer(new Tuple(0, i, matrix[0][i]));
		}
		//replace the first k-1 elements
		for(int i = 0; i < k-1; i++) {
			Tuple t = queue.poll();
			if(t.x == n-1)
				continue;
			queue.offer(new Tuple(t.x + 1, t.y, matrix[t.x + 1][t.y]));
		}

		return queue.poll().val;
	}
}

class Tuple implements Comparable<Tuple> {
	int x, y, val;
    public Tuple (int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    @Override
    public int compareTo (Tuple that) {
        return this.val - that.val;
    }
}