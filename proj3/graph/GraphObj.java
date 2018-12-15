package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;
/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author Harry Zhang
 */
abstract class GraphObj extends Graph {

    /** A new, empty Graph. */
    GraphObj() {
        _vertices = new ArrayList<>();
        _edges = new ArrayList<>();
    }

    @Override
    public int vertexSize() {
        return _vertices.size();
    }

    @Override
    public int maxVertex() {
        if (_vertices.isEmpty()) {
            return 0;
        }
        return _vertices.get(_vertices.size() - 1);
    }

    @Override
    public int edgeSize() {
        return _edges.size();
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        if (!_vertices.contains(v)) {
            return 0;
        } else {
            ArrayList<Integer> successors = new ArrayList<>();
            for (int vertex : _vertices) {
                if (contains(v, vertex)) {
                    successors.add(vertex);
                }
            }
            return successors.size();
        }

    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
        for (int v : _vertices) {
            if (v == u) {
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean contains(int u, int v) {
        if (!contains(u) || !contains(v)) {
            return false;
        } else {
            for (int[] edge : _edges) {
                if (!isDirected()) {
                    if (edge[0] == u && edge[1] == v) {
                        return true;
                    } else if (edge[0] == v && edge[1] == u) {
                        return true;
                    }
                } else {
                    if (edge[0] == u && edge[1] == v) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @Override
    public int add() {
        int index = 0;
        while (index < _vertices.size() && _vertices.get(index) == index + 1) {
            index += 1;
        }
        _vertices.add(index, index + 1);
        return index + 1;
    }

    @Override
    public int add(int u, int v) {
        checkMyVertex(u);
        checkMyVertex(v);
        for (int[] edge : _edges) {
            if (edge[0] == u && edge[1] == v) {
                return edgeId(u, v);
            }
        }
        int[] addEdge = {u, v};
        _edges.add(addEdge);
        return edgeId(u, v);

    }

    @Override
    public void remove(int v) {
        int i = 0;
        while (i < _vertices.size()) {
            if (_vertices.get(i) == v) {
                _vertices.remove(i);
            } else {
                i += 1;
            }
        }
        int j = 0;
        while (j < _edges.size()) {
            if (_edges.get(j)[0] == v || _edges.get(j)[1] == v) {
                _edges.remove(j);
            } else {
                j += 1;
            }
        }
    }

    @Override
    public void remove(int u, int v) {
        if (contains(u, v)) {
            if (isDirected()) {
                for (int i = 0; i < _edges.size(); i += 1) {
                    if (_edges.get(i)[0] == u && _edges.get(i)[1] == v) {
                        _edges.remove(i);
                    }
                }
            } else {
                for (int i = 0; i < _edges.size(); i += 1) {
                    if ((_edges.get(i)[0] == u && _edges.get(i)[1] == v)
                            || (_edges.get(i)[0] == v
                            && _edges.get(i)[1] == u)) {
                        _edges.remove(i);
                    }
                }
            }
        }
    }

    @Override
    public Iteration<Integer> vertices() {
        return Iteration.iteration(_vertices.iterator());
    }

    /** Helper function that returns successor K of vertex V.
     * @param v vertex.
     * @param k successor index.
     * @return successor.
     */
    private int successorHelper(int v, int k) {
        int count = k;
        for (int[] edge : _edges) {
            if (edge[0] == v) {
                if (count == 0) {
                    return edge[1];
                } else {
                    count -= 1;
                }
            } else if (!isDirected() && edge[1] == v) {
                if (count == 0) {
                    return edge[0];
                } else {
                    count -= 1;
                }
            }
        }
        return 0;
    }

    @Override
    public Iteration<Integer> successors(int v) {
        ArrayList<Integer> successor = new ArrayList<>();
        for (int[] edge : _edges) {
            if (isDirected()) {
                if (edge[0] == v) {
                    successor.add(edge[1]);
                }
            } else {
                if (edge[0] == v) {
                    successor.add(edge[1]);
                }
                if (edge[1] == v && edge[0] != v) {
                    successor.add(edge[0]);
                }
            }
        }
        return Iteration.iteration(successor);
    }


    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> edges() {
        return Iteration.iteration(_edges.iterator());
    }

    @Override
    protected void checkMyVertex(int v) {
        if (!contains(v)) {
            throw new IllegalArgumentException("Illegal vertex.");
        }
    }

    @Override
    protected int edgeId(int u, int v) {
        if (!isDirected()) {
            int a = Math.max(u, v);
            int b = Math.min(u, v);
            return ((a + b) * (a + b + 1)) / 2 + b;
        } else {
            return ((u + v) * (u + v + 1)) / 2 + v;
        }
    }
    /** Return edges. */
    protected ArrayList<int[]> getEdges() {
        return _edges;
    }
    /** Return vertices. */
    protected ArrayList<Integer> getVertices() {
        return _vertices;
    }

    /** Vertices of the graph represented by an ArrayList. */
    private ArrayList<Integer> _vertices;
    /** Edges of the graph represented by an ArrayList. */
    private ArrayList<int[]> _edges;

}
