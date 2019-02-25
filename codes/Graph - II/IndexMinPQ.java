import java.util.*;

public class IndexMinPQ<Key extends Comparable<Key>>
{
  private int maxN;
  private int N;
  private int[] pq;
  private int[] qp;
  private Key[] keys;

  public IndexMinPQ(int maxN)
  {
    this.maxN = maxN;
    N = 0;
    keys = (Key[]) new Comparable[maxN+1];
    pq   = new int[maxN+1];
    qp   = new int[maxN+1];
    for(int i=0;i<=maxN;i++)
      qp[i] = -1;
  }

  private boolean less(int i,int j)
  { return keys[i].compareTo(keys[j]) < 0; }

  private void exch(int i,int j)
  {
    int swap = pq[i];
    pq[i] = pq[j];
    pq[j] = swap;
    qp[pq[i]] = i;
    qp[pq[j]] = j;
  }

  public boolean isEmpty()
  { return N == 0; }

  public boolean contains(int i)
  { return qp[i] != -1;  }

  public int size()
  { return N; }

  public void insert(int i,Key key)
  {
    
    // i is index key is Key
    if(contains(i)) return;
    N++;
    qp[i]   = N;    // (qp[i]=) N is the heap position of the key with index i
    
    pq[N]   = i;    // (pq[i]=) i is index of key in heap position i
                    // heap operations are applied on this array
    
    keys[i] = key;  // (keys[i]=) key is priority of i

    swim(N);
  }

  private void swim(int k)
  {
    while(k>1 && less(k,k/2))
    {
      exch(k,k/2);
      k = k/2;
    }
  }

  public int minIndex()
  { return pq[1]; }

  public Key minKey()
  { return keys[pq[1]]; }

  public int delMin()
  {
    int min = pq[1];
    exch(1,N--);
    sink(1);
    qp[min] = -1;
    keys[min] = null;
    pq[N+1] = -1;
    return min;
  }

  private void sink(int i)
  {
    while(2*i <= N)
    {
      int j = 2*i;

      if(j<N && less(j+1,j))
        j++;

      if(less(i,j))
        break;
      exch(i,j);
      i = j;
    }
  }

  public Key keyOf(int i)
  {
    return keys[i];
  }

  public void changeKey(int i,Key key)
  {
    keys[i] = key;
    swim(qp[i]);
    sink(qp[i]);
  }

  public void increaseKey(int i,Key key)
  {
    keys[i] = key;
    sink(qp[i]);
  }

  public void decreaseKey(int i,Key key)
  {
    keys[i] = key;
    swim(qp[i]);
  }
}
