/*
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Analysis:

How to determine if three points are on the same line?
The answer is to see if slopes of arbitrary two pairs are the same.

Second, let's see what the minimum time complexity can be.
Definitely, O(n^2). It's because you have to calculate all slopes between any two points.

Then let's go back to the solution of this problem.
In order to make this discussion simpler, let's pick a random point A as an example.
Given point A, we need to calculate all slopes between A and other points. There will be three cases:
Some other point is the same as point A.
Some other point has the same x coordinate as point A, which will result to a positive infinite slope.
General case. We can calculate slope.
We can store all slopes in a hash table. And we find which slope shows up mostly. Then add the number of 
same points to it. Then we know the maximum number of points on the same line for point A.

We can do the same thing to point B, point C...
Finally, just return the maximum result among point A, point B, point C...

*/
/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
class MaxPointsOnALine{
	public int maxPoints(Point[] points) {
		int max = 0;
		for(int i = 0; i < points.length; i++) {
			int samePoints = 1;
			HashMap<Double, Integer> map = new HashMap<Double, Integer>();
			for(int j = i+1; j < points.length; j++) {
				if(points[i].x == points[j].x && points[i].y == points[j].y)
					samePoints++;
				else if(points[i].x == points[j].x) {
					if(!map.containsKey((double)(Integer.MAX_VALUE)))
						map.put((double)(Integer.MAX_VALUE), 1);
					else
						map.put((double)(Integer.MAX_VALUE), map.get((double)Integer.MAX_VALUE) + 1);
				}
				else {
					double slope = (double)(points[i].y - points[j].y) / (double)(points[i].x - points[j].x);
					//the thing is in double, 0.0 is not -0.0
					if(slope == -0.0)
					    slope = 0.0;
					if(!map.containsKey(slope))
						map.put(slope, 1);
					else
						map.put(slope, map.get(slope) + 1);
				}
			}
			int localMax = 0;
			for(Double key : map.keySet()) {
				localMax = Math.max(map.get(key), localMax);
			}
			localMax += samePoints;
			max = Math.max(max, localMax);
		}
		return max;
	}
}