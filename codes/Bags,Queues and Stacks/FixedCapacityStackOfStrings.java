import java.util.Scanner;

public class FixedCapacityStackOfStrings
{
	private String[] s;
	private int N = 0; // Always one ahead of last valid 
					   // object that is always stands at null object. 

	public FixedCapacityStackOfStrings(int capacity)
	{
		s = new String[capacity];
	}

	public boolean isEmpty()
	{
		return N==0;
	}

	public void push(String item)
	{
		s[N++] = item;
	}

	public String pop()
	{
		// return s[--N]; --> Loitering, object is accessed but it still remains in array
		String item = s[--N];
		s[N] = null;
		return item;
	}

	public static void main(String args[])
	{
		FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(10);
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