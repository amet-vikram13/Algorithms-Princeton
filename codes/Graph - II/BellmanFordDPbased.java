import java.util.*;

public class BellmanFordDPbased
{
	private double[] distTo;
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
				if (distTo[v]!=Double.POSITIVE_INFINITY && distTo[v]+e.weight()<distTo[w])
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

			if (distTo[v]!=Double.POSITIVE_INFINITY && distTo[w]>distTo[v]+e.weight())
			{
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
			}
		}

		if ((cost++)%G.V() == 0)
			findNegativeCycle(G);
	}

	public BellmanFordDPbased(EdgeWeightedDigraph G,int source)
	{
		hasNegativeCycle = false;
		cost = 0;

		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];

		for(int v=0;v<G.V();v++)
		{
			distTo[v] = Double.POSITIVE_INFINITY;
			edgeTo[v] = new DirectedEdge(-1,-1,-1);
		}

		distTo[source] = 0.0;

		for(int i=0;i<G.V();i++)
		{
			for(int v=0;v<G.V()&&(!hasNegativeCycle);v++)
				relax(G,v);
		}
	}

	public boolean negativeCycle()
	{	return hasNegativeCycle; }
}