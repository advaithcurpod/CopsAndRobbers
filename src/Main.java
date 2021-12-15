import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Cops & Robbers\n" +
                "Given a graph, this program will determine if the graph is cop win or robber win\n");

        int N, E;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        N = sc.nextInt();
        
        System.out.print("Enter number of edges: ");
        E = sc.nextInt();
        System.out.println();

        int[] U = new int[E];
        int[] V = new int[E];
        ArrayList<ArrayList<Integer>>adj = new ArrayList<ArrayList<Integer> >();
        for(int i = 0;i<N;i++)
        {
            adj.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < E; i++){
            do {
                System.out.print("First vertex number: ");
                U[i] = sc.nextInt();
            }while (!(U[i] > 0 && U[i] <= N));

            do {
                System.out.print("Connected vertex number: ");
                V[i] = sc.nextInt();
            }while (!(V[i] > 0 && V[i] <= N));
            adj.get(U[i]-1).add(V[i]-1);
            adj.get(V[i]-1).add(U[i]-1);
            System.out.println();
        }
        // For testing remove later....
        // for(int i = 0;i<N;i++)
        // {
        //     for(int j = 0;j<adj.get(i).size();j++)
        //     {
        //         System.out.printf("%d ",adj.get(i).get(j));
        //     }
        //     System.out.println();
        // }
        if(CompleteGraph.checkCompleteGraph(N, E, U, V)){
            System.out.println("COP-WIN graph\nIt is a complete graph\nIn a complete graph, every vertex is connected to every other vertex of the graph\n");
        }

        /**if true, it is definitely ROBBER WIN graph
         * if false, we cannot conclude anything*/
        else if (CyclicGraph.checkCyclicGraph(N, E, U, V)) {
            System.out.println("The graph is robber win as it is cyclic\nCop number = 2");
        }

        else if(TreeGraph.isTree(N, U, V)) {
            System.out.println("Cop-win Graph.\nCop number = 1");
        }
        else{
            Cop_n_robber test = new Cop_n_robber();
            test.checkCycle(N,adj);
            if(test.rob == true)
            System.out.println("Robber winning graph");
            else
            System.out.println("Cop winning graph");
        }

        copNumber.minimumCops(N, E, U, V);
    }
}