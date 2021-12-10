public class MergeSort
{
	private static void merge(Comparable[] a,Comparable[] aux,int lo,int mid,int hi)
	{
		assert isSorted(a,lo,mid);
		assert isSorted(a,mid+1,hi);

		for(int k=lo;k<=hi;k++)
			aux[k]=a[k];

		int i=lo, j = mid+1;

		for(int k=lo;k<=hi;k++)
		{
			if    	  (i>mid)	  			a[k] = aux[j++];
			else if   (j>hi)      			a[k] = aux[i++];
			else if   (less(aux[j],aux[i])) a[k] = aux[j++];
			else							a[k] = aux[i++];
		}

		assert isSorted(a,lo,hi);
	}

	private static void sort(Comparable[] a,Comparable[] aux,int lo,int hi)
	{
		if(lo<hi)
		{
			int mid = lo + (hi-lo)/2;
			sort(a,aux,lo,mid);
			sort(a,aux,mid+1,hi);
			merge(a,aux,lo,mid,hi);
		}
	}

	public static void sort(Comparable[] a)
	{
		Comparable[] aux = new Comparable[a.length];
		sort(a,aux,0,a.length-1);
	}

	public static boolean isSorted(Comparable[] a,int i,int j)
	{
		for(int k=i;k<j;k++)
		{
			if(less(a[k+1],a[k]))
				return false;
		}
		return true;
	}

	private static boolean less(Comparable v,Comparable w)
	{
		return v.compareTo(w)<0;
	}

	public static void main(String[] args)
	{
		String a[] = {"A","S","O","M","E","W","H","A","T","L","O","N","G","M","E","R","G","E","S","O","R","T","E","X","A","M","P","L","E"};
		sort(a);
		for(String c : a)
			System.out.printf("%s ",c);
		System.out.println();

	}
}