/*
A Range Module is a module that tracks ranges of numbers. Your task is to design 
and implement the following interfaces in an efficient manner.

addRange(int left, int right) Adds the half-open interval [left, right), tracking 
	every real number in that interval. Adding an interval that partially overlaps with 
	currently tracked numbers should add any numbers in the interval [left, right) that 
	are not already tracked.
queryRange(int left, int right) Returns true if and only if every real number in the 
	interval [left, right) is currently being tracked.
removeRange(int left, int right) Stops tracking every real number currently being 
	tracked in the interval [left, right).

Example 1:
addRange(10, 20): null
removeRange(14, 16): null
queryRange(10, 14): true (Every number in [10, 14) is being tracked)
queryRange(13, 15): false (Numbers like 14, 14.03, 14.17 in [13, 15) are not being tracked)
queryRange(16, 17): true (The number 16 in [16, 17) is still being tracked, despite 
the remove operation)

Note:
A half open interval [left, right) denotes all real numbers left <= x < right.
0 < left < right < 10^9 in all calls to addRange, queryRange, removeRange.
The total number of calls to addRange in a single test case is at most 1000.
The total number of calls to queryRange in a single test case is at most 5000.
The total number of calls to removeRange in a single test case is at most 1000.
*/

class RangeModule {
    TreeSet<Interval> ranges;

    public RangeModule() {
        ranges = new TreeSet();
    }
    
    public void addRange(int left, int right) {
        Iterator<Interval> iterator = ranges.tailSet(new Interval(0, left - 1)).iterator();
        while(iterator.hasNext()){
            Interval interval = iterator.next();
            if(right < interval.left)
                break;
            left = Math.min(left, interval.left);
            right = Math.max(right, interval.right);
            iterator.remove();
        }
        ranges.add(new Interval(left, right));
    }
    
    public boolean queryRange(int left, int right) {
        Interval interval = ranges.higher(new Interval(0, left));
        return (interval != null && interval.left <= left && interval.right >= right);
    }
    
    public void removeRange(int left, int right) {
        Iterator<Interval> iterator = ranges.tailSet(new Interval(0, left)).iterator();
        List<Interval> todo = new ArrayList<>();
        while(iterator.hasNext()){
            Interval interval = iterator.next();
            if(right < interval.left)
                break;
            if(interval.left < left){
                todo.add(new Interval(interval.left, left));
            }
            if(interval.right > right){
                todo.add(new Interval(right, interval.right));
            }
            iterator.remove();
        }
        for(Interval interval : todo){
            ranges.add(interval);
        }
    }
}

class Interval implements Comparable<Interval> {
    int left;
    int right;
    
    public Interval(int left, int right){
        this.left = left;
        this.right = right;
    }
    
    public int compareTo(Interval that){
        if(this.right == that.right){
            return this.left - that.left;
        }
        return this.right - that.right;
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */