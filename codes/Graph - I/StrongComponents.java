import java.util.*;

// For DAGs ONLY
public class StrongComponents
{
	private boolean[] marked;
	private int[] id;  // connected component identifier
	private int count; // number of components
	
	public StrongComponents(GraphAPI G)
	{
		marked = new boolean[G.V()];
		id     = new int[G.V()];
		count  = 0;
		DepthFirstSearchOrder dfsOrder  = new DepthFirstSearchOrder(G.reverse());
		ArrayDeque<Integer> reversePost = dfsOrder.reversePost();
		for(int k : reversePost)
			System.out.printf("%d ",k);
		for(int v : reversePost)
			if(!marked[v])
			{
				dfs(G,v);
				count++;
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
	public boolean stronglyConnected(int v,int w)
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