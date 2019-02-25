import java.util.*;

public class EdgeWeightedGraph
{
  private class Bag<T> implements Iterable<T>
  {
    private T[] arr;
    private int N;

    public Bag()
    {
      arr = (T[]) new Object[1];
      N   = 0;
    }

    public void add(T item)
    {
      if (N == arr.length)
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
    { return N>0; }

    public Iterator<T> iterator()
    { return new StackIterator(); }

    private class StackIterator implements Iterator<T>
    {
      private int i = N;
      public T next() { return arr[--i]; }
      public boolean hasNext() {  return i>0; }
    }
  }

  private final int V;
  private int E;
  private Bag<Edge>[] adj;
  private ArrayDeque<Edge> edges;

  public EdgeWeightedGraph(int V)
  {
    this.V = V;
    E = 0;
    adj = (Bag<Edge>[]) new Bag[V];
    for(int i=0;i<V;i++)
      adj[i] = new Bag<Edge>();
    edges = new ArrayDeque<Edge>();
  }

  public void addEdge(Edge e)
  {
    int v = e.either();
    int w = e.other(v);

    // Allows self loops and multiple parallel edges
    adj[v].add(e);
    adj[w].add(e);
    edges.push(e);
    E++;

  }

  public Iterable<Edge> adj(int v)
  { return adj[v]; }

  public int V()
  { return V; }

  public int E()
  { return E; }

  public Iterable<Edge> edges()
  { return edges; }

}
