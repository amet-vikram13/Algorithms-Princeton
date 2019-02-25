import java.util.*;

public class Solution
{
  public static void main(String[] args)
  {
    Scanner sc = new Scanner(System.in);
    int v = sc.nextInt();
    int l = sc.nextInt();
    EdgeWeightedGraph G = new EdgeWeightedGraph(v);

    while(l!=0)
    {
      int x = sc.nextInt();
      int y = sc.nextInt();
      double w = sc.nextDouble();
      Edge e = new Edge(x,y,w);
      G.addEdge(e);
      l--;
    }

    LazyPrimMST k = new LazyPrimMST(G);

    ArrayDeque<Edge> ans = new ArrayDeque<>();
    ans = k.edges();


    while(!ans.isEmpty())
    {
      Edge p = ans.remove();
      int a = p.either();
      int b = p.other(a);
      double c = p.weight();
      System.out.printf("%d %d %f\n",a,b,c);
    }
  }
}
