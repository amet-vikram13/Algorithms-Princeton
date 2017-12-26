public class InsertionSort
{
	public static void sort(Comparable[] a)
	{
		/*
		Comparable key;
		int j;
		int N = a.length;
		for(int i=1;i<N;i++)
		{
			key=a[i];
			j=i-1;
			while((j>-1)&&(less(key,a[j])))  // Same Cost
			{
				exch(a,j+1,j);
				j--;
			}
			a[j+1]=	key;
		}
		*/
		int N = a.length;
		for(int i=0;i<N;i++)
			for(int j=i;j>0;j--)
				if(less(a[j],a[j-1]))
					exch(a,j,j-1);
				else
					break;
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
		String[] a = {"L","E","E","A","M","H","L","E","P","S","O","L","T","S","X","R"};
		sort(a);
		for(int i=0;i<a.length;i++)
			System.out.printf("%s ",a[i]);
		System.out.println();

	}
}

