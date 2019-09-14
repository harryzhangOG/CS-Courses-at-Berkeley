package arrays;

import org.junit.Test;
import static org.junit.Assert.*;

/** FIXME
 *  @author FIXME
 */

public class ArraysTest {
    /** FIXME
     */
    @Test
    public void catenateTest() {
        int[] a = {1, 2, 3, 4};
        int[] b = {0, 2, 3, 4,9,11};
        int[] result = {1, 2, 3, 4, 0, 2, 3, 4,9,11};
        assertArrayEquals(Arrays.catenate(a, b), result);
    }
    @Test
    public void removeTest() {
        int[] a = {1,2,3,4,5,6,7,8};
        int[] result1 = {1,2,6,7,8};
        int[] result2 = {1, 2};
        assertArrayEquals(result1, Arrays.remove(a, 2, 3));
        assertArrayEquals(result2, Arrays.remove(a, 2, 40));
    }
    @Test
    public void naturalRunTest() {
        int[] a = {1, 3, 7, 5, 4, 6, 9, 10};
        int[][] b = {{1, 3, 7}, {5}, {4, 6, 9, 10}};
        assertArrayEquals(b, Arrays.naturalRuns(a));
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(ArraysTest.class));
    }
}
