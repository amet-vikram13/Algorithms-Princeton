import java.util.Scanner;

public class WQUFPC
{
	private int[] id;
	private int[] sz;
	private int count;


	public WQUFPC(int N)
	{
		count = N;
		id = new int[N];
		sz = new int[N];
		for(int i=0;i<N;i++)
		{
			id[i]=i;
			sz[i]=1;
		}
	}

	public int count()
	{
		return count;
	}

	public int root(int i)
	{
		while(i!=id[i])
		{
			id[i]=id[id[i]];
			i = id[i];
		}
		return i;
	}

	public boolean connected(int p,int q)
	{
		return root(p)==root(q);
	}

	public void union(int p,int q)
	{
		if(connected(p,q)) return;
		
		int proot = root(p);
		int qroot = root(q);
		if(sz[p]>sz[q])
		{
			id[qroot] = proot;
			sz[p]+=sz[q];
			count--;
		}
		else
		{
			id[proot] = qroot;
			sz[q]+=sz[p];
			count--;
		}
	}

	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		WQUFPC uf = new WQUFPC(sc.nextInt());

		while(sc.hasNextInt())
		{
			int p = sc.nextInt();
			int q = sc.nextInt();

			if(!uf.connected(p,q))
			{
				uf.union(p,q);
				System.out.println(p+" "+q+" "+"components: "+uf.count());
			}
		}
	}
}
