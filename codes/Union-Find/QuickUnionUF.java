// Weighted quick-union

public class QuickUnionUF
{
	private int[] id;
	private int[] sz;

	public QuickUnionUF(int N)
	{
		id = new int[N];
		sz = new int[N];
		for(int i=0;i<N;i++)
		{	
			id[i]=i;
			sz[i]=1; // Cant be zero
		}
	}

	public int root(int i)
	{
		while(i != id[i])
		{
			//id[i] = id[id[i]]; // Quick Union with Path compresion
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
		int i = root(p);
		int j = root(q);
		if(sz[j]<sz[i]) // i is larger tree
		{
			id[j] = i;
			sz[i] += sz[j];
		}
		else // j is larger tree (default Assumption)
		{
			id[i] = j;
			sz[j] += sz[i];
		}
	}

	public void printids()
	{
		for(int i=0;i<id.length;i++)
			System.out.printf("%d ",id[i]);
		System.out.println();
	}
}
