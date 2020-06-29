import java.util.Scanner;

public class LinkedStackOfStrings
{
	private Node first = null;

	private class Node
	{
		String item;
		Node next;
	}

	public LinkedStackOfStrings()
	{

	}

	public LinkedStackOfStrings(String item)
	{
		push(item);
	}

	public boolean isEmpty()
	{
		return first==null;
	}

	public void push(String item)
	{
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}

	public String pop()
	{
		String item = first.item;
		first = first.next;
		return item;
	}

	public static void main(String args[])
	{
		LinkedStackOfStrings stack = new LinkedStackOfStrings();
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			String s = sc.nextLine();
			if(s.equals("-"))
				System.out.println(stack.pop());
			else
				stack.push(s);
		}
	}
}