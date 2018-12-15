package graph;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Iterator;
/** Unit tests for the Graph class.
 *  @author Harry Zhang
 */
public class GraphTest {
    @Test
    public void emptyGraph() {
        DirectedGraph g = new DirectedGraph();
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
    }
    @Test
    public void undirected() {
        UndirectedGraph g = new UndirectedGraph();
        for (int i = 0; i < 5; i++) {
            g.add();
        }
        assertEquals("Wrong number of vertices", 5, g.vertexSize());
        g.add(1, 3);
        g.add(1, 5);
        g.add(3, 4);
        g.add(4, 5);
        g.add(5, 3);
        g.add(1, 3);
        assertEquals("Wrong edges", 5, g.edgeSize());
        assertEquals("Wrong degree", 0, g.degree(2));
        assertEquals("Wrong degree", 2, g.degree(1));
        assertEquals("Wrong degree", 3, g.outDegree(5));
        g.remove(2);
        assertEquals("Wrong vertices", 4, g.vertexSize());
        assertEquals("Wrong edges", 5, g.edgeSize());
        g.remove(5);
        assertEquals("Wrong vertices", 3, g.vertexSize());
        assertEquals("Wrong edges", 2, g.edgeSize());
    }
    @Test
    public void undirected2() {
        UndirectedGraph g = new UndirectedGraph();
        for (int i = 0; i < 4; i++) {
            g.add();
        }
        assertEquals("Wrong number of vertices", 4, g.vertexSize());
        g.add(1, 3);
        g.add(3, 4);
        g.add(1, 3);
        assertEquals("Wrong edges", 2, g.edgeSize());
    }

    @Test
    public void directed() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 5; i++) {
            g.add();
        }
        assertEquals("Wrong vertices", 5, g.vertexSize());
        g.add(1, 3);
        g.add(1, 5);
        g.add(3, 4);
        g.add(4, 5);
        g.add(5, 3);
        g.add(1, 3);
        assertEquals("Wrong edges", 5, g.edgeSize());
        assertEquals("Wrong degree", 0, g.inDegree(1));
        assertEquals("Wrong degree", 0, g.inDegree(2));
        assertEquals("Wrong degree", 2, g.inDegree(3));
        assertEquals("Wrong degree", 2, g.inDegree(5));
        assertEquals("Wrong degree", 2, g.outDegree(1));
        assertEquals("Wrong degree", 0, g.outDegree(2));
        assertEquals("Wrong degree", 1, g.outDegree(3));
        g.remove(2);
        assertEquals("Wrong vertices", 4, g.vertexSize());
        assertEquals("Wrong edges", 5, g.edgeSize());
        g.remove(5);
        assertEquals("Wrong vertices", 3, g.vertexSize());
        assertEquals("Wrong edges", 2, g.edgeSize());
    }
    @Test
    public void directed1() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 5; i++) {
            g.add();
        }
        assertEquals("Wrong vertices", 5, g.vertexSize());
        g.add(1, 3);
        g.add(1, 5);
        g.add(3, 4);
        g.add(4, 5);
        g.add(5, 3);
        g.add(1, 3);
        assertEquals("Wrong edges", 5, g.edgeSize());
        assertEquals("Wrong degree", 0, g.inDegree(1));
        assertEquals("Wrong degree", 0, g.inDegree(2));
        assertEquals("Wrong degree", 2, g.inDegree(3));
        assertEquals("Wrong degree", 2, g.inDegree(5));
        assertEquals("Wrong degree", 2, g.outDegree(1));
        assertEquals("Wrong degree", 0, g.outDegree(2));
        assertEquals("Wrong degree", 1, g.outDegree(3));
        g.remove(2);
        assertEquals("Wrong vertices", 4, g.vertexSize());
        assertEquals("Wrong edges", 5, g.edgeSize());
        g.remove(5);
        assertEquals("Wrong vertices", 3, g.vertexSize());
        assertEquals("Wrong edges", 2, g.edgeSize());
        g.add();
        ArrayList<Integer> check = g.getVertices();
    }
    @Test
    public void directed2() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 10; i++) {
            g.add();
        }
        assertEquals("Wrong vertices", 10, g.vertexSize());
        g.add(1, 3);
        g.add(3, 4);
        g.add(1, 3);
        assertEquals("Wrong edges", 2, g.edgeSize());
        g.remove(1);
        g.remove(10);
        g.add();
    }
    @Test
    public void directedIteratorTest() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 5; i++) {
            g.add();
        }
        g.add(1, 3);
        g.add(1, 5);
        g.add(3, 4);
        g.add(4, 5);
        g.add(5, 3);
        Iterator a = g.successors(1);
        ArrayList<Integer> a11 = new ArrayList<>();
        a11.add(3);
        a11.add(5);
        Iterator b = g.successors(2);
        ArrayList<Integer> b11 = new ArrayList<>();
        Iterator c = g.successors(3);
        ArrayList<Integer> c11 = new ArrayList<>();
        c11.add(4);
        Iterator d = g.successors(4);
        ArrayList<Integer> d11 = new ArrayList<>();
        d11.add(5);
        Iterator e = g.successors(5);
        ArrayList<Integer> e11 = new ArrayList<>();
        e11.add(3);
        assertTrue("Wrong iterator", iteratorHelper(a, a11));
        assertTrue("Wrong iterator", iteratorHelper(b, b11));
    }
    @Test
    public void directedIteratorTest2() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 5; i++) {
            g.add();
        }
        g.add(1, 3);
        g.add(1, 5);
        g.add(3, 4);
        g.add(4, 5);
        g.add(5, 3);
        Iterator a = g.successors(1);
        ArrayList<Integer> a11 = new ArrayList<>();
        a11.add(3);
        a11.add(5);
        Iterator b = g.successors(2);
        ArrayList<Integer> b11 = new ArrayList<>();
        Iterator c = g.successors(3);
        ArrayList<Integer> c11 = new ArrayList<>();
        c11.add(4);
        Iterator d = g.successors(4);
        ArrayList<Integer> d11 = new ArrayList<>();
        d11.add(5);
        Iterator e = g.successors(5);
        ArrayList<Integer> e11 = new ArrayList<>();
        e11.add(3);
        ArrayList<Integer> e22 = new ArrayList<>();
        e22.add(1);
        e22.add(4);
        assertTrue("Wrong iterator", iteratorHelper(c, c11));
        assertTrue("Wrong iterator", iteratorHelper(d, d11));
        assertTrue("Wrong iterator", iteratorHelper(e, e11));
    }
    @Test
    public void directedIteratorTest3() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 5; i++) {
            g.add();
        }
        g.add(1, 3);
        g.add(1, 5);
        g.add(3, 4);
        g.add(4, 5);
        g.add(5, 3);
        Iterator a = g.successors(1);
        ArrayList<Integer> a11 = new ArrayList<>();
        a11.add(3);
        a11.add(5);
        Iterator e = g.successors(5);
        ArrayList<Integer> e11 = new ArrayList<>();
        e11.add(3);
        Iterator a2 = g.predecessors(1);
        ArrayList<Integer> a22 = new ArrayList<>();
        Iterator b2 = g.predecessors(2);
        ArrayList<Integer> b22 = new ArrayList<>();
        Iterator c2 = g.predecessors(3);
        ArrayList<Integer> c22 = new ArrayList<>();
        c22.add(1);
        c22.add(5);
        assertTrue("Wrong iterator", iteratorHelper(e, e11));
        assertTrue("Wrong iterator", iteratorHelper(a2, a22));
        assertTrue("Wrong iterator", iteratorHelper(b2, b22));
        assertTrue("Wrong iterator", iteratorHelper(c2, c22));
    }
    @Test
    public void directedIteratorTest4() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 5; i++) {
            g.add();
        }
        g.add(1, 3);
        g.add(1, 5);
        g.add(3, 4);
        g.add(4, 5);
        g.add(5, 3);
        Iterator a = g.successors(1);
        ArrayList<Integer> a11 = new ArrayList<>();
        a11.add(3);
        a11.add(5);
        ArrayList<Integer> d11 = new ArrayList<>();
        d11.add(5);
        ArrayList<Integer> c22 = new ArrayList<>();
        c22.add(1);
        c22.add(5);
        Iterator d2 = g.predecessors(4);
        ArrayList<Integer> d22 = new ArrayList<>();
        d22.add(3);
        Iterator e2 = g.predecessors(5);
        ArrayList<Integer> e22 = new ArrayList<>();
        e22.add(1);
        e22.add(4);
        assertTrue("Wrong iterator", iteratorHelper(d2, d22));
        assertTrue("Wrong iterator", iteratorHelper(e2, e22));
    }
    @Test
    public void directedIteratorTest5() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 5; i++) {
            g.add();
        }
        g.add(1, 3);
        g.add(1, 5);
        g.add(3, 4);
        g.add(4, 5);
        g.add(5, 3);
        Iterator a = g.successors(1);
        ArrayList<Integer> a11 = new ArrayList<>();
        a11.add(3);
        a11.add(5);
        ArrayList<Integer> d11 = new ArrayList<>();
        d11.add(5);
        ArrayList<Integer> c22 = new ArrayList<>();
        c22.add(1);
        c22.add(5);
        Iterator d2 = g.predecessors(4);
        ArrayList<Integer> d22 = new ArrayList<>();
        d22.add(3);
        Iterator e2 = g.predecessors(5);
        ArrayList<Integer> e22 = new ArrayList<>();
        e22.add(1);
        e22.add(4);
        assertTrue("Wrong iterator", iteratorHelper(d2, d22));
        assertTrue("Wrong iterator", iteratorHelper(e2, e22));
    }
    @Test
    public void directedIteratorTest6() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 5; i++) {
            g.add();
        }
        g.add(1, 3);
        g.add(1, 5);
        g.add(3, 4);
        g.add(4, 5);
        g.add(5, 3);
        Iterator a = g.successors(1);
        ArrayList<Integer> a11 = new ArrayList<>();
        a11.add(3);
        a11.add(5);
        ArrayList<Integer> d11 = new ArrayList<>();
        d11.add(5);
        ArrayList<Integer> c22 = new ArrayList<>();
        c22.add(1);
        c22.add(5);
        Iterator d2 = g.predecessors(4);
        ArrayList<Integer> d22 = new ArrayList<>();
        d22.add(3);
        Iterator e2 = g.predecessors(5);
        ArrayList<Integer> e22 = new ArrayList<>();
        e22.add(1);
        e22.add(4);
        assertTrue("Wrong iterator", iteratorHelper(d2, d22));
        assertTrue("Wrong iterator", iteratorHelper(e2, e22));
    }
    @Test
    public void undirected3() {
        UndirectedGraph g = new UndirectedGraph();
        for (int i = 0; i < 4; i++) {
            g.add();
        }
        assertEquals("Wrong number of vertices", 4, g.vertexSize());
        g.add(1, 3);
        g.add(3, 4);
        g.add(1, 3);
        assertEquals("Wrong edges", 2, g.edgeSize());
    }
    @Test
    public void undirected4() {
        UndirectedGraph g = new UndirectedGraph();
        for (int i = 0; i < 4; i++) {
            g.add();
        }
        assertEquals("Wrong number of vertices", 4, g.vertexSize());
        g.add(1, 3);
        g.add(3, 4);
        g.add(1, 3);
        assertEquals("Wrong edges", 2, g.edgeSize());
    }
    @Test
    public void undirected5() {
        UndirectedGraph g = new UndirectedGraph();
        for (int i = 0; i < 4; i++) {
            g.add();
        }
        assertEquals("Wrong number of vertices", 4, g.vertexSize());
        g.add(1, 3);
        g.add(3, 4);
        g.add(1, 3);
        assertEquals("Wrong edges", 2, g.edgeSize());
    }
    @Test
    public void undirected6() {
        UndirectedGraph g = new UndirectedGraph();
        for (int i = 0; i < 4; i++) {
            g.add();
        }
        assertEquals("Wrong number of vertices", 4, g.vertexSize());
        g.add(1, 3);
        g.add(3, 4);
        g.add(1, 3);
        assertEquals("Wrong edges", 2, g.edgeSize());
    }
    @Test
    public void undirected7() {
        UndirectedGraph g = new UndirectedGraph();
        for (int i = 0; i < 4; i++) {
            g.add();
        }
        assertEquals("Wrong number of vertices", 4, g.vertexSize());
    }

    /** Return a boolean comparing between A and B. */
    public boolean iteratorHelper(Iterator A, ArrayList B) {
        ArrayList<Object> result = new ArrayList<>();
        while (A.hasNext()) {
            Object a = A.next();
            result.add(a);
            if (!B.contains(a)) {
                return false;
            }
        }
        return result.size() == B.size();
    }

}
