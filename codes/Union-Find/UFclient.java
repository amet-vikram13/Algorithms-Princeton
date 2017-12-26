import java.util.Scanner;

public class UFclient
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		//QuickFindUF qf = new QuickFindUF(N);
		QuickUnionUF qu = new QuickUnionUF(N);
		while(sc.hasNext())
		{
			int p = sc.nextInt();
			int q = sc.nextInt();
			if(!qu.connected(p,q))
			{
				qu.union(p,q);
				//ystem.out.println(p+" "+q);
				qu.printids();
			}

		}
	}
}