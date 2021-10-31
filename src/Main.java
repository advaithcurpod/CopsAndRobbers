import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
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

        System.out.print("U = [");
        for(int i : U){
            System.out.print(i + " ");
        }
        System.out.print("]");

        System.out.println();

        System.out.print("V = [");
        for(int i : V){
            System.out.print(i + " ");
        }
        System.out.print("]");
        System.out.println();
    }
}
