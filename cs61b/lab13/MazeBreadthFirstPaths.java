import java.util.*;

/**
 *  @author Josh Hug
 */

public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;

    }
    private void bfs(int s) {
        Queue<Integer> visited = new ArrayDeque<>();
        distTo[s] = 0;
        marked[s] = true;
        announce();
        visited.add(s);
        while (!visited.isEmpty()) {
            int v = visited.remove();
            if (v == t) {
                return;
            }
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    announce();
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    visited.add(w);
                }
            }
        }

    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        bfs(s);
    }


    @Override
    public void solve() {
        bfs();
    }
}

