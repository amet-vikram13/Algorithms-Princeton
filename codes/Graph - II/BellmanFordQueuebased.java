import java.util.*;

public class BellmanFordQueuebased
{
	private double[] distTo;
	private boolean[] onQ;
	private Queue<Integer> q;
	private DirectedEdge[] edgeTo;
	private boolean hasNegativeCycle;
	private int cost;

	private void findNegativeCycle(EdgeWeightedDigraph G)
	{
		int w;
		for(int v=0;v<G.V()&&(!hasNegativeCycle);v++)
		{
			for(DirectedEdge e : G.adj(v))
			{
				w = e.to();
				if (distTo[w]!=Integer.MAX_VALUE && distTo[v]+e.weight() < distTo[w])
				{
					hasNegativeCycle = true;
					break;
				}
			}
		}
	}

	private void relax(EdgeWeightedDigraph G,int v)
	{
		int w;
		for(DirectedEdge e : G.adj(v))
		{
			w = e.to();

			if (distTo[w] > distTo[v] + e.weight())
			{
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if (!onQ[w])
				{
					q.add(w);
					onQ[w] = true;
				}
			}
		}

		if ((cost++)%G.V() == 0)
			findNegativeCycle(G);
	}

	public BellmanFordQueuebased(EdgeWeightedDigraph G,int source)
	{
		hasNegativeCycle = false;
		cost = 0;

		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		onQ = new boolean[G.V()];
		q = new LinkedList<Integer>();

		for(int v=0;v<G.V();v++)
		{
			distTo[v] = Double.POSITIVE_INFINITY;
			edgeTo[v] = new DirectedEdge(-1,-1,-1);
			onQ[v] = false;
		}

		distTo[source] = 0.0;
		q.add(source);
		onQ[source] = true;

		for(int i=0;i<G.V();i++)
		{
			for(int v=0;v<G.V()&&(!hasNegativeCycle);v++)
				relax(G,v);
		}

		while(q.size()!=0 && !hasNegativeCycle)
		{
			relax(G,q.peek());
			onQ[q.peek()] = false;
			q.remove();
		}
	}

	public boolean negativeCycle()
	{	return hasNegativeCycle; }
}