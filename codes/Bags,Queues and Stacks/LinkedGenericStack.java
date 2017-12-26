import java.util.Scanner;

public class LinkedGenericStack<Item>
{
	private Node first = null;

	private class Node
	{
		Item item;
		Node next;
	}

	public boolean isEmpty()
	{
		return first==null;
	}

	public void push(Item item)
	{
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}

	public Item pop()
	{
		Item item = first.item;
		first = first.next;
		return item;
	}

	public static void main(String args[])
	{
		LinkedGenericStack<Integer> stack = new LinkedGenericStack<>();
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			int i = sc.nextInt();
			if(i==0)
				System.out.println(stack.pop());
			else
				stack.push(i);
		}
	}
}