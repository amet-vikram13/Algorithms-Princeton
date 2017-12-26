import java.util.Scanner;

public class ThreeSumBrute
{
	public static int count(int[] a)
	{
		int N = a.length;
		int number = 0;
		for(int i=0;i<N;i++)
			for(int j=i+1;j<N;j++)
				for(int k=j+1;k<N;k++)
					if(a[i]+a[j]+a[k]==0)
						number++;
		return number;
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int[] a = new int[8];
		int i=0;
		while(i<8)
		{
			a[i] = sc.nextInt();
			i++;
		}
		System.out.println(count(a));
	
	}
}