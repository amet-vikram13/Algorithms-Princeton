import java.util.Scanner;

public class ResizingArrayQueueOfStrings
{
	private String[] q;
	private int N;
	private int first;
	private int last;

	public ResizingArrayQueueOfStrings()
	{
		q = new String[2];
		first = 0;
		last = 0;
		N = 0;
	}

	public void enqueue(String item)
	{
		if(N==q.length)
			resize(2*q.length);
		q[last++] = item;
		if(last==q.length)
			last=0;
		N++;
	}

	public String dequeue()
	{
		String item = q[first];
		q[first++] = null;
		N--;
		if(first==q.length)
			first=0;
		if(N>0 && N==q.length/4)
			resize(q.length/2);
		return item;
	}

	public void resize(int capacity)
	{
		String[] copy = new String[capacity];
		for(int i=0;i<N;i++)
			copy[i] = q[(first+i)%q.length];
		q = copy;
		first = 0;
		last = N;
	}

	public static void main(String args[])
	{
		ResizingArrayQueueOfStrings queue = new ResizingArrayQueueOfStrings();
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