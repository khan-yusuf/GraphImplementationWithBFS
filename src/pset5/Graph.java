package pset5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class Graph {
    private int numNodes; // number of nodes in the graph
    private boolean[][] edges;
    // edges[i][j] is true if and only if there is an edge from node i to node j

    // class invariant: fields "edges" is non-null;
    //                  "edges" is a square matrix;
    //                  numNodes is number of rows in "edges"

    public Graph(int size) {
        numNodes = size;
        edges = new boolean[size][size];
    }

    public String toString() {
        return "numNodes: " + numNodes + "\n" + "edges: " + Arrays.deepToString(edges);
    }

    public boolean equals(Object o) {
        if (o.getClass() != Graph.class) return false;
        return toString().equals(o.toString());
    }

    public void addEdge(int from, int to) {
        // precondition: inputs are valid for addEdge per Piazza post @141
        // postcondition: adds a directed edge "from" -> "to" to this graph

        if(from < 0 || to < 0) {
            throw new IllegalArgumentException(); // reliability check, not necessary per precondition
        }
        if(from >= numNodes || to >= numNodes) {
            expandEdges(Math.max(from, to) + 1);
        }
        edges[from][to] = true;

    }

    // helper method for addEdges that expands edges 2-d array
    public void expandEdges(int size) {
        boolean[][] edgeMatrix = new boolean[size][size];
        for(int i = 0; i < numNodes; i++) {
            for(int j=0; j < numNodes; j++){
                edgeMatrix[i][j] = edges[i][j];
            }
        }
        numNodes = size;
        edges = edgeMatrix;
    }

    public boolean reachable(Set<Integer> sources, Set<Integer> targets) {
        if (sources.isEmpty() || sources == null || targets.isEmpty() || targets == null) throw new IllegalArgumentException();

        // postcondition: returns true if (1) "sources" does not contain an illegal node
        //                                (2) "targets" does not contain an illegal node, and
        //                                (3) for each node "m" in set "targets", there is some
        //                                node "n" in set "sources" such that there is a directed
        //                                path that starts at "n" and ends at "m" in "this"; and
        //                                false otherwise

        // 1.
        for(Integer s : sources) {
            if(s < 0 || s >= numNodes) return false;
        }

        //2.
        for(Integer t : targets) {
            if(t < 0 || t >= numNodes) return false;
        }

        //3.
        for(Integer s: sources) {
            boolean targetFound = false;
            for(Integer t : targets) {
                if(reachHelper(s, t) || s.equals(t)) {
                    targetFound = true;
                    break;
                }
            }
            if(!targetFound) return false; // target not found for particular s value
        }

        return true; // all targets reached
    }

    // helper to see if 'from' node is reachable to 'to' node
    // Uses BFS logic
    public boolean reachHelper(int from, int to) {
        if(edges[from][to]) return true;

        ArrayList<Integer> nonVisited = new ArrayList<>();
        nonVisited.add(from);

        ArrayList<Integer> visited = new ArrayList<>();

        int curr = 0;
        while(nonVisited.size() > 0) {
            curr = nonVisited.remove(0);
            visited.add(curr);

            for(int i = 0; i < numNodes; i++) {
                if(!visited.contains(i) && edges[curr][i]) {
                    if(i != to){
                        visited.add(i);
                        nonVisited.add(i);
                    }
                    else return true;
                }
            }
        }

        return false;
    }
}