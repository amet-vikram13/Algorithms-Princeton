import java.util.*;

public class WQUFPC
{
  private int[] id;
  private int[] sz; // Weighted
  private int   count;

  public WQUFPC(int N)
  {
    count = N;
    id    = new int[N];
    sz  = new int[N];
    for(int i=0;i<N;i++)
    {
      id[i]   = i;
      sz[i]   = 1;
    }
  }

  public int count()
  { return count; }

  public int root(int i)
  {
    while(i != id[i])
    {
      id[i] = id[id[i]]; // Path Compression
      i = id[i];
    }
    return i;
  }

  public boolean connected(int p,int q)
  { return root(p) == root(q); }

  public void union(int p,int q)
  {
    int proot = root(p);
    int qroot = root(q);

    if (proot == qroot) return;

    if (sz[p] > sz[q])
    {
      id[qroot] = proot;
      sz[p] += sz[q];
      count--;
    }
    else
    {
      id[proot] = qroot;
      sz[q] += sz[p];
      count--;
    }
  }
}
