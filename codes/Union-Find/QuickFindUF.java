public class QuickFindUF
{
	private int id[];
	private int k;

	public QuickFindUF(int N)
	{
		id = new int[N];
		for(k=0;k<N;k++)
			id[k] = k;
	}

	public boolean connected(int p,int q)
	{
		return id[p]==id[q];
	}

	public void union(int p,int q)
	{
		int pid = id[p];
		int qid = id[q];

		for(k=0;k<id.length;k++)
		{
			if(id[k]==pid)
				id[k]=qid;
		}
	}

}