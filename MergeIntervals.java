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
	    if(intervals == null || intervals.size() <= 1) {
	        return intervals;
	    }
	    
	    //sort intervals based on start elements
	    Collections.sort(intervals, new Comparator<Intervals>() {
	        public int compare(Interval a, Interval b) {
	            return a.start - b.start;
	        }
	    });
	    
	    List<Interval> result = new ArrayList<>();
	    Interval last = intervals.get(0);
	    for(int i = 1; i < intervals.size(); i++) {
	        Interval curr = intervals.get(i);
	        if(curr.start <= last.end) {
	            last.end = Math.max(last.end, curr.end);
	        }
	        else {
	            result.add(last);
	            last = curr;
	        }
	    }
	    
	    result.add(last);
	    return result;
	}
}

class Interval {
    int start, end;
    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
