import java.util.*;

public class copNumber {

    // Function to count the minimum
// number of color required
    static void minimumCops(int N, int E,
                            int U[], int V[]) {

        // Create array of vectors
        // to make adjacency list
        Vector<Integer>[] adj = new Vector[N];

        // Initialise cops array to 1
        // and count array to 0
        int[] count = new int[N];
        int[] cops = new int[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new Vector<Integer>();
            cops[i] = 1;
        }

        // Create adjacency list of
        // a graph
        for (int i = 0; i < E; i++) {
            adj[V[i] - 1].add(U[i] - 1);
            count[U[i] - 1]++;
        }

        // Declare queue Q
        Queue<Integer> Q = new LinkedList<>();

        // Traverse count[] and insert
        // in Q if count[i] = 0;
        for (int i = 0; i < N; i++) {
            if (count[i] == 0) {
                Q.add(i);
            }
        }

        // Traverse queue and update
        // the count of cops
        // adjacent node
        while (!Q.isEmpty()) {
            int u = Q.peek();
            Q.remove();

            // Traverse node u
            for (int x : adj[u]) {
                count[x]--;

                // If count[x] = 0
                // insert in Q
                if (count[x] == 0) {
                    Q.add(x);
                }

                // If cops of child
                // node is less than
                // parent node, update
                // the count by 1
                if (cops[x] <= cops[u]) {
                    cops[x] = 1 + cops[u];
                }
            }
        }

        // Stores the minimumColors
        // requires to color the graph.
        int minCops = -1;

        // Find the maximum of cops[]
        for (int i = 0; i < N; i++) {
            minCops = Math.max(minCops, cops[i]);
        }

        // Print the minimum number of cops required.
        System.out.print("The number of cops required to make it a COP-WIN will be less than or equal to: ");
        if(minCops > 4)
            minCops = 4;
        System.out.print(minCops - 1 + "\n");
    }

    // Driver function
    public static void main(String[] args) {
        int N = 5, E = 9;
        int U[] = {1, 1, 1, 1, 2, 2, 2, 3, 4};
        int V[] = {2, 3, 4, 5, 3, 4, 5, 5, 5};

        minimumCops(N, E, U, V);
    }
}