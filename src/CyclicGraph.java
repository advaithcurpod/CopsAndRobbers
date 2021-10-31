import java.util.HashSet;
import java.util.Set;

public class CyclicGraph {
    public static boolean checkCyclicGraph(int N, int E, int[] U, int[] V){
        /**for a cyclic graph, number of vertices = number of edges*/
        if(N != E)
            return false;

        int[] arr = new int[N+E];
        System.arraycopy(U, 0, arr, 0, N);
        System.arraycopy(V, 0, arr, N, E);

        /**store unique elements of the array in a HashSet*/
        Set<Integer> set = new HashSet<>();
        for(int i : arr)
            set.add(i);

        /**check if every number occurs exactly twice*/
        for(int i : set){
            int count = 0;
            for(int j : arr){
                if(i == j)
                    count++;
            }
            if(count != 2)
                return false;
        }

        /**every vertex must have an edge pointing to another different vertex
         * it shouldn't be a self loop*/
        for(int i = 0; i < N; i++){
            if(U[i] == V[i])
                return false;
        }
        return true;
    }
}
