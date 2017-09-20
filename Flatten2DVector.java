/*
Implement an iterator to flatten a 2d vector.

Example
Given 2d vector =
[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, the order of elements returned by 
next should be: [1,2,3,4,5,6].
*/

class Flatten2DVector {
    Queue<Integer> queue = new LinkedList<>();

    public Vector2D(List<List<Integer>> vec2d) {
        // Initialize your data structure here
        for(List<Integer> list : vec2d) {
            for(int i : list) {
                queue.add(i);
            }
        }
    }

    @Override
    public Integer next() {
        // Write your code here
        if(!queue.isEmpty()) {
            return queue.poll();
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        // Write your code here
        return !queue.isEmpty();
    }

    @Override
    public void remove() {}
}


/////////better version/////////////////////////////////////////////////////////

class Flatten2DVector {
    private Iterator<List<Integer>> i;
    private Iterator<Integer> j;

    public Vector2D(List<List<Integer>> vec2d) {
        // Initialize your data structure here
        i = vec2d.iterator();
        j = null;
    }

    @Override
    public Integer next() {
        // Write your code here
        hasNext();
        return j.next();
    }

    @Override
    public boolean hasNext() {
        // Write your code here
        while ((j == null || !j.hasNext()) && i.hasNext())
            j = i.next().iterator();
        return j != null && j.hasNext();
    }

    @Override
    public void remove() {}
}