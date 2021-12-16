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
            System.out.println("COP-WIN graph\nCop-number = 1");
        }

        /**if true, it is definitely ROBBER WIN graph
         * if false, we cannot conclude anything*/
        else if (CyclicGraph.checkCyclicGraph(N, E, U, V)) {
            System.out.println("ROBBER-WIN graph");
            graph1.greedyColorNodes();
        } else if (TreeGraph.isTree(N, U, V)) {
            System.out.println("COP-WIN graph\nCop-number = 1");
        } else {
            if (graph1.checkCycle() == true) {
                System.out.println("ROBBER-WIN graph");
                graph1.greedyColorNodes();
            } else
                System.out.println("COP-WIN graph\nCop-number = 1");
        }

    }
}
