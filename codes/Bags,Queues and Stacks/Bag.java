import java.util.*;

public class Bag<T> implements Iterable<T>
{
	private T[] s;
	private int N;

	public Bag()
	{
		s = (T[]) new Object[1];
		N=0;
	}

	public void push(T item)
	{
		if(N==s.length)
			resize(2*s.length);
		s[N++] = item;
	}

	public void resize(int capacity)
	{
		T[] copy = (T[]) new Object[capacity];
		for(int i=0;i<N;i++)
			copy[i] = s[i];
		s = copy;
	}

	public Iterator<T> iterator()
	{
		return new StackIterator();
	}

	private class StackIterator implements Iterator<T>
	{
		private int i = N;

		public boolean hasNext() {return i>0;}
		public T next() {return s[--i];}
	}

	public static void main(String[] args)
	{
		Bag<String> b = new Bag<>();
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			String item = sc.nextLine();
			if(item.equals("-"))
			{
				Iterator<String> it = b.iterator();
				while(it.hasNext())
				{
					System.out.printf("%s ",it.next());
				}
				System.out.println();
			}
			else
				b.push(item);
		}
	
	}

}