public class RedBlackTress<Key extends Comparable<Key>,Value>
{
	
	// RED and BLACK definition
	private static final boolean RED   = true;
	private static final boolean BLACK = false;


	// Node Definition
	private class Node
	{
		private Key key;
		private Value val;
		private Node left  = null;
		private Node right = null;
		private boolean color; 		// color of parent link  

		public Node(Key key,Value val,boolean color)
		{
			this.key   = key;
			this.val   = val;
			this.color = color;
		}
	}

	
	// Root Node
	private Node root;


	// Checking link of color with parent
	private boolean isRed(Node x) 
	{
		if (x == null) return false; // null links are black
		return x.color==RED;
	}


	// left rotation
	private Node rotateLeft(Node h)
	{
		// assert isRed(h.right);
		Node x  = h.right;
		h.right = x.left;
		x.left  = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}


	// right rotation
	private Node rotateRight(Node h)
	{
		// assert isRed(h.left);
		Node x  = h.left;
		h.left  = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		return x; 
	}


	// flip colors to split temporary 4-node
	private void flipColor(Node h)
	{
		// assert !isRed(h);
		// assert isRed(h.left);
		// assert isRed(h.right);
		h.color = RED;
		h.left.color  = BLACK;
		h.right.color = BLACK;
	}


	// Inserting Key Value pair in tree
	public void put(Key key,Value val)
	{
		root = put(root,key,val);
	}
	private Node put(Node h,Key key,Value val)
	{
		if (h == null) return new Node(key,val,RED);
		int cmp = key.compareTo(h.key);
		if 		(cmp <  0) h.left  = put(h.left,key,val);
		else if (cmp >  0) h.right = put(h.right,key,val);
		else    (cmp == 0) h.val   = val;

		if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
		if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
		if (isRed(h.left)  &&  isRed(h.right))     flipColor(h);

		return h;
	}


	// Deleting Min key
	private Node deleteMin(Node x)
	{
		if (x.left == null) return x.right;
		x.left = deleteMin(x.left);

		if (isRed(x.right) && !isRed(x.left))      x = rotateLeft(x);
		if (isRed(x.left)  &&  isRed(x.left.left)) x = rotateRight(x);
		if (isRed(x.right) &&  isRed(x.left))      flipColor(x);

		return x;
	}


	// Deleting a key value pair - Hibbard Deletion
	public void delete(Key key)
	{
		root = delete(root,key);
	}
	private Node delete(Node x,Key key)
	{
		if (x == null) return null;
		int cmp = key.compareTo(x.key);

		if 		(cmp <  0) x.left  = delete(x.left,key);
		else if (cmp >  0) x.right = delete(x.right,key)
		else
		{
			if (x.right == null) return x.left;

			Node t = x;
			x = x.right;
			while(x.left != null)
				x = x.left;
			x.right = deleteMin(t.right);
			x.left  = t.left;
		}

		if (isRed(x.right) && !isRed(x.left))      x = rotateLeft(x);
		if (isRed(x.left)  &&  isRed(x.left.left)) x = rotateRight(x);
		if (isRed(x.right) &&  isRed(x.left))      flipColor(x);

		return x;

	}


	// Getting Value for a particular Key
	public Value get(Key key)
	{
		Node x = root;
		while(x != null)
		{
			int cmp = key.compareTo(x.key)

			if 		(cmp <  0) x = x.left;
			else if (cmp >  0) x = x.right;
			else    (cmp == 0) return x.val;
		}
		return null;
	}
}