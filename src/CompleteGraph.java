import java.util.HashSet;
import java.util.Set;

public class CompleteGraph {
    public static boolean checkCompleteGraph(int N, int E, int[] U, int[] V){
        if(E != N*(N-1)/2)
            return false;

        int[] arr = new int[2*E];
        System.arraycopy(U, 0, arr, 0, E);
        System.arraycopy(V, 0, arr, E, E);

        /**store unique elements of the array in a HashSet*/
        Set<Integer> set = new HashSet<>();
        for(int i : arr)
            set.add(i);

        /**check if every number occurs exactly N-1 times*/
        for(int i : set){
            int count = 0;
            for(int j : arr){
                if(i == j)
                    count++;
            }
            if(count != N-1)
                return false;
        }

        /**every vertex must have an edge pointing to another different vertex
         * it shouldn't be a self loop*/
        for(int i = 0; i < E; i++){
            if(U[i] == V[i])
                return false;
        }
        return true;
    }
}
