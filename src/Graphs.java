// A Java program that implements the greedy algorithm for the graph coloring

import java.util.LinkedList;
import java.util.*;
import java.io.*;

// The class shows an undirected graph using an adjacency list
public class Graphs {
    private int N; // No. of nodes
    private LinkedList<Integer> adjList[]; //Adjacency List
    private boolean end = false,rob = false;
    private int length = 0;
    //Constructor
    Graphs(int n) {
        N = n;
        adjList = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new LinkedList();
        }
    }

    // Method to create an edge into the graph sfrom node x to y and y to x
    void addingEdge(int x, int y) {
        adjList[x].add(y);
        adjList[y].add(x);
    }
    public boolean checkCycle() {
        boolean visited[] = new boolean[this.N];
        for(int i = 0;i<this.N;i++)
        visited[i] = false;    
        this.length = 0;

        for(int i = 0;i<this.N;i++)
        {
            if(visited[i] == false)
            {
                if(this.dfs(i,visited,-1) == true)
                {
                    if(this.length >= 4)
                    {
                        this.rob = true;
                    }
                    this.length = 0;
                }
            }
        }
        return this.rob;
    }
    public boolean dfs(int i,boolean visited[],int parent) {
        visited[i] = true;
        for(int j : adjList[i])
        {
            if(this.end) this.length = 0;
            if(visited[j] == false)
            {
                this.length+=1;
                if(this.dfs(j,visited,i));
                return true;
            }
            else if(parent != j)
            return true;
        }
        this.end = true;
        return false;
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
        int copNumber = chromaticNo - 1;
        if (copNumber == 1) {
            copNumber = 2;
        }
        System.out.println("The cop number of the graph is at-most: " + (copNumber));

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

        findChromticNo(res);
    }

}
