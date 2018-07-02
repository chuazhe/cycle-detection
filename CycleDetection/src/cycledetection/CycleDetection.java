package cycledetection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class CycleDetection {

    private int Vertices; // Number of vertices for the graph
    static LinkedList<Integer> adj_list[]; // Adjacency List Representation
    Scanner scanner;

    //Constructor
    CycleDetection(int nodes) {
        Vertices = nodes;
        adj_list = new LinkedList[nodes];
        for (int i = 0; i < nodes; ++i) {
            adj_list[i] = new LinkedList();

        }
    }

    //Function to add undirected edges into the graph
    void addUndirectedEdge(int a, int b) {
        adj_list[a].add(b);
        adj_list[b].add(a);
    }

    //Function to add directed edge into the graph
    void addDirectedEdge(int a, int b) {
        adj_list[a].add(b);
    }

    // Recursive function used by checkCycleFunction
    void checkCycleFunctionRecursiveFunction(int source_vertex, int current_vertex, int parent_vertex, boolean visited[], boolean isCycle[]) {

        // Mark the current vertex as visited
        visited[current_vertex] = true;

        // Declare Iterator as collection to cycle through the element of the graph
        Iterator<Integer> graphIterator = adj_list[current_vertex].listIterator();

        //Start the loop
        while (graphIterator.hasNext()) {
            //Search for next vertex 
            int next_vertex = graphIterator.next();
            //If source vertex is the same as the next vertex and not belong to the parent vertex, it indicates there is a cycle
            if (source_vertex == next_vertex && source_vertex != parent_vertex) {
                isCycle[0] = true;
                return;
            } //If the next vertex is not visited and cycle is not detected yet, go to the next vertex with recursive function
            else if (!visited[next_vertex] && !isCycle[0]) {
                //Recursive function to go to next vertex
                checkCycleFunctionRecursiveFunction(source_vertex, next_vertex, current_vertex, visited, isCycle);
            }
        }
    }

    // Depth First Search (DFS) traversal by using recursive function checkCycleFunctionRecursiveFunction()
    boolean checkCycleFunction(int v) {

        // First, mark all the vertices as not visited which is false(default boolean case in Java)
        boolean visited[] = new boolean[Vertices];

        /*Declare a boolean array case because the value can be changed within the class because of pass by reference
        and served as cycle indication for the vertex*/
        boolean isCycle[] = new boolean[1];

        //Start the recursive function
        checkCycleFunctionRecursiveFunction(v, v, -1, visited, isCycle);

        //Return the specified vertex result
        return isCycle[0];
    }

    public static void main(String args[]) {
        int no_of_vertices = 3;
        CycleDetection g = new CycleDetection(no_of_vertices);

        //Add directed edges
        g.addDirectedEdge(0, 1);
        g.addDirectedEdge(1, 2);
        g.addDirectedEdge(2, 0);

        //Add Undirected Edges
        /*
        g.addUndirectedEdge(0, 1);
        g.addUndirectedEdge(1, 2);
        g.addUndirectedEdge(2, 0);
        */
 
        //To store the vertices
        LinkedList<Integer> vertex = new LinkedList<Integer>();
        LinkedList<Integer> cycle_vertex = new LinkedList<Integer>();

        for (int i = 0; i < no_of_vertices; i++) {
            vertex.add(i);
            if (g.checkCycleFunction(i)) {
                cycle_vertex.add(i);
            }
        }

        System.out.println("The Graph Contain: " + vertex);
        if (cycle_vertex.isEmpty()) {
            System.out.println("The Graph has no Cycle!");
        } else {
            System.out.println("The Graph has Cycle!");
            System.out.println("Vertices involved in the Cycle(s): " + cycle_vertex);
        }
    }

}
