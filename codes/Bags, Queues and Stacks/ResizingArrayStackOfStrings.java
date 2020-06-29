import java.util.Scanner;

public class ResizingArrayStackOfStrings
{
	private String[] s;
	private int N; // Always one ahead of last valid 
				   // object that is always stands at null object.

	public ResizingArrayStackOfStrings()
	{
		s = new String[1];
		N = 0;
	}

	public void push(String item)
	{
		if(N==s.length)
			resize(2*s.length);
		s[N++] = item;
	}

	public String pop()
	{
		String item = s[--N];
		s[N] = null;
		if(N>0 && N==s.length/4) resize(s.length/2);
		// s.length always gives full length of array including
		// null objects.
		return item;
	}

	public void resize(int capacity)
	{
		String[] copy = new String[capacity];
		for(int i=0;i<N;i++)
			copy[i]=s[i];
		s = copy;
	}

	public static void main(String args[])
	{
		ResizingArrayStackOfStrings stack = new ResizingArrayStackOfStrings();
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			String s = sc.nextLine();
			if(s.equals("-"))
				System.out.println(stack.pop());
			else
				stack.push(s);
		}
	}


}