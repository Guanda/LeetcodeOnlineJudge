//不用内置Stack的实现

class MinStack2
{
	Node top = null;

	public void push(int x)
	{
		if(top == null)
		{
			top = new Node(x);
			top.min = x;
		}
		else
		{
			Node tmp = new Node(x);
			tmp.next = top;
			top = tmp;
			top.min = Math.min(top.next.min, x);
		}
	}

	public void pop()
	{
		top = top.next;
	}

	public int top()
	{
		return top == null ? 0 : top.val;
	}

	public int getMin()
	{
		return top == null ? 0 : top.min;
	}
}

class Node
{
	int val;
	int min;
	Node next;

	public Node(int val)
	{
		this.val = val;
	}

}