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
        
        for (int i = 0; i < E; i++) {
            do {
                System.out.print("First vertex number: ");
                U[i] = sc.nextInt();
            } while (!(U[i] > 0 && U[i] <= N));

            do {
                System.out.print("Connected vertex number: ");
                V[i] = sc.nextInt();
            } while (!(V[i] > 0 && V[i] <= N));
          
            System.out.println();
        }
        Graphs graph1 = new Graphs(N);
        for (int i = 0; i < U.length; i++) {
            graph1.addingEdge(U[i] - 1, V[i] - 1);
        }

        if (CompleteGraph.checkCompleteGraph(N, E, U, V)) {
            System.out.println("COP-WIN graph\nIt is a complete graph\nIn a complete graph, every vertex is connected to every other vertex of the graph\n");
        }

        /**if true, it is definitely ROBBER WIN graph
         * if false, we cannot conclude anything*/
        else if (CyclicGraph.checkCyclicGraph(N, E, U, V)) {
            System.out.println("The graph is robber win as it is cyclic");  
            graph1.greedyColorNodes();
        } else if (TreeGraph.isTree(N, U, V)) {
            System.out.println("Cop-win Graph.\nCop number = 1");
        } else {
            if (graph1.checkCycle() == true) {
                System.out.println("Robber winning graph");
                graph1.greedyColorNodes();
            } else
                System.out.println("Cop winning graph");
        }

    }
}
