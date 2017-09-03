/*
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed 
from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape 
photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri 
are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is 
guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect 
rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], 
[15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] 
that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last 
key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has 
zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], 
[20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, 
[...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be 
merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]

Analysis:
	Too difficult to describe the solution here, check online.
*/

class SkylineProblem {
	public List<int[]> getSkyline(int[][] buildings) {
		Map<Integer, List<int[]>> cps = new TreeMap<>(); // ordered by the critical points
		for(int[] b : buildings) {
            cps.putIfAbsent(b[0], new ArrayList<>());
            cps.putIfAbsent(b[1], new ArrayList<>());
            cps.get(b[0]).add(b);
            cps.get(b[1]).add(b);
        }

        // heap for the currently active buildings, descending order (max heap)    
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>(){
           public int compare(int[] b1, int[] b2) {
               return Integer.compare(b2[2], b1[2]);
           } 
        });

        List<int[]> res = new ArrayList<>();
        // iterate from left to right over the critical points
        for(Map.Entry<Integer, List<int[]>> entry : cps.entrySet()) {
            int c = entry.getKey();
            List<int[]> bs = entry.getValue();
            
            for(int[] b : bs) {
                if(c == b[0]) { // this critical point is a left edge of building `b`
                    heap.add(b);
                } else { // right edge
                    heap.remove(b);
                }
            }
            
            if(heap.isEmpty()) {
                // the heap is empty, so the skyline is 0
                res.add(new int[] { c, 0 });
            } else {
                int h = heap.peek()[2];
                if(res.isEmpty() || res.get(res.size() - 1)[1] != h) {
                    // only add the highest rectangle if it different than before
                    res.add(new int[] { c,  h });
                }
            }
        }
        
        return res;
	}
}