import java.util.Scanner;

public class FixedCapacityBinaryHeapMaxPQ<T extends Comparable<T>>
{
	private T[] pq;
	private int N;     // Always points 1 less than where element has to be inerted.
	private int size;

	public FixedCapacityBinaryHeapMaxPQ(int capacity)
	{
		pq = (T[]) new Comparable[capacity+1];
		N = 0;
		size = 0;
	}

	public boolean isEmpty()
	{	return size==0; }

	private void swim(int k)
	{
		// parent of node k is at k/2
		while(k>1 && less(k/2,k))
		{
			exch(k,k/2);
			k = k/2;
		}
	}

	public void insert(T x)
	{
		// insertion begins at index 1;
		pq[++N] = x;
		swim(N);
		size++;
	}

	private void sink(int k)
	{
		// k goes till last parent key
		while(2*k <= N)
		{
			int j = 2*k;
			
			// Choosing child with larger key
			// Also (j<N) to ensure that j++ exists
			if(j<N && less(j,j+1))
				j++;

			if(!less(k,j))
				break;

			exch(k,j);

			k = j;
		}
	}

	public T delMax()
	{
		T max = pq[1];
		exch(1,N--);
		sink(1);
		pq[N+1] = null;
		size--;
		return max;
	}

	public T topMax()
	{
		T max = pq[1];
		return max;
	}

	private boolean less(int i,int j)
	{	return pq[i].compareTo(pq[j])<0; }

	private void exch(int i,int j)
	{	
		T temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		FixedCapacityBinaryHeapMaxPQ<String> pq = new FixedCapacityBinaryHeapMaxPQ<String>(11);

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