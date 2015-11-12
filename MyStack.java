/*
Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
Notes:
You must use only standard operations of a queue -- which means only push to back, 
peek/pop from front, size, and is empty operations are valid.

Depending on your language, queue may not be supported natively. You may simulate a 
queue by using a list or deque (double-ended queue), as long as you use only standard 
operations of a queue.

You may assume that all operations are valid (for example, no pop or top operations 
will be called on an empty stack).

*/

class MyStack 
{
	Queue<Integer> q1 = new LinkedList<Integer>();
	Queue<Integer> q2 = new LinkedList<Integer>();

    // Push element x onto stack.
    public void push(int x) {
     	q1.add(x);   
    }

    // Removes the element on top of the stack.
    public void pop() {
        while(q1.size() > 1) {
            q2.add(q1.poll());
        }
        q1.poll();

        //swith q1 and q2 to be ready for next time call
        Queue<Integer> tmp = new LinkedList<Integer>();
        tmp = q1;
        q1 = q2;
        q2 = tmp;
    }

    // Get the top element.
    public int top() {
        while(q1.size() > 1) {
            q2.add(q1.poll());
        }
        return q1.peek()
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }
}