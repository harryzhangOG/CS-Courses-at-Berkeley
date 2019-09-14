package image;

import org.junit.Test;
import static org.junit.Assert.*;

/** FIXME
 *  @author FIXME
 */

public class MatrixUtilsTest {
    @Test
    public void accumulateVerticalTest() {
        double[][] input = {
                {1000000,   1000000,  1000000,   1000000},
                {1000000 ,    75990  ,   30003 ,  1000000},
                {1000000  ,   30002  ,  103046,   1000000},
                {1000000   ,  29515  ,   38273,   1000000},
                {1000000 ,    73403   ,  35399 ,  1000000},
                {1000000 ,  1000000  , 1000000 , 1000000}};
        double[][] outputV = {
                {1000000,   1000000,  1000000,   1000000},
                {2000000 ,  1075990,   1030003,   2000000},
                {2075990 ,  1060005  , 1133049, 2030003},
                {2060005, 1089520, 1098278, 2133049},
                {2089520,1162923,  1124919 , 2098278},
                {2162923,2124919,2124919,2124919}};
        double[][] inputH = MatrixUtils.transpose(input);
        double[][] outputH = MatrixUtils.transpose(outputV);
        double[][] expectedV = MatrixUtils.accumulate(input, MatrixUtils.Orientation.VERTICAL);
        double[][] expectedH = MatrixUtils.accumulate(inputH, MatrixUtils.Orientation.HORIZONTAL);
        assertArrayEquals(expectedV, outputV);
        assertArrayEquals(expectedH, outputH);
    }

    @Test
    public void transposeTest() {
        double[][] a = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
        };
        double[][] b = {
                {1, 5, 9},
                {2, 6, 10},
                {3, 7, 11},
                {4, 8, 12},
        };
        assertArrayEquals(b,MatrixUtils.transpose(a));
    }
    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(MatrixUtilsTest.class));
    }
}
