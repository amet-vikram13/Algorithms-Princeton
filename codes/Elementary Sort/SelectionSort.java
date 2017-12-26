public class SelectionSort
{
	public static void sort(Comparable[] a)
	{
		int N = a.length;
		for(int i=0;i<N;i++)
		{
			int min = i;
			for(int j=i+1;j<N;j++)
				if(less(a[j],a[min]))
					min = j;
			exch(a,i,min);
		}
	}

	private static boolean less(Comparable v,Comparable w)
	{
		return v.compareTo(w)<0;
	}

	private static void exch(Comparable[] a,int i,int j)
	{
		Comparable swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	public static void main(String[] args)
	{
		Integer[] a = new Integer[5];
		a[0]=50; a[1]=40; a[2]=30; a[3]=20; a[4]=10;
		sort(a);
		for(int i=0;i<a.length;i++)
			System.out.printf("%d ",a[i]);
		System.out.println();

	}
}