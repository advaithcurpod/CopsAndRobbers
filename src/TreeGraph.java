import java.util.Iterator;
import java.util.LinkedList;

public class TreeGraph {
	
	public static boolean isTree(int nodes,int[] u,int[] v)
	{
//		using u and v creating a adjacency list to 
//		represent the given graph
		LinkedList<Integer> adj[] = new LinkedList[nodes];
		for(int i = 0;i<nodes;i++)
		{
			adj[i] = new LinkedList<Integer>();
		}
		for(int i = 0;i<u.length;i++)
		{
			adj[u[i]-1].add(v[i]-1);
			adj[v[i]-1].add(u[i]-1);
		}
		
		boolean visited[] = new boolean[nodes];
        for (int i = 0; i < nodes; i++)
            visited[i] = false;
        
        if (isCyclicUtil(0, visited, -1,adj))
            return false;
        
        for (int i = 0; i < nodes; i++)
            if (!visited[i])
                return false;
        
		return true;
	}
	
	private static boolean isCyclicUtil(int v, boolean visited[], int parent,LinkedList<Integer>adj[])
    {
        // Mark the current node as visited
        visited[v] = true;
        Integer i;
 
        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext())
        {
            i = it.next();
 
            if (!visited[i])
            {
                if (isCyclicUtil(i, visited, v,adj))
                    return true;
            }
            else if (i != parent)
            return true;
        }
        return false;
    }
}
