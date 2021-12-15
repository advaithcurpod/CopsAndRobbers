package Sem3.DIscMath;

// A Java program that implements the greedy algorithm for the graph coloring

import java.util.LinkedList;
import java.util.*;
import java.io.*;

// The class shows an undirected graph using an adjacency list
public class Graphs {
    private int N; // No. of nodes
    private LinkedList<Integer> adjList[]; //Adjacency List

    //Constructor
    Graphs(int n) {
        N = n;
        adjList = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new LinkedList();
        }
    }

    // Method to create an edge into the graph
// from node x to y and y to x
    void addingEdge(int x, int y) {
// The Graph is not directed
        adjList[x].add(y);
        adjList[y].add(x);
    }


    // A method that finds the chromatic number of a graph
    void findChromticNo(int arr[]) {
// caculating the size of the array
        int size = arr.length;
        Set<Integer> hashSet = new HashSet<Integer>();

// iterating over every node and
// storing its color in the hashset
        for (int j = 0; j < size; j++) {
// hashset only contains unique numbers.
            hashSet.add(arr[j]);
        }

// finding the chromatic Number of the graph
        int chromaticNo = hashSet.size();


        System.out.println("The cop number of the graph is at-most: " + (chromaticNo - 1));

    }

    // Assigning colors (beginning from 0) to all the nodes and
// displaying the assignment of colors
    void greedyColorNodes() {
        int res[] = new int[N];

// Initializing all the vertices as unassigned
        Arrays.fill(res, -1);

// Assiging the first color to the first vertex
        res[0] = 0;

// A temporary array in order to keep the available colors. A False
// value of the avail[clr] will mean that the color clr has been assigned
// to one of its adjacent nodes
        boolean avail[] = new boolean[N];

// In the beginning, all of the colors are available
        Arrays.fill(avail, true);

// Assign colors to theh remaining N - 1 nodes
        for (int n = 1; n < N; n++) {
// Processing all the adjacent nodes and flag their colors as unavailable
            Iterator<Integer> itr = adjList[n].iterator();
            while (itr.hasNext()) {
                int i = itr.next();
                if (res[i] != -1)
                    avail[res[i]] = false;
            }

// Find the first color that is available
            int clr;
            for (clr = 0; clr < N; clr++) {
                if (avail[clr]) {
                    break;
                }
            }

            res[n] = clr; // Assigning the found color

// For the next iteration, resetting the values back to true
            Arrays.fill(avail, true);
        }

// printing the result
//        for (int n = 0; n < N; n++) {
//            System.out.println("Node " + n + " ---> Color - " + res[n]);
//        }
// for finding the chromatic number of the graph
        findChromticNo(res);
    }


    // main method
//    public static void main(String argvs[]) {
//// creating a graph that contains
//// 5 nodes
//        Graphs graph1 = new Graphs(4);
//
//// creating edges between nodes
////        graph1.addingEdge(1, 2);
////        graph1.addingEdge(1, 5);
////        graph1.addingEdge(2, 3);
////        graph1.addingEdge(2, 5);
////        graph1.addingEdge(3, 4);
////        graph1.addingEdge(4, 5);
//
//        graph1.addingEdge(0, 1);
//        graph1.addingEdge(0, 2);
//        graph1.addingEdge(0, 3);
//        graph1.addingEdge(1, 2);
//        graph1.addingEdge(1, 3);
//        graph1.addingEdge(2, 3);
//
//
//        System.out.println("Coloring of the graph 1 is: ");
//
//// invoking the method greedyColorNodes() to color the nodes
//        graph1.greedyColorNodes();
//
//        System.out.println();
//
//// creating a graph that contains
//// 4 nodes
//        Graphs graph2 = new Graphs(4);
//
//        System.out.println("Coloring of the graph 2 is: ");
//
//// creating edges between nodes
//        graph2.addingEdge(0, 1);
//        graph2.addingEdge(0, 2);
//        graph2.addingEdge(1, 3);
//        graph2.addingEdge(2, 3);
//
//// invoking the method greedyColorNodes() to color the nodes
//        graph2.greedyColorNodes();
//
//
//    }

}
