import java.util.Scanner;

public class UnderstandingQueue
{
	private String[] q;
	private int N;
	private int first;
	private int last;

	public UnderstandingQueue()
	{
		q = new String[8];
		q[0] = "aq";
		q[1] = "bq";
		q[2] = "cq";
		q[3] = "dq";
		q[4] = "eq";
		q[5] = "fq";
		q[6] = "gq";
		q[7] = null;
		first = 0;
		last = 7;
		N = 7;
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

	public void printarray()
	{
		for(int i=first;i<last;i++)
		{
			System.out.printf("%s ",q[i]);
		}
		System.out.println();
	}

	public int returnN()
	{
		return N;
	}

	public int returnfirst()
	{
		return first;
	}

	public int returnlast()
	{
		return last;
	}

	public static void main(String args[])
	{
		UnderstandingQueue queue = new UnderstandingQueue();
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			String s = sc.nextLine();
			if(s.equals("-"))
			{
				String item = queue.dequeue();
				int N = queue.returnN();
				int last = queue.returnlast();
				int first = queue.returnfirst();
				System.out.printf("Item:%s N:%d first:%d last:%d\n",item,N,first,last);
				queue.printarray();
			}
			else
			{
				queue.enqueue(s);
				int N = queue.returnN();
				int last = queue.returnlast();
				int first = queue.returnfirst();
				System.out.printf("N:%d first:%d last:%d\n",N,first,last);
				queue.printarray();
			}
		}
	}

}

