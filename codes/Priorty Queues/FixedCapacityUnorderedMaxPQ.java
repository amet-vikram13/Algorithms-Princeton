import java.util.Scanner;

public class FixedCapacityUnorderedMaxPQ<T extends Comparable<T>>
{
	private T[] pq;	// pq[i] = ith element on pq
	private int N;	// number of elements on pq

	public FixedCapacityUnorderedMaxPQ(int capacity)
	{
		pq = (T[]) new Comparable[capacity];
		N = 0;
	}

	public boolean isEmpty()
	{
		return N==0;
	}

	public void insert(T x)
	{
		pq[N++] = x;
	}

	public T delMax()
	{
		int max = 0;
		for(int i=1;i<N;i++)
			if(less(pq[max],pq[i]))
				max = i;
		exch(pq,max,N-1);
		T x =  pq[--N];
		pq[N] = null;
		return x;
	}

	private boolean less(Comparable v,Comparable w)
	{
		return v.compareTo(w)<0;
	}

	private void exch(Comparable[] pq,int i,int j)
	{
		Comparable temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}

	public static void main(String[] args)
	{
		FixedCapacityUnorderedMaxPQ<String> pq = new FixedCapacityUnorderedMaxPQ<String>(10);
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			String s = sc.nextLine();
			if(s.equals("-"))
				System.out.println(pq.delMax());
			else
				pq.insert(s);
		}

	}
}