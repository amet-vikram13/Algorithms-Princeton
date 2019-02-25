import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.*;

public class BinarySearchTree<Key extends Comparable<Key>,Value> implements Iterable<Key>
{
	// Node Definition
	private class Node
	{
		private Key   key;
		private Value val;
		private int   count;
		private int   apparentHeight;
		private Node left   = null;
		private Node right  = null;
		private Node parent = null;

		public Node(Key key,Value val)
		{
			this.key            = key;
			this.val    		= val;
			this.count  		= 1;
			this.apparentHeight = 0; 
			
		}
	}

	
	// Root Node
	private Node root = null;
	private Key[] arr = (Key[]) new Object[1];
	private int N = 0;


	// Maximum Utility function
	private int max(int i,int j)
	{	return ( (i > j) ? i : j); }

	
	// Inserting Key,Value pair in Tree
	public void put(Key key,Value val)
	{
		int oldc = size(root);
		root = put(root,key,val);
		int newc = size(root);
		if(newc > oldc)
		{
			if(N==arr.length)
				resize(2*N);
			arr[N++] = key;
		}

	}
	private void resize(int capacity)
	{
		Key[] copy = (Key[]) new Object[capacity];
		for(int i=0;i<N;i++)
			copy[i] = arr[i];
		arr = copy;
	}
	private Node put(Node x,Key key,Value val)
	{

		if(x == null) return new Node(key,val);

		int cmp = key.compareTo(x.key);

		if  	(cmp < 0)
		{
			x.left  = put(x.left,key,val);
			x.left.parent = x;
		}
		else if (cmp > 0)
		{
			x.right = put(x.right,key,val);
			x.right.parent = x;
		}
		else if (cmp == 0) x.val = val;
		
		x.count          = 1 + size(x.left) + size(x.right);
		x.apparentHeight = 1 + max(height(x.left),height(x.right)); 
		return x;
	}

	
	// Getting Value for a particular Key
	public Value get(Key key)
	{
		Node x = root;
		while(x!=null)
		{
			int cmp = key.compareTo(x.key);
			if     (cmp < 0)  x = x.left;
			else if(cmp > 0)  x = x.right;
			else if(cmp == 0) return x.val;
		}

		return null;
	}


	// Getting Node for a particular Key
	private Node getNode(Key key)
	{
		Node x = root;
		while(x != null)
		{
			int cmp = key.compareTo(x.key);
			if 		(cmp < 0)  x = x.left;
			else if (cmp > 0)  x = x.right;
			else if (cmp == 0) return x;
		}

		return null;
	}


	//delete minimum key
	public void deleteMin()
	{	root = deleteMin(root); }
	private Node deleteMin(Node x)
	{
		if (x.left == null) return x.right;
		x.left  = deleteMin(x.left);
		x.count = 1 + size(x.left) + size(x.right);
		x.apparentHeight = 1 + max(height(x.left),height(x.right));
		if (x.left != null ) x.left.parent = x;
		return x;
	}


	// delete the given key (Hibbard Deletion)
	public void delete(Key key)
	{
		root = delete(root,key);
	}
	private Node delete(Node x,Key key)
	{
		if (x == null) return null;
		int cmp = key.compareTo(x.key);

		if 		(cmp < 0) 
		{
			x.left  = delete(x.left,key);
			if (x.left != null ) x.left.parent = x;
		}
		else if (cmp > 0) 
		{
			x.right = delete(x.right,key);
			if (x.right != null ) x.right.parent = x;
		}
		else
		{
			/* Node t is the node to delete
			Case 1 [0 children] : delete t by setting parent link to null
			Case 2 [1 children] : delete t by replacing parent link
			Case 3 [2 children] 
				- find successor x of t                : x has no left cild
				- delete the min in t's right subtree. : but dont garbage collect x
				- Put x in t's spot					   : still a bst
			*/
	
			if (x.right == null) return x.left;

			Node t = x;
			x = x.right;
			while (x.left != null)
				x = x.left;
			
			x.right = deleteMin(t.right);
			if (x.right != null ) x.right.parent = x;

			x.left = t.left;
			if (x.left != null ) x.left.parent = x;
		}
		
		x.count = 1 + size(x.left) + size(x.right);
		x.apparentHeight = 1 + max(height(x.left),height(x.right));
		return x;
	}


	// Leftmost Node
	public Key getMaximum()
	{
		Node x = root;
		while(x.right!=null)
			x = x.right;
		return x.key;
	}

	
	// Rightmost node
	public Key getMinimum()
	{
		Node x = root;
		while(x.left!=null)
			x = x.left;
		return x.key;
	}

	
	// Largest Key <= Given Key
	public Key floor(Key key)
	{
		Node x = floor(root,key);
		if(x==null) return null;
		else 		return x.key;
	}
	private Node floor(Node x,Key key)
	{
		if(x==null) return null;
		int cmp = key.compareTo(x.key);

		if(cmp == 0) return x;

		if(cmp < 0)  return floor(x.left,key);

		Node t = floor(x.right,key);
		if(t != null) return t;
		else          return x;
	}

	
	// Smallest Key >= Given Key
	public Key ceil(Key key)
	{
		Node x = ceil(root,key);
		if(x==null) return null;
		else        return x.key;
	}
	private Node ceil(Node x,Key key)
	{
		if(x==null) return null;
		int cmp = key.compareTo(x.key);

		if(cmp ==0 ) return x;

		if(cmp > 0)  return ceil(x.right,key);

		Node t = ceil(x.left,key);
		if(t != null)  return t;
		else           return x; 
	}
	


	// Height of Tree from bottom till root
	public int height()
	{	
		return height(root); 
	}
	private int height(Node x)
	{
		if(x == null) return -1;
		return x.apparentHeight;
	}


	// Size of Tree
	public int size()
	{	
		return size(root); 
	}
	private int size(Node x)
	{
		if(x == null) return 0;
		return x.count;
	}


	// Depth of a Node from top
	public int depth(Key key)
	{
		int currDepth = -1;
		return depth(key,currDepth);
	}
	private int depth(Key key,int currDepth)
	{
		Node x = root;
		while (x != null)
		{
			currDepth++;
			int cmp = key.compareTo(x.key);
			if 		(cmp <  0) x = x.left;
			else if (cmp >  0) x = x.right;
			else    		   return currDepth;
		}
		return -1;
	}
	

	// Successor of given Key
	public Key successor(Key key)
	{
		Node x = getNode(key);
		if (x == null)
			return null;

		if (x.right != null)
		{
			Node t = x.right;
			while(t.left != null)
				t = t.left;
			return t.key;
		}

		Node y = x.parent;
		while(y!=null && x==y.right)
		{
			x = y;
			y = y.parent;
		}
		return y.key;		
	}


	// Predecessor of given key
	public Key predecessor(Key key)
	{
		Node x = getNode(key);
		if (x == null)
			return null;

		if (x.left != null)
		{
			Node t = x.left;
			while(t.right != null)
				t = t.right;
			return t.key;
		}

		Node y = x.parent;
		while(y!=null && x==y.left)
		{
			x = y;
			y = y.parent;
		}
		return y.key;
	}

	
	// rank = Number of Keys < Given Key
	public int rank(Key key)
	{	
		return rank(root,key); 
	}
	private int rank(Node x,Key key)
	{
		if(x==null) return 0;
		int cmp = key.compareTo(x.key);

		if      (cmp < 0)  return rank(x.left,key);
		else if (cmp > 0)  return (1+size(x.left)+rank(x.right,key));
		else			   return size(x.left);
	}

	
	// Key with rank = given k
	public Key select(int k)
	{	
		return select(root,k).key; 
	}
	private Node select(Node x,int k)
	{
		if(x==null) return null;
		int t = size(x.left);
		if 		(t > k) return select(x.left,k);
		else if (t < k) return select(x.right,k-t-1);
		else 		    return x;
	}

	
	// Container with all Keys in tree in increasing order
	public Iterable<Key> iterator()
	{
		return new StackIterator();
	}
	private class StackIterator implements Iterator<Key>
	{
		private int i = 0;
		public boolean hasNext()  {	return i<N; }
		public Key next() {	return arr[i++]; }

	}


	// Printing Keys InOrder
	public void printInorder()
	{
		printInorder(root);
		System.out.println();
	}
	private void printInorder(Node x)
	{
		if (x == null) return;
		printInorder(x.left);
		System.out.printf("%s ",x.key);
		printInorder(x.right);
	}

	
	// main
	// S X Y Z W E R H M A C   
	public static void main(String[] args)
	{
		BinarySearchTree<String,Integer> bst = new BinarySearchTree<>();
		Scanner sc = new Scanner(System.in);
		while(true)
		{
			String s = sc.nextLine();
			if (s.equals("*"))
			{
				System.out.printf("Height : ");
				System.out.println(bst.height());
				System.out.printf("Size : ");
				System.out.println(bst.size());
				System.out.printf("Minimum : ");
				System.out.println(bst.getMinimum());
				System.out.printf("Maximum : ");
				System.out.println(bst.getMaximum());
				System.out.printf("Depth of W : ");
				System.out.println(bst.depth("W"));
				System.out.printf("floor of O : ");
				System.out.println(bst.floor("O"));
				System.out.printf("ceil of O : ");
				System.out.println(bst.ceil("O"));
				System.out.printf("Successor of W : ");
				System.out.println(bst.successor("W"));
				System.out.printf("Predecessor of W : ");
				System.out.println(bst.predecessor("W"));
				System.out.printf("rank of W : ");
				System.out.println(bst.rank("W"));
				System.out.printf("select 7 : ");
				System.out.println(bst.select(7));
				System.out.println("Deleting E and printing InOrder");
				bst.delete("E");
				bst.printInorder();
			}
			else
				bst.put(s,0);

		}
	}
}