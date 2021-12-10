import java.util.*;

public class BreadthFirstPaths
{
	private final int s;
	private boolean[] marked;
	private int[]     edgeTo;
	private int[]     distTo;  // figure out

	public BreadthFirstPaths(GraphAPI G,int s)
	{
		this.s = s;
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		distTo = new int[G.V()];
		bfs(G,s);
	}
	private void bfs(GraphAPI G,int s)
	{
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.addLast(s);
		marked[s] = true;
		edgeTo[s] = s;
		distTo[s] = -1;
		while(!q.isEmpty())
		{
			int v = q.removeFirst();
			distTo[v] = 1+distTo[edgeTo[v]];
			for(int w : G.adj(v))
				if(!marked[w])
				{
					q.addLast(w);
					marked[w] = true;
					edgeTo[w] = v;
				}
		}
	}

	public int startingPoint()
	{	return s; }

	public boolean hasPathTo(int v)
	{	
		// null translates to false;
		return marked[v]; 
	}

	public int distTo(int v)
	{	return distTo[v]; }

	public ArrayDeque<Integer> pathTo(int v)
	{
		if(!hasPathTo(v)) return null;
		ArrayDeque<Integer> path = new ArrayDeque<>();
		for(int x=v;x!=s;x=edgeTo[x]) // Gives all nodes in path in reverse direction
			path.push(x);
		path.push(s);
		return path;
	}


}