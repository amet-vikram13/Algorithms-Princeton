import java.util.*;

public class KruskalMST
{
  private ArrayDeque<Edge> mst = new ArrayDeque<Edge>();

  public KruskalMST(EdgeWeightedGraph G)
  {
    MinPQ<Edge> pq = new MinPQ<Edge>(G.E());
    for(Edge e : G.edges())
      pq.insert(e);

    WQUFPC uf = new WQUFPC(G.V());
    while(!pq.isEmpty() && mst.size() < G.V()-1)
    {
      Edge e = pq.delMin();
      int v  = e.either(); int w = e.other(v);
      if(!uf.connected(v,w))
      {
        uf.union(v,w);
        mst.push(e);
      }
    
    }
  }

  public ArrayDeque<Edge> edges()
  { return mst; }

}
