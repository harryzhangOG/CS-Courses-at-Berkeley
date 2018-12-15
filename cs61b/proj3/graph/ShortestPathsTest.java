package graph;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class ShortestPathsTest {
    /** Graph1. */
    private static DirectedGraph _g1 = new DirectedGraph();
    /** Edge and weight for Graph1. */
    private static int[][] _edges = {
            {1, 2, 2},
            {1, 5, 6},
            {3, 1, 3},
            {2, 3, 6},
            {4, 2, 1},
            {2, 6, 6},
            {6, 4, 9},
            {6, 7, 2},
            {7, 6, 2},
            {8, 6, 5},
            {7, 5, 1},
            {5, 3, 9}
    };
    /** Construct graph1. */
    public void graph1() {
        for (int i = 0; i < 9; i += 1) {
            _g1.add();
        }
        for (int[] pair : _edges) {
            _g1.add(pair[0], pair[1]);
        }
    }
    /** Shortest Path. */
    class SP1 extends SimpleShortestPaths {

        SP1(int source) {
            super(_g1, source);
        }

        @Override
        public double getWeight(int u, int v) {
            for (int[] pair : _edges) {
                if (pair[0] == u && pair[1] == v) {
                    return pair[2];
                }
            }
            return Double.POSITIVE_INFINITY;
        }
    }

    @Test
    public void graph1Test() {
        graph1();
        int k = 0;
        SimpleShortestPaths sp1 = new SP1(1);
        int[][] paths = {
                {1},
                {1, 2},
                {1, 2, 3},
                {1, 2, 6, 4},
                {1, 5},
                {1, 2, 6},
                {1, 2, 6, 7},
                {8}};
        sp1.setPaths();
        for (int i = 0; i < 7; i += 1) {
            List path = sp1.pathTo(i + 1);
            for (int j = 0; j < path.size(); j++) {
                assertEquals(paths[i][j], path.get(j));
            }
        }
        try {
            sp1.pathTo(8);
        } catch (IllegalArgumentException e) {
            k = 1;
        }
        try {
            sp1.pathTo(9);
        } catch (IllegalArgumentException e) {
            k = 2;
        }
    }

    /** Graph2. */
    private static UndirectedGraph _g2 = new UndirectedGraph();
    /** Edge and weight for Graph2. */
    private static int[][] _edges2 = {
            {1, 4, 8},
            {1, 2, 8},
            {1, 3, 5},
            {1, 5, 8},
            {2, 3, 6},
            {2, 6, 7},
            {4, 6, 8},
            {4, 8, 3},
            {2, 7, 6},
            {7, 8, 9},
            {7, 5, 6},
            {8, 5, 4}
    };
    /** Construct graph2. */
    public void graph2() {
        for (int i = 0; i < 9; i += 1) {
            _g2.add();
        }
        for (int[] pair : _edges2) {
            _g2.add(pair[0], pair[1]);
        }
    }
    /** Shortest Path. */
    class SP2 extends SimpleShortestPaths {

        SP2(int source) {
            super(_g2, source);
        }

        @Override
        public double getWeight(int u, int v) {
            for (int[] pair : _edges2) {
                if (pair[0] == u && pair[1] == v) {
                    return pair[2];
                }
            }
            return Double.POSITIVE_INFINITY;
        }
    }

    @Test
    public void graph2Test() {
        graph2();
        SimpleShortestPaths sp2 = new SP2(1);
        int[][] paths = {
                {1},
                {1, 2},
                {1, 3},
                {1, 4},
                {1, 5},
                {1, 2, 6},
                {1, 2, 7},
                {1, 4, 8}};
        sp2.setPaths();
        for (int i = 0; i < paths.length; i += 1) {
            List path = sp2.pathTo(i + 1);
            for (int j = 0; j < path.size(); j++) {
                assertEquals(paths[i][j], path.get(j));
            }
        }
    }
    @Test
    public void graph3Test() {
        graph2();
        SimpleShortestPaths sp2 = new SP2(1);
        int[][] paths = {
                {1},
                {1, 2},
                {1, 3},
                {1, 4},
                {1, 5},
                {1, 2, 6},
                {1, 2, 7},
                {1, 4, 8}};
        sp2.setPaths();
        for (int i = 0; i < paths.length; i += 1) {
            List path = sp2.pathTo(i + 1);
            for (int j = 0; j < path.size(); j++) {
                assertEquals(paths[i][j], path.get(j));
            }
        }
    }
    @Test
    public void graph4Test() {
        graph2();
        SimpleShortestPaths sp2 = new SP2(1);
        int[][] paths = {
                {1},
                {1, 2},
                {1, 3},
                {1, 4},
                {1, 5},
                {1, 2, 6},
                {1, 2, 7},
                {1, 4, 8}};
        sp2.setPaths();
        for (int i = 0; i < paths.length; i += 1) {
            List path = sp2.pathTo(i + 1);
            for (int j = 0; j < path.size(); j++) {
                assertEquals(paths[i][j], path.get(j));
            }
        }
    }
    @Test
    public void graph6Test() {
        graph2();
        SimpleShortestPaths sp2 = new SP2(1);
        int[][] paths = {
                {1},
                {1, 2},
                {1, 3},
                {1, 4},
                {1, 5},
                {1, 2, 6},
                {1, 2, 7},
                {1, 4, 8}};
        sp2.setPaths();
        for (int i = 0; i < paths.length; i += 1) {
            List path = sp2.pathTo(i + 1);
            for (int j = 0; j < path.size(); j++) {
                assertEquals(paths[i][j], path.get(j));
            }
        }
    }
    @Test
    public void graph7Test() {
        graph2();
        SimpleShortestPaths sp2 = new SP2(1);
        int[][] paths = {
                {1},
                {1, 2},
                {1, 3},
                {1, 4},
                {1, 5},
                {1, 2, 6},
                {1, 2, 7},
                {1, 4, 8}};
        sp2.setPaths();
        for (int i = 0; i < paths.length; i += 1) {
            List path = sp2.pathTo(i + 1);
            for (int j = 0; j < path.size(); j++) {
                assertEquals(paths[i][j], path.get(j));
            }
        }
    }
    @Test
    public void graph9Test() {
        graph2();
        SimpleShortestPaths sp2 = new SP2(1);
        int[][] paths = {
                {1},
                {1, 2},
                {1, 3},
                {1, 4},
                {1, 5},
                {1, 2, 6},
                {1, 2, 7},
                {1, 4, 8}};
        sp2.setPaths();
        for (int i = 0; i < paths.length; i += 1) {
            List path = sp2.pathTo(i + 1);
            for (int j = 0; j < path.size(); j++) {
                assertEquals(paths[i][j], path.get(j));
            }
        }
    }
    @Test
    public void graph10Test() {
        graph2();
        SimpleShortestPaths sp2 = new SP2(1);
        int[][] paths = {
                {1},
                {1, 2},
                {1, 3},
                {1, 4},
                {1, 5},
                {1, 2, 6},
                {1, 2, 7},
                {1, 4, 8}};
        sp2.setPaths();
        for (int i = 0; i < paths.length; i += 1) {
            List path = sp2.pathTo(i + 1);
            for (int j = 0; j < path.size(); j++) {
                assertEquals(paths[i][j], path.get(j));
            }
        }
    }

}
