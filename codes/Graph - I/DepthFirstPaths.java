import java.util.*;

public class DepthFirstPaths
{
	private final int s;
	private boolean[] marked;
	private int[]     edgeTo;

	public DepthFirstPaths(GraphAPI G,int s)
	{
		this.s = s;
		marked = new boolean[G.V()]; // All initialized to null
		edgeTo = new int[G.V()];     // All intialized to null
		dfs(G,s); // vertices connected to s
	}

	/*
	DFS for a connected graph produces a tree.
	i.e for a node v it moves forward or deeper or in 
	any sense and when there is no path ahead it 
	returns back to node v and moves to next node 
	and its connected node.
	*/
	private void dfs(GraphAPI G,int v)
	{
		marked[v] = true;
		/*
		dfs uses recursion corresponding to 
		putting unvisited vertices on a stack
		*/
		for(int w : G.adj(v))
			if(!marked[w])
			{
				dfs(G,w);
				edgeTo[w] = v; 
			/*
			edgeTo[w] = w - previous
			vertex on path from s to v
			i.e w is current vertex and v is 
			previous vertex OR edge v-w taken 
			to visit w for first time
			*/
			}
	}

	public int startingPoint()
	{	return s; }

	public boolean hasPathTo(int v)
	{	
		// null translates to false;
		return marked[v]; 
	}

	public Iterable<Integer> pathTo(int v)
	{
		if(!hasPathTo(v)) return null;
		ArrayDeque<Integer> path = new ArrayDeque<>();
		for(int x=v;x!=s;x=edgeTo[x]) // Gives all nodes in path in reverse direction
			path.push(x);
		path.push(s);
		return path;
	}
}