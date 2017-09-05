/*
Follow up Zigzag Iterator: What if you are given k 1d vectors? How well can your code be extended 
to such cases? The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" 
does not look right to you, replace "Zigzag" with "Cyclic".

Example
Given k = 3 1d vectors:
[1,2,3]
[4,5,6,7]
[8,9]
Return [1,4,8,2,5,9,3,6,7].
*/

public class ZigzagIterator2 {
    
    List<Iterator<Integer>> iterList = new ArrayList<>();
    int count;
    
    /**
     * @param vecs a list of 1d vectors
     */
    public ZigzagIterator2(ArrayList<ArrayList<Integer>> vecs) {
        // initialize your data structure here.
        for(ArrayList<Integer> list : vecs) {
            if(list.size() > 0) {
                iterList.add(list.iterator());   
            }
        }
        count = 0;
    }

    public int next() {
        // Write your code here
        int curr = iterList.get(count).next();
        if(iterList.get(count).hasNext()) {
            count = (count + 1) % iterList.size();
        }
        else {
            iterList.remove(count);
            if(iterList.size() > 0) {
                count = count % iterList.size();
            }
        }
        return curr;
    }

    public boolean hasNext() {
        // Write your code here
        return iterList.size() > 0;
    }
}

/**
 * Your ZigzagIterator2 object will be instantiated and called as such:
 * ZigzagIterator2 solution = new ZigzagIterator2(vecs);
 * while (solution.hasNext()) result.add(solution.next());
 * Output result
 */