package lists;

import org.junit.Test;
import static org.junit.Assert.*;

/** Testing Lists.java
 *
 *  @author FIXME
 */
public class ListsTest {
    /**
     * One Test case
     */
    @Test
    public void naturalRunsTest() {
        int[] array = {1, 3, 7, 5, 4, 6, 9, 10, 10, 11};
        IntList L = IntList.list(array);
        int[] array1 = {1, 3, 7};
        IntList L1 = IntList.list(array1);
        IntList L2 = new IntList(5, null);
        int[] array3 = {4, 6, 9, 10};
        IntList L3 = IntList.list(array3);
        int[] array4 = {10, 11};
        IntList L4 = IntList.list(array4);
        IntList[] comp = {L1, L2, L3, L4};
        IntListList Lnat = IntListList.list(comp);
        assertEquals(Lists.naturalRuns(L), Lnat);
    }




    // It might initially seem daunting to try to set up
    // IntListList expected.
    //
    // There is an easy way to get the IntListList that you want in just
    // few lines of code! Make note of the IntListList.list method that
    // takes as input a 2D array.

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(ListsTest.class));
    }
}
