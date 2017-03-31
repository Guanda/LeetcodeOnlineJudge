/*
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].

Analysis:
	The key to solve this problem is defining a Comparator based on "start" to sort the 
	arraylist of intervals and then merge intervals
*/
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class MergeIntervals {
	public List<Interval> merge(List<Interval> intervals) {
		if(intervals == null || intervals.size() <= 1)
			return intervals;

		//sort the intervals
		Collections.sort(intervals, new IntervalComparator());

		List<Interval> result = new ArrayList<Interval>();

		Interval prev = intervals.get(0);
		for(Interval interval : intervals) {
			if(prev.end >= interval.start) {
				//merge case
				Interval merged = new Interval(prev.start, Math.max(prev.end, interval.end));
				prev = merged;
			}
			else {
				result.add(prev);
				prev = interval;
			}
		}
		result.add(prev);
		return result;
	}
}

class IntervalComparator implements Comparator<Interval> {
	public int compare(Interval i1, Interval i2) {
		return i1.start - i2.start;
	}
}