import java.util.*;

public class Graph
{
	public static int degree(GraphAPI G,int v)
	{
		int degree = 0;
		for(int w : G.adj(v))
			degree++;
		return degree;
	}

	public static int maxDegree(GraphAPI G)
	{
		int max = 0;
		for(int v=0;v<G.V();v++)
			if(degree(G,v) > max)
				max = degree(G,v);
		return max;
	}

	public static double averageDegree(GraphAPI G)
	{
		return (2.0*G.E()/G.V());
	}

	public static int numberOfSelfLoops(GraphAPI G)
	{
		int count = 0;
		for(int v = 0;v<G.V();v++)
			for(int w : G.adj(v))
				if(w==v) count++;
		return count/2;  // each edge is counted twice
	}

	// For Undirected Graph Ony 
	// DFS for a connected graph produces a tree.
	// i.e for a node v it moves forward or deeper or in 
	// any sense and when there is no path ahead it 
	// returns back to node v and moves to next node 
	// and its connected node (notion of Topposort)
	private static class UndirectedCycle
	{
		private boolean[] marked;
		private boolean   hasCycle;

		public UndirectedCycle(GraphAPI G)
		{
			marked = new boolean[G.V()];
			for(int s=0;s<G.V();s++)
				if(!marked[s])
					dfs(G,s,s);
		}
		private void dfs(GraphAPI G,int v,int u)
		{
			marked[v] = true;
			for(int w : G.adj(v))
				if(!marked[w])
					dfs(G,w,v);
				else if (w!=u) 
				{
					/*
					Here in the function(v,u) 
					we keep a track of where v is coming
					from by using variable u and then we check
					adjacents of v that are elements : w.
					If for any element w which is already visited
					and if this elemet != u then we get ourself a cycle 
					which is in graph rooted with u i.e

					               X
					            /     \
					      u=====v=====w

					w already visited but it node u as it is visted by X.
					If X doesn't exit then w and u has to be SAME.
					*/

					hasCycle = true;
				}
		}	

		public boolean hasCycle()
		{	return hasCycle; }
	}

	/*
	Cant use UndiretedCycle because it is possible
	to reach a mark node from two different parent 
	components and doesn't form a cycle which in case of
	undirected always forms a cycle.
				u -> v -> x <- w
	*/
	private static class DirectedCycle
	{
		private boolean[] marked;
		private boolean[] recurStack;
		boolean hasCycle;

		public DirectedCycle(GraphAPI G)
		{
			hasCycle   = false;
			marked     = new boolean[G.V()];
			recurStack = new boolean[G.V()];
			for(int s=0;s<G.V();s++)
				if(!marked[s])
					dfs(G,s);
		}
		private void dfs(GraphAPI G,int v)
		{
			marked[v]     = true;
			recurStack[v] = true;
			for(int w : G.adj(v))
				if(!marked[w])
					dfs(G,w);
				else if (recurStack[w]) { hasCycle = true; }
			recurStack[v] = false;
		}

		boolean hasCycle()
		{ return hasCycle; }
	}
	// second method
	private class DirectedCycleColor
	{
		//enum { WHITE,GREY,BLACK };
		private int[] color;
		private boolean hasCycle;

		public DirectedCycleColor(GraphAPI G)
		{
			hasCycle = false;
			color = new int[G.V()];
			for(int i=0;i<G.V();i++)
				color[i] = 0;

			for(int s=0;s<G.V();s++)
				if(color[s]==0)
					dfs(G,s);
		}
		private void dfs(GraphAPI G,int v)
		{
			color[v] = 1;
			for(int w : G.adj(v))
				if(color[w]==0)
					dfs(G,w);
				else if (color[w]==1) hasCycle = true;
			color[v] = 2;

		}

		private boolean hasCycle()
		{	return hasCycle; }
	}



	private static class TwoColor
	{
		private boolean[] marked;
		private boolean[] color;
		private boolean isTwoColorable  = true;

		public TwoColor(GraphAPI G)
		{
			marked = new boolean[G.V()];
			color  = new boolean[G.V()];
			for(int s = 0;s<G.V();s++)
				if(!marked[s])
					dfs(G,s);
		}
		private void dfs(GraphAPI G,int v)
		{
			marked[v] = true;
			for(int w : G.adj(v))
				if(!marked[w])
				{
					// Adjacents nodes are colored differently 
					color[w] = !color[v];
					dfs(G,w);
				}
				else if (color[w]==color[v]) 
				{
					// If ans adjacent marked node has same
					// color then not bipartite
					isTwoColorable = false;
				}
		}

		public boolean isBipartite()
		{	return isTwoColorable; }
	}

	public static void main(String[] args)
	{
		/*
		GraphAPI G = new GraphAPI(13,"UNDIRECTED");
		G.addEdge(0,1);
		G.addEdge(0,2);
		G.addEdge(0,6);
		G.addEdge(0,5);
		G.addEdge(3,5);
		G.addEdge(3,4);
		//G.addEdge(1,3);
		//G.addEdge(2,3);
		//G.addEdge(2,4);
		G.addEdge(4,6);
		G.addEdge(4,5);
		G.addEdge(7,8);
		G.addEdge(9,10);
		G.addEdge(9,11);
		G.addEdge(9,12);
		G.addEdge(11,12);
		DepthFirstPaths   dfs = new DepthFirstPaths(G,0);
		BreadthFirstPaths bfs = new BreadthFirstPaths(G,0);
		CC                cc  = new CC(G);

		System.out.printf("Number of Vertices : %d\n",G.V());
		System.out.printf("Number of Edges    : %d\n",G.E());
		System.out.printf("Number of CC       : %d\n",cc.count());
		System.out.printf("degree of 9        : %d\n",degree(G,9));
		System.out.printf("Average degree     : %f\n",averageDegree(G));


		if(cc.connected(2,12))
			System.out.printf("is 2 and 12 connected ? : yes\n");
		else
			System.out.printf("is 2 and 12 connected ? : no\n");

		if(dfs.hasPathTo(10))
			System.out.printf("does 0 and 10 has path ? : yes\n");
		else
			System.out.printf("does 0 and 10 has path ? : no\n");

		if(bfs.hasPathTo(3))
			System.out.printf("does 0 and 3 has path ? : yes\n");
		else
			System.out.printf("does 0 and 3 has path ? : no\n");
		
		
		System.out.printf("Shortest path from 0 to 3 : ");
		ArrayDeque<Integer> p = bfs.pathTo(3);
		if(p!=null)
			while(!p.isEmpty())
				System.out.printf("%d ",p.removeFirst());
		System.out.println();

		System.out.printf("Distance path from 0 to 3 : %d\n",bfs.distTo(3));

		UndirectedCycle cy = new UndirectedCycle(G);
		if(cy.hasCycle())
			System.out.printf("Does Graph has Cycle ? : yes\n");
		else
			System.out.printf("Does Graph has Cycle ? : no\n");

		TwoColor bi = new TwoColor(G);
		if(bi.isBipartite())
			System.out.printf("Is Graph Bipartite ? : yes\n");
		else
			System.out.printf("Is Graph Bipartite ? : no\n"); 
		*/
		
		
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt(); int E = sc.nextInt();
		GraphAPI G = new GraphAPI(V,"DIRECTED");
		while(E!=0)
		{
			G.addEdge(sc.nextInt(),sc.nextInt());
			E -= 1;
		}
		DirectedCycle C = new DirectedCycle(G);
		StrongComponents scc = new StrongComponents(G);
		if(C.hasCycle())
			System.out.printf("The Graph has cycle\n");
		else
			System.out.println("The Graph doesnt have cycle");
		System.out.printf("Number of strong components : %d\n",scc.count());
		for(int i=0;i<V;i++)
			System.out.printf("id of %d : %d\n",i,scc.id(i));

	}
}	