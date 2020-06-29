import java.util.Scanner;

public class LinkedQueueOfStrings
{
	private Node first = null;
	private Node  last = null;

	public class Node
	{
		String item;
		Node next;
	}

	public LinkedQueueOfStrings()
	{

	}

	public LinkedQueueOfStrings(String item)
	{
		enqueue(item);
	}

	public boolean isEmpty()
	{
		return first==null;
	}

	public void enqueue(String item)
	{
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if(isEmpty())
			first = last;
		else
			oldlast.next = last;
	}

	public String dequeue()
	{
		String item = first.item;
		first = first.next;
		if(isEmpty())
			last = null;
		return item;
	}

	public static void main(String args[])
	{
		LinkedQueueOfStrings queue = new LinkedQueueOfStrings();
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			String s = sc.nextLine();
			if(s.equals("-"))
				System.out.println(queue.dequeue());
			else
				queue.enqueue(s);
		}
	}
}