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


///////////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Guanda on 9/23/17.
 */
public class Flatten2DVector {
    private static int row = 0;
    private static int col = 0;
    private static List<List<Integer>> vec;
    public Flatten2DVector(List<List<Integer>> vec2d) {
        vec = vec2d;
    }

    public static Integer next() {
        return vec.get(row).get(col++);
    }

    public static boolean hasNext() {
        //if row<vec.size() && col<list.get(row).size() no need to enter while loop,it's true
        //otherwise, go to the next row
        //see if there's any row left
        while(row<vec.size() && col==vec.get(row).size()) {
            col = 0;
            row++;
        }
        return !(row==vec.size());
    }

    public static void remove() {
        if(col == 0) {
            if(row == 0) {
                return;
            }
            vec.get(row - 1).remove(vec.get(row-1).size() - 1);
        }
        else {
            vec.get(row).remove(col - 1);
        }
        col--;
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> curr1 = new ArrayList<>();
        curr1.add(1);
        curr1.add(2);
        List<Integer> curr2 = new ArrayList<>();
        curr2.add(3);
        List<Integer> curr3 = new ArrayList<>();
        curr3.add(4);
        curr3.add(5);
        curr3.add(6);
        list.add(curr1);
        list.add(curr2);
        list.add(curr3);

        Flatten2DVector iter = new Flatten2DVector(list);
        while(iter.hasNext()) {
            int val = iter.next();
            if(val % 2 == 0) {
                iter.remove();
            }
        }

        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
