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

        for(int i = 0; i < E; i++){
            do {
                System.out.print("First vertex number: ");
                U[i] = sc.nextInt();
            }while (!(U[i] > 0 && U[i] <= N));

            do {
                System.out.print("Connected vertex number: ");
                V[i] = sc.nextInt();
            }while (!(V[i] > 0 && V[i] <= N));
            System.out.println();
        }

        /**if true, it is definitely ROBBER WIN graph
         * if false, we cannot conclude anything*/
        if (CyclicGraph.checkCyclicGraph(N, E, U, V) == true) {
            System.out.println("The graph is cop win as it is cyclic");
            return;
        }

        copNumber.minimumCops(N, E, U, V);
    }
}
