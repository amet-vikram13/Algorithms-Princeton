import java.util.*;

public class GraphAPI
{
	private class Bag<T> implements Iterable<T>
	{
		private T[] arr;
		private int N;		// curr empty position

		public Bag()
		{
			arr = (T[]) new Object[1];
			N = 0;
		}

		public void add(T item)
		{
			if(N==arr.length)
				resize(2*N);
			arr[N++] = item;
		}
		private void resize(int capacity)
		{
			T[] copy = (T[]) new Object[capacity];
			for(int i=0;i<N;i++)
				copy[i] = arr[i];
			arr = copy;
		}

		public boolean isEmpty()
		{	return N>0; }

		public int size()
		{	return N; }

		public Iterator<T> iterator()
		{
			return new StackIterator();
		}
		private class StackIterator implements Iterator<T>
		{
			private int i = N;
			public boolean hasNext() {return i>0; }
			public T next() {return arr[--i]; }

		}
	}

	private final int V;        // number of vertices from 0 to V-1
	private int E;
	private Bag<Integer>[] adj;
	private String type;

	public GraphAPI(int V,String S)
	{
		type = S;
		this.V = V;
		adj = (Bag<Integer>[]) new Bag[V]; // Maintaining an adjacent-list graph representation
		for(int v=0;v<V;v++)
			adj[v] = new Bag<Integer>();
	}

	public void addEdge(int v,int w)
	{
		if(type=="UNDIRECTED")
			addEdgeUndirected(v,w);
		if(type=="DIRECTED")
			addEdgeDirected(v,w);
	}
	private void addEdgeDirected(int v,int w)
	{
		// loops not allowed
		if(v==w)
			return;

		// Multiple edges not allowed
		boolean flag = true;
		if(adj[v].isEmpty() || adj[w].isEmpty())
			flag = true;
		else
		{
			for(int k : adj[v])
				if(w==k)
				{
					flag = false;
					break;
				}
		}
		
		if(flag)
		{
			adj[v].add(w);
			// For directed only v->w NOT w->v so not required
			E++;
		}	
	}
	private void addEdgeUndirected(int v,int w)
	{
		// loops not allowed
		if(v==w)
			return;

		// Multiple edges not allowed
		boolean flag = true;
		if(adj[v].isEmpty() || adj[w].isEmpty())
			flag = true;
		else
		{
			for(int k : adj[v])
				if(w==k)
				{
					flag = false;
					break;
				}
		}
		
		if(flag)
		{
			adj[v].add(w);
			adj[w].add(v);
			E++;
		}
		
	}

	public GraphAPI reverse()
	{
		if(type=="UNDIRECTED")
			return null;

		GraphAPI G = new GraphAPI(V,"DIRECTED");
		for(int v=0;v<V;v++)
			for(int w : adj[v])
				G.addEdge(w,v);		
		
		return G;
	}

	public Iterable<Integer> adj(int v)
	{
		return adj[v];
	}

	public int V()
	{
		return V;
	}

	public int E()
	{
		return E;
	}
}