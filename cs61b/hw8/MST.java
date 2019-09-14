import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/** Minimal spanning tree utility.
 *  @author
 */
public class MST {

    /** Given an undirected, weighted, connected graph whose vertices are
     *  numbered 1 to V, and an array E of edges, returns an array of edges
     *  in E that form a minimal spanning tree of the input graph.
     *  Each edge in E is a three-element int array of the form (u, v, w),
     *  where 0 < u < v <= V are vertex numbers, and 0 <= w is the weight
     *  of the edge. The result is an array containing edges from E.
     *  Neither E nor the arrays in it may be modified.  There may be
     *  multiple edges between vertices.  The objects in the returned array
     *  are a subset of those in E (they do not include copies of the
     *  original edges, just the original edges themselves.) */
    public static int[][] mst(int V, int[][] E) {
        _union = new UnionFind(V);
        _edge = Arrays.copyOf(E, E.length);
        _temp = new ArrayList<>();
        Arrays.sort(_edge, EDGE_WEIGHT_COMPARATOR);
        for (int i = 0; i < _edge.length; i++) {
            int v1 = _edge[i][0];
            int v2 = _edge[i][1];
            if (!_union.samePartition(v1, v2)) {
                _temp.add(_edge[i]);
                _union.union(v1, v2);
            }

        }

        if (_temp == null || _temp.size() == 0) {
            return _edge;
        }
        _tree = new int[_temp.size()][];
        for (int i = 0; i < _temp.size(); i++) {
            _tree[i] = _temp.get(i);
        }
        return _tree;
    }

    /** An ordering of edges by weight. */
    private static final Comparator<int[]> EDGE_WEIGHT_COMPARATOR =
            new Comparator<int[]>() {
                @Override
                public int compare(int[] e0, int[] e1) {
                    return e0[2] - e1[2];
                }
            };

    private static ArrayList<int[]> _temp;
    private static int[][] _tree;
    private static UnionFind _union;
    private static int[][] _edge;



}