package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;

/** Represents a general unlabeled directed graph whose vertices are denoted by
 *  positive integers. Graphs may have self edges.
 *
 *  @author Harry Zhang
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int inDegree(int v) {
        int deg = 0;
        for (int[] edge : getEdges()) {
            if (edge[1] == v) {
                deg += 1;
            }
        }
        return deg;
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        ArrayList<Integer> successor = new ArrayList<>();
        for (int[] edge : getEdges()) {
            if (edge[1] == v) {
                successor.add(edge[0]);
            }
        }
        return Iteration.iteration(successor);
    }
}
