import java.util.*;

public class EdgeWeightedDigraph
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
  private Bag<DirectedEdge>[] adj;

  public EdgeWeightedDigraph(int V)
  {
    this.V = V;
    E = 0;
    adj = (Bag<DirectedEdge>[]) new Bag[V];
    for(int i=0;i<V;i++)
      adj[i] = new Bag<DirectedEdge>();
  }

  public void addEdge(DirectedEdge e)
  {
    int v = e.from();
    int w = e.to();

    // Allows self loops and multiple parallel edges
    adj[v].add(e);
    E++;

  }

  public Iterable<DirectedEdge> adj(int v)
  { return adj[v]; }

  public int V()
  { return V; }

  public int E()
  { return E; }
}
