package graph;

/* See restrictions in Graph.java. */

import java.util.Comparator;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.List;

/** The shortest paths through an edge-weighted graph.
 *  By overrriding methods getWeight, setWeight, getPredecessor, and
 *  setPredecessor, the client can determine how to represent the weighting
 *  and the search results.  By overriding estimatedDistance, clients
 *  can search for paths to specific destinations using A* search.
 *  @author Harry Zhang
 */
public abstract class ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public ShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public ShortestPaths(Graph G, int source, int dest) {
        _G = G;
        _source = source;
        _dest = dest;
        _comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                double x = estimatedDistance(o1) + getWeight(o1);
                double y = estimatedDistance(o2) + getWeight(o2);
                if (x > y) {
                    return 1;
                } else if (x < y) {
                    return -1;
                } else {
                    return o1 - o2;
                }
            }
        };
        _fringe = new TreeSet<>(_comparator);

    }

    /** Initialize the shortest paths.  Must be called before using
     *  getWeight, getPredecessor, and pathTo. */
    public void setPaths() {
        _fringe.add(_source);
        for (int i = 0; i < _G.maxVertex() + 1; i += 1) {
            setWeight(i, Double.POSITIVE_INFINITY);
        }
        setWeight(_source, 0);
        while (!_fringe.isEmpty()) {
            int v = _fringe.pollFirst();
            if (v == _dest) {
                break;
            }
            for (int next : _G.successors(v)) {
                double v1 = getWeight(v) + getWeight(v, next);
                double v2 = getWeight(next);
                if (v1 < v2) {
                    _fringe.remove(next);
                    setWeight(next, v1);
                    setPredecessor(next, v);
                    _fringe.add(next);
                }
            }
        }

    }
    /** Helper that returns the kth successor of v.
     * @param v is the vertex
     * @param k is index k
     * @return kth successor
     */
    public int successor(int v, int k) {
        if (_G.contains(v)) {
            ArrayList<Integer> successors = new ArrayList<>();
            for (int node : _G.vertices()) {
                if (_G.contains(v, node)) {
                    successors.add(node);
                }
            }
            if (k >= successors.size() || successors.size() == 0) {
                return 0;
            } else {
                return successors.get(k);
            }
        } else {
            return 0;
        }
    }

    /** Returns the starting vertex. */
    public int getSource() {
        return _source;
    }

    /** Returns the target vertex, or 0 if there is none. */
    public int getDest() {
        return _dest;
    }

    /** Returns the current weight of vertex V in the graph.  If V is
     *  not in the graph, returns positive infinity. */
    public abstract double getWeight(int v);

    /** Set getWeight(V) to W. Assumes V is in the graph. */
    protected abstract void setWeight(int v, double w);

    /** Returns the current predecessor vertex of vertex V in the graph, or 0 if
     *  V is not in the graph or has no predecessor. */
    public abstract int getPredecessor(int v);

    /** Set getPredecessor(V) to U. */
    protected abstract void setPredecessor(int v, int u);

    /** Returns an estimated heuristic weight of the shortest path from vertex
     *  V to the destination vertex (if any).  This is assumed to be less
     *  than the actual weight, and is 0 by default. */
    protected double estimatedDistance(int v) {
        return 0.0;
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    protected abstract double getWeight(int u, int v);

    /** Returns a list of vertices starting at _source and ending
     *  at V that represents a shortest path to V.  Invalid if there is a
     *  destination vertex other than V. */
    public List<Integer> pathTo(int v) {
        _G.checkMyVertex(v);
        ArrayList<Integer> path = new ArrayList<>();
        path.add(v);
        if (v != _source && getPredecessor(v) == 0) {
            throw new IllegalArgumentException("Error: Invalid path.");
        }
        while (getPredecessor(v) != 0 && v != _source) {
            path.add(0, getPredecessor(v));
            v = getPredecessor(v);
        }
        return path;
    }

    /** Returns a list of vertices starting at the source and ending at the
     *  destination vertex. Invalid if the destination is not specified. */
    public List<Integer> pathTo() {
        return pathTo(getDest());
    }

    /** The graph being searched. */
    protected final Graph _G;
    /** The starting vertex. */
    private final int _source;
    /** The target vertex. */
    private final int _dest;
    /** Comparator to compare vertex. */
    private Comparator<Integer> _comparator;
    /** Fringe for the algorithm. */
    private TreeSet<Integer> _fringe;

}
