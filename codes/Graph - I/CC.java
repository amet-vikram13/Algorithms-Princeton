public class CC
{
	private boolean[] marked;
	private int[] id;  // connected component identifier
	private int count; // number of components
	
	public CC(GraphAPI G)
	{
		marked = new boolean[G.V()];
		id     = new int[G.V()];
		count  = 0;
		for(int v = 0;v<G.V();v++)
		{
			if(!marked[v])
			{
				count++;
				dfs(G,v);
			}
		}
	}
	private void dfs(GraphAPI G,int v)
	{
		marked[v] = true;
		id[v] = count;
		for(int w : G.adj(v))
			if(!marked[w])
				dfs(G,w);
	}

	// are v and w connected ?
	public boolean connected(int v,int w)
	{
		return id[v]==id[w];
	}

	// number of connected components
	public int count()
	{
		return count;
	}

	// component identifier for v
	public int id(int v)
	{
		return id[v];
	}

}