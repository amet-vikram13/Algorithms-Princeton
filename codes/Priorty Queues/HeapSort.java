public class HeapSort
{
	public static void sort(Comparable[] pq)
	{
		
		// N points to last element not size of arr
		int N = pq.length-1;

		for(int k = N/2;k>=1;k--)
		{
			sink(pq,k,N);
		}

		while(N>1)
		{
			exch(pq,1,N--);
			sink(pq,1,N);
		}
	}

	private static void sink(Comparable[] pq,int k,int N)
	{
		while(2*k<=N)
		{
			int j = 2*k;

			if(j<N && less(pq,j,j+1))
				j++;

			if(less(pq,j,k))
				break;

			exch(pq,j,k);

			k = j;
		}
	}

	private static boolean less(Comparable[] pq,int i,int j)
	{
		return pq[i].compareTo(pq[j])<0;
	}

	private static void exch(Comparable[] pq,int i,int j)
	{
		Comparable temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}

	public static void main(String[] args)
	{
		String[] arr = {"","S","O","R","T","E","X","A","M","P","L","E"};

		sort(arr);

		for(int i=1;i<arr.length;i++)
			System.out.printf("%s",arr[i]);
		System.out.println();
	}


}