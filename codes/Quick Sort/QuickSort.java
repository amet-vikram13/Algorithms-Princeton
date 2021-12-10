import java.util.*;

public class QuickSort
{
	private static void _sort(int[] arr,int l,int r)
	{
		if (l < r)
		{
			int i = l-1;
			for(int j=l;j<r-1;j++)
			{
				if (arr[j] < arr[r])
				{
					i++;
					int tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
			int tmp = arr[i+1];
			arr[i+1] = arr[r];
			arr[r] = tmp;
			int pivot = i+1;

			_sort(arr,l,pivot-1);
			_sort(arr,pivot+1,r);
		}
	}

	private static void sort(int[] arr,int n)
	{
		int l = 0;
		int r = n-1;
		_sort(arr,l,r);
	}

	public static void main(String[] args)
	{
		int[] arr = {10,9,8,7,6,5,4,3,2,1};
		QuickSort.sort(arr,10);
		for(int i=0;i<10;i++)
			System.out.printf("%d ",arr[i]);
		System.out.println();
	}
}