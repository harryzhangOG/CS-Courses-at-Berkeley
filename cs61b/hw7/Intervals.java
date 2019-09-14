import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

/** HW #7, Sorting ranges.
 *  @author
  */
public class Intervals {
    /** Assuming that INTERVALS contains two-element arrays of integers,
     *  <x,y> with x <= y, representing intervals of ints, this returns the
     *  total length covered by the union of the intervals. */
    public static int coveredLength(List<int[]> intervals) {
        Comparator<int[]> comparator = new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.valueOf(o1[0]).compareTo(Integer.valueOf(o2[0]));
            }
        };
        intervals.sort(comparator);
        int temp = 0;
        int start = Integer.MIN_VALUE;
        int end = Integer.MIN_VALUE;
        for (int i = 0; i < intervals.size(); i += 1) {
            if (intervals.get(i)[0] > end) {
                temp += (end - start);
                start = intervals.get(i)[0];
                end = intervals.get(i)[1];
            } else if (intervals.get(i)[0] <= end && intervals.get(i)[1] > end) {
                end = intervals.get(i)[1];
            }
        }
        temp += (end - start);
        return temp;
    }

    /** Test intervals. */
    static final int[][] INTERVALS = {
        {19, 30},  {8, 15}, {3, 10}, {6, 12}, {4, 5},
    };
    /** Covered length of INTERVALS. */
    static final int CORRECT = 23;

    /** Performs a basic functionality test on the coveredLength method. */
    @Test
    public void basicTest() {
        assertEquals(CORRECT, coveredLength(Arrays.asList(INTERVALS)));
    }

    /** Runs provided JUnit test. ARGS is ignored. */
    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(Intervals.class));
    }

}
