import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Cops & Robbers\n" +
                "Given a graph, this program will determine if the graph is cop win or robber win\n");

        int V, E;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        E = sc.nextInt();
        System.out.println();

        System.out.println("\nEdges:");
        int[][] edgeList = new int[E][2];
        for(int i=0; i<E; i++) {
            System.out.print("Edge"+i+": ");
            edgeList[i][0] = sc.nextInt();
            edgeList[i][1] = sc.nextInt();
        }

        Map<Integer,Set<Integer>> G = getAdjListUG(edgeList);
        if(copOrRobberWin(G,V)) {
            System.out.println("Cop win graph");
        }
        else {
            System.out.println("Robber win graph");
        }
    }

    // returns true for cop win, false for robber-win
    public static boolean copOrRobberWin(Map<Integer, Set<Integer>> graph, int V) {
        Set<Integer> pitfalls = new HashSet<>();
        while(true) {
            Queue<Integer> q = new LinkedList<>();
            
            // picking a random vertex to start bfs from
            // as 0 might not exist in the graph if it got removed in the previous iteration
            for(int key : graph.keySet()) {
                q.add(key);
                break;
            }

            String[] color = new String[V];
            for(int i=0; i<color.length; i++) {
                color[i] = "white";
            }

            boolean flag = false;
            while(!q.isEmpty()) {
                int u = q.remove();
                color[u] = "grey";

                for(int v : graph.get(u)) {
                    if(color[v].equals("white")) {
                        q.add(v);
                        color[v] = "grey";
                    }

                    Set<Integer> diff = setDifference(graph.get(u),graph.get(v));
                    Set<Integer> check = new HashSet<>();
                    check.add(v);
                    if(check.equals(diff) && !pitfalls.contains(u)) {
                        // u is pitfall
                        pitfalls.add(u);

                        // remove u and all its connected edges from graph
                        Set<Integer> removedValues = graph.remove(u);
                        for(int x : removedValues) {
                            Set<Integer> temp = graph.get(x);
                            temp.remove(u);
                        }
                        
                        flag = true;
                        break;
                    }
                }

                color[u] = "black";
            }

            if(!flag) {
                // no more pitfalls
                break;
            }
        }
        System.out.println();
        System.out.print("Pitfalls in graph: ");
        System.out.println(pitfalls);
        return (pitfalls.size() >= V-1);
    }

    /**
     * HELPER FUNCTIONS
     */

    // converts edgelist to adjacency list for UN-directed graph
    public static Map<Integer, Set<Integer>> getAdjListUG(int[][] edgeList) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for(int i=0; i<edgeList.length; i++) {
            int key = edgeList[i][0];
            int value = edgeList[i][1];
            Set<Integer> valueSet;
            if(!graph.containsKey(key)) {
                valueSet = new HashSet<>();
                
            } else {
                valueSet = graph.get(key);
            }
            valueSet.add(value);
            graph.put(key, valueSet);

            key = edgeList[i][1];
            value = edgeList[i][0];
            
            if(!graph.containsKey(key)) {
                valueSet = new HashSet<>();
            } else {
                valueSet = graph.get(key);
            }
            valueSet.add(value);
            graph.put(key, valueSet);     
        }   
        return graph;
    }

    // returns set difference
    private static Set<Integer> setDifference(Set<Integer> s1, Set<Integer> s2) {
        Set<Integer> copyOfS1 = new HashSet<>();
        for(int x : s1) {
            copyOfS1.add(x);
        }
        copyOfS1.removeAll(s2);
        return copyOfS1;
    }

    // converts edgelist to adjacency list for DIRECTED graph
    public static void getAdjListDG(int[][] edgeList) {
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();

        for(int i=0; i<edgeList.length; i++) {
            int key = edgeList[i][0];
            int value = edgeList[i][1];

            if(!graph.containsKey(key)) {
                ArrayList<Integer> arr = new ArrayList<>(value);
                graph.put(key, arr);
            } else {
                ArrayList<Integer> arr = graph.get(key);
                arr.add(value);
                graph.put(key, arr);
            }
        }
    }
}