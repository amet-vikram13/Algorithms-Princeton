import java.util.*;

public class MinPQ<T extends Comparable<T> >
{
  private T[] pq;
  private int N;
  private int size;

  public MinPQ(int capacity)
  {
    pq = (T[]) new Comparable[capacity+1];
    N  = 0;
  }

  private boolean less(int i,int j)
  { return pq[i].compareTo(pq[j]) < 0; }

  private void exch(int i,int j)
  {
    T temp  = pq[i];
    pq[i]  = pq[j];
    pq[j]  = temp;
  }

  public void insert(T item)
  {
    pq[++N] = item;
    swim(N);
    size++;
  }

  public int size()
  { return size; }

  private void swim(int i)
  {
    while(i>1 && less(i,i/2))
    {
      exch(i,i/2);
      i = i/2;
    }
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

  public T delMin()
  {
      T item = pq[1];
      exch(1,N);
      pq[N--] = null;
      size--;
      sink(1);
      return item;
  }

  public boolean isEmpty()
  { return N == 0; }

}
