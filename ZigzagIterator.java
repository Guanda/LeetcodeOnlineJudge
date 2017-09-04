/*
Given two 1d vectors, implement an iterator to return their elements alternately.

Example
Given two 1d vectors:

v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned 
by next should be: [1, 3, 2, 4, 5, 6].
*/

public class ZigzagIterator {
    
    Iterator<Integer> i1;
    Iterator<Integer> i2;
    int count;
    
    /**
     * @param v1 v2 two 1d vectors
     */
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        // initialize your data structure here.
        i1 = v1.iterator();
        i2 = v2.iterator();
        count = 0;
    }

    public int next() {
        // Write your code here
        count++;
        if((count % 2 == 1 && i1.hasNext()) || !i2.hasNext()) {
            return i1.next();
        }
        else if((count % 2 == 0 && i2.hasNext()) || !i1.hasNext()) {
            return i2.next();
        }
        
        return -1;
    }

    public boolean hasNext() {
        // Write your code here 
        return i1.hasNext() || i2.hasNext();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator solution = new ZigzagIterator(v1, v2);
 * while (solution.hasNext()) result.add(solution.next());
 * Output result
 */