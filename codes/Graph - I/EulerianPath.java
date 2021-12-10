/*
Eulerian Path is a path in graph that visits every edge exactly once. 
Eulerian Circuit is an Eulerian Path which starts and ends on the same vertex.

A graph is called Eulerian if it has an Eulerian Cycle and called Semi-Eulerian if it has an Eulerian Path. 
The problem seems similar to Hamiltonian Path which is NP complete problem for a general graph. Fortunately, we can find 
whether a given graph has a Eulerian Path or not in polynomial time. In fact, we can find it in O(V+E) time.

Eulerian Cycle
An undirected graph has Eulerian cycle if following two conditions are true.
a) All vertices with non-zero degree are connected. We don’t care about vertices with zero degree because they don’t belong 
   to Eulerian Cycle or Path (we only consider all edges).
b) All vertices have even degree.

Eulerian Path
An undirected graph has Eulerian Path if following two conditions are true.
a) Same as condition (a) for Eulerian Cycle
b) If zero or two vertices have odd degree and all other vertices have even degree. 
Note that only one vertex with odd degree is not possible in an undirected graph (sum of all degrees is always even in an undirected graph)

Note that a graph with no edges is considered Eulerian because there are no edges to traverse.
*/
public class EulerianPath
{
	private boolean isConnected()
    {
        // Mark all the vertices as not visited
        boolean visited[] = new boolean[V];
        int i;
        for (i = 0; i < V; i++)
            visited[i] = false;
 
        // Find a vertex with non-zero degree
        for (i = 0; i < V; i++)
            if (adj[i].size() != 0)
                break;
 
        // If there are no edges in the graph, return true
        if (i == V)
            return true;
 
        // Start DFS traversal from a vertex with non-zero degree
        DFSUtil(i, visited);
 
        // Check if all non-zero degree vertices are visited
        for (i = 0; i < V; i++)
           if (visited[i] == false && adj[i].size() > 0)
                return false;
 
        return true;
    }
    private void DFSUtil(int v,boolean visited[])
    {
        // Mark the current node as visited
        visited[v] = true;
 
        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext())
        {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }
    int isEulerian()
    {
        // Check if all non-zero degree vertices are connected
        if (isConnected() == false)
            return 0;
 
        // Count vertices with odd degree
        int odd = 0;
        for (int i = 0; i < V; i++)
            if (adj[i].size()%2!=0)
                odd++;
 
        // If count is more than 2, then graph is not Eulerian
        if (odd > 2)
            return 0;
 
        // If odd count is 2, then semi-eulerian.
        // If odd count is 0, then eulerian
        // Note that odd count can never be 1 for undirected graph
        return (odd==2)? 1 : 2;
    }



}