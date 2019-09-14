package arrays;

/* NOTE: The file Arrays/Utils.java contains some functions that may be useful
 * in testing your answers. */

/** HW #2 */

/** Array utilities.
 *  @author
 */
class Arrays {
    /* C. */
    /** Returns a new array consisting of the elements of A followed by the
     *  the elements of B. */
    static int[] catenate(int[] A, int[] B) {
        if (A == null) {
            return B;
        }
        if (B == null) {
            return A;
        }
        int[] result = new int[A.length + B.length];
        int i;
        for (i = 0; i < A.length; i += 1) {
            result[i] = A[i];
        }
        for (int j = 0; j < B.length; j += 1, i += 1) {
            result[i] = B[j];
        }
        return result;
    }

    /** Returns the array formed by removing LEN items from A,
     *  beginning with item #START. */
    static int[] remove(int[] A, int start, int len) {
        if (len <= 0) {
            return A;
        }
        int lenMax;
        if (len > A.length - start) {
            lenMax = A.length - start;
        }
        else {
            lenMax = len;
        }
        int[] result = new int[A.length - lenMax];
        int i = 0;
        for (; i < start; i += 1) {
            result[i] = A[i];
        }
        for (int j = len + start; j < A.length; j += 1, i +=1) {
            result[i] = A[j];
        }
        return result;

    }

    /* E. */
    /** Returns the array of arrays formed by breaking up A into
     *  maximal ascending lists, without reordering.
     *  For example, if A is {1, 3, 7, 5, 4, 6, 9, 10}, then
     *  returns the three-element array
     *  {{1, 3, 7}, {5}, {4, 6, 9, 10}}. */
    static int[][] naturalRuns(int[] A) {
        if (A.length == 0) {
            return new int[0][];
        }
        int listNum = 1;
        for (int i = 1; i < A.length; i += 1) {
            if (A[i-1] >= A[i]) {
                listNum += 1;
            }
        }
        int[][] result = new int[listNum][];
        int index = 0;
        int temp = 0;
        for (int j = 1; j < A.length; j += 1) {
            if (A[j-1] >= A[j]) {
                result[index] = Utils.subarray(A, temp, j-temp);
                temp = j;
                index += 1;
            }
        }
        if (index != listNum) {
            result[index] = Utils.subarray(A, temp, A.length - temp);
        }
        return result;
    }
}
