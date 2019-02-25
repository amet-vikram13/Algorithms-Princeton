import java.util.*;

public class DijkstraAllPairsSP
{
   private DijkstraSP[] all;

   DijkstraAllPairsSP(EdgeWeightedDigraph G)
   {
     all = new DijkstraSP[G.V()];
     for (int v = 0; v < G.V(); v++)
          all[v] = new DijkstraSP(G, v);
   }

   public ArrayDeque<DirectedEdge> path(int s, int t)
   { return all[s].pathTo(t); }

   public double dist(int s, int t)
   { return all[s].distTo(t); }

}
