import java.util.*;

// TopoLogical Sort
/*
There can be multiple topological sorts of a Graph.
For DAG Directed Acyclic Graphs only
DFS for a connected graph produces a tree.
*/
public class DepthFirstSearchOrder
{
	private boolean[] marked;
	private ArrayDeque<Integer> reversePostOrder;

	public DepthFirstSearchOrder(GraphAPI G)
	{
		reversePostOrder = new ArrayDeque<Integer>();
		marked = new boolean[G.V()];
		for(int s=0;s<G.V();s++)
			if(!marked[s])
				dfs(G,s);
	}
	private void dfs(GraphAPI G,int v)
	{
		marked[v] = true;
		for(int w : G.adj(v))
			if(!marked[w])
				dfs(G,w);
		reversePostOrder.addFirst(v);
	}

	public boolean visited(int w)
	{	return marked[w]; }

	public ArrayDeque<Integer> reversePost()
	{	return reversePostOrder; }
}