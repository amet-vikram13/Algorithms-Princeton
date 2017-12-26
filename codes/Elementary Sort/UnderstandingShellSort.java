public class UnderstandingShellSort
{
	public static void sort(Comparable[] a)
	{
		int N = a.length;

		int h=1; 
		
		while (h<N/3)
			h=h*3+1;

		while(h>=1)
		{
			System.out.printf("h: %d\n",h);
			for(int i=h;i<N;i++)
			{
				System.out.printf("\ti: %d\n",i);
				for(int j=i;j>=h && less(a[j],a[j-h]);j-=h)
				{
					System.out.printf("\t\tj: %d j-h:%d\n",j,j-h);
					exch(a,j-h,j);
				}

			}
			h=h/3;
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
		//String[] a = {"L","E","E","A","M","H","L","E","P","S","O","L","T","S","X","R"};
		Integer a[]={20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0};		
		sort(a);
		for(int i=0;i<a.length;i++)
			System.out.printf("%d ",a[i]);
		System.out.println();

	}
}
