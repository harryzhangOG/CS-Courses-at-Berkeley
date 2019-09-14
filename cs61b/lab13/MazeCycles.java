import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author Josh Hug
 */

public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private Maze maze;
    private boolean[] inStack;
    private int cycle = -1;
    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        inStack = new boolean[m.V()];
        for (boolean x : inStack) {
            x = false;
        }
    }

    @Override
    public void solve() {
        announce();
        dfs(0);
        for (int i = 0;i < maze.V(); i++) {
            marked[i] = false;
        }
        if (cycle == -1) {
            return;
        }
        for (int j = edgeTo[cycle]; j!= cycle; j = edgeTo[j]) {
            marked[j] = true;
        }
        marked[cycle] =true;
        announce();
    }
    private void dfs(int s) {
        marked[s] = true;
        inStack[s] = true;
        distTo[s] = 0;
        marked[s] = true;
        for(int w : maze.adj(s)) {
            if (inStack[w] && edgeTo[s] != w) {
                edgeTo[w] = s;
                cycle = w;
                return;
            }
            if (!marked[w]) {
                edgeTo[w] = s;
                distTo[w] = distTo[s] + 1;
                marked[w] = true;
                dfs(w);
            }
        }
        inStack[s] = false;
    }
}