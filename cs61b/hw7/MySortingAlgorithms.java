import java.util.Arrays;
import java.util.*;

/**
 * Class containing all the sorting algorithms from 61B to date.
 *
 * You may add any number instance variables and instance methods
 * to your Sorting Algorithm classes.
 *
 * You may also override the empty no-argument constructor, but please
 * only use the no-argument constructor for each of the Sorting
 * Algorithms, as that is what will be used for testing.
 *
 * Feel free to use any resources out there to write each sort,
 * including existing implementations on the web or from DSIJ.
 *
 * All implementations except Distribution Sort adopted from Algorithms,
 * a textbook by Kevin Wayne and Bob Sedgewick. Their code does not
 * obey our style conventions.
 */
public class MySortingAlgorithms {

    /**
     * Java's Sorting Algorithm. Java uses Quicksort for ints.
     */
    public static class JavaSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            Arrays.sort(array, 0, k);
        }

        @Override
        public String toString() {
            return "Built-In Sort (uses quicksort for ints)";
        }
    }

    /** Insertion sorts the provided data. */
    public static class InsertionSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            k = Math.min(array.length, k);
            for (int i = 1; i < k; i += 1) {
                int temp = array[i];
                int j = i - 1;
                while (j >= 0 && array[j] > temp) {
                    array[j + 1] = array[j];
                    j -= 1;
                }
                array[j + 1] = temp;
            }
        }

        @Override
        public String toString() {
            return "Insertion Sort";
        }
    }

    /**
     * Selection Sort for small K should be more efficient
     * than for larger K. You do not need to use a heap,
     * though if you want an extra challenge, feel free to
     * implement a heap based selection sort (i.e. heapsort).
     */
    public static class SelectionSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            k = Math.min(array.length, k);
            for (int i = 0; i < k - 1; i += 1) {
                int min_idx = i;
                for (int j = i + 1; j < k; j += 1) {
                    if (array[j] < array[min_idx]) {
                        min_idx = j;
                    }
                }
                int temp = array[min_idx];
                array[min_idx] = array[i];
                array[i] = temp;
            }

        }

        @Override
        public String toString() {
            return "Selection Sort";
        }
    }

    /** Your mergesort implementation. An iterative merge
      * method is easier to write than a recursive merge method.
      * Note: I'm only talking about the merge operation here,
      * not the entire algorithm, which is easier to do recursively.
      */
    public static class MergeSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            if (k < 2) {
                return;
            }
            int mid = k / 2;
            int[] l = new int[mid];
            int[] r = new int[k - mid];

            for (int i = 0; i < mid; i += 1) {
                l[i] = array[i];
            }
            for (int i = mid; i < k; i += 1) {
                r[i - mid] = array[i];
            }
            sort(l, mid);
            sort(r, k - mid);
            merge(array, l, r, mid, k - mid);
        }
        public void merge(int[] array, int[] l, int[] r, int left, int right) {
            int i, j, k;
            i = 0;
            j = 0;
            k = 0;
            while (i < left && j < right) {
                if (l[i] < r[j]) {
                    array[k++] = l[i++];
                } else {
                    array[k++] = r[j++];
                }
            }
            while (i < left) {
                array[k++] = l[i++];
            } while (j < right) {
                array[k++] = r[j++];
            }
        }

//        public void merge(int[] array, int start, int end) {
//            if (start < end) {
//                int mid = (end - start) / 2;
//                merge(array, start, mid);
//                merge(array, mid + 1, end);
//                merge(array, start, mid, end);
//            }
//        }
//        public void merge(int[] array, int start, int mid, int end) {
//            int len1 = mid - start + 1;
//            int len2 = end - mid;
//            int[] left = new int[len1];
//            int[] right = new int[len2];
//            for (int i = 0; i < len1; i += 1) {
//                left[i] = array[i];
//            }
//            for (int j = 0; j < len2; j += 1) {
//                right[j] = array[j + mid + 1];
//            }
//            int i, j, k;
//            i = 0;
//            j = 0;
//            k = start;
//            while (i < len1 && j < len2) {
//                if (left[i] <= right[j]) {
//                    array[k] = left[i];
//                    i += 1;
//                } else {
//                    array[k] = right[j];
//                    j += 1;
//                }
//                k += 1;
//            }
//            while (i < len1) {
//                array[k] = left[i];
//                i += 1;
//                k += 1;
//            }
//            while (j < len2) {
//                array[k] = right[j];
//                j += 1;
//                k += 1;
//            }

        // may want to add additional methods

        @Override
        public String toString() {
            return "Merge Sort";
        }
    }

    /**
     * Your Distribution Sort implementation.
     * You should create a count array that is the
     * same size as the value of the max digit in the array.
     */
    public static class DistributionSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            k = Math.min(array.length, k);
            int max = array[0];
            for (int i = 1; i < array.length; i++) {
                if (array[i] > max) {
                    max = array[i];
                }
            }
//            int numCounts[] = new int[max + 1];
//            for (int num : array) {
//                numCounts[num]++;
//            }
//            int[] sortedArray = new int[array.length];
//            int currentSortedIndex = 0;
//
//            for (int num = 0; num < numCounts.length; num++) {
//                int count = numCounts[num];
//                for (int i = 0; i < count; i++) {
//                    sortedArray[currentSortedIndex] = num;
//                    currentSortedIndex++;
//                }
//            }
//            for (int i = 0; i < sortedArray.length; i += 1) {
//                array[i] = sortedArray[i];
//            }
            int[] counts = new int[max + 1];
            for(int i = 0; i < k; i += 1) {
                counts[array[i]]++;
            }
            int index = 0;
            for(int elem = 0; elem < max + 1; elem += 1){
                int end = counts[elem] + index;
                if(end != index)
                    Arrays.fill(array, index, end, elem);
                index = end;
            }
        }


        // may want to add additional methods

        @Override
        public String toString() {
            return "Distribution Sort";
        }
    }

    /** Your Heapsort implementation.
     */
    public static class HeapSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
//            k  = Math.min(array.length, k);
//            for (int i = k / 2 - 1; i >= 0; i--) {
//                heapify(array, k, i);
//            }
//            for (int i = k - 1; i >= 0; i--) {
//                int temp = array[0];
//                array[0] = array[i];
//                array[i] = temp;
//                heapify(array, i , 0);
//            }
//
//        }
//        public void heapify(int[] array, int k, int i) {
//            int max = i;
//            int left = 2 * i + 1;
//            int right = 2 * i + 2;
//            if (left < k && array[left] > array[max]) {
//                max = left;
//            }
//            if (right < k && array[right] > array[max]) {
//                max = right;
//            }
//            if (max != i) {
//                int temp = array[i];
//                array[i] = array[temp];
//                array[max] = temp;
//                heapify(array, k, max);
//            }
            int n = Math.min(array.length, k);
            for (int i = n / 2 - 1; i >= 0; i--)
                heapify(array, n, i);
            for (int i = n - 1; i >= 0; i--) {
                int temp = array[0];
                array[0] = array[i];
                array[i] = temp;
                heapify(array, i, 0);
            }
        }
        void heapify(int arr[], int n, int i)
        {
            int max = i;
            int l = 2 * i + 1;
            int r = 2 * i + 2;
            if (l < n && arr[l] > arr[max])
                max = l;
            if (r < n && arr[r] > arr[max])
                max = r;
            if (max != i) {
                int temp = arr[i];
                arr[i] = arr[max];
                arr[max] = temp;
                heapify(arr, n, max);
            }

        }

        @Override
        public String toString() {
            return "Heap Sort";
        }
    }

    /** Your Quicksort implementation.
     */
    public static class QuickSort implements SortingAlgorithm {
        @Override
        public void sort(int[] array, int k) {
            int n = Math.min(k, array.length);
            sort(array,0, n - 1);
        }
        public void sort(int[] array, int low, int high) {
            if (low < high) {
                int pivot = partition(array, low, high);
                sort(array, low, pivot - 1);
                sort(array, pivot + 1, high);
            }
        }
        private int partition(int[] array, int low, int high) {
            int pivot = array[high];
            int i = low - 1;
            for (int j = low; j < high; j += 1) {
                if (array[j] <= pivot) {
                    i += 1;
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            int temp = array[i + 1];
            array[i + 1] = array[high];
            array[high] = temp;
            return i + 1;
        }

        @Override
        public String toString() {
            return "Quicksort";
        }
    }

    /* For radix sorts, treat the integers as strings of x-bit numbers.  For
     * example, if you take x to be 2, then the least significant digit of
     * 25 (= 11001 in binary) would be 1 (01), the next least would be 2 (10)
     * and the third least would be 1.  The rest would be 0.  You can even take
     * x to be 1 and sort one bit at a time.  It might be interesting to see
     * how the times compare for various values of x. */

    /**
     * LSD Sort implementation.
     */
    public static class LSDSort implements SortingAlgorithm {
        @Override
        public void sort(int[] a, int k) {
//            k = Math.min(k, a.length);
//            Queue<Integer>[] buckets = new Queue[10];
//            for (int i = 0; i < 10; i += 1) {
//                buckets[i] = new LinkedList<>();
//            }
//            boolean sorted = false;
//            int exp = 1;
//            while (!sorted) {
//                sorted = true;
//                for (int i : a) {
//                    int bucket = (i / exp) % 10;
//                    if (bucket > 0) {
//                        sorted = false;
//                    }
//                    buckets[bucket].add(i);
//                }
//                exp = exp * 10;
//                int ind = 0;
//                for (Queue<Integer> queue : buckets) {
//                    while (!queue.isEmpty()) {
//                        a[ind++] = queue.remove();
//                    }
//                }
//            }
            k = Math.min(k, a.length);
            int[] temp = new int[k];
            for (int i = 0; i < k; i += 1) {
                temp[i] = a[i];
            }
            lsd(temp);
            for (int i = 0; i < k; i += 1) {
                a[i] = temp[i];
            }
        }
        public static void lsd(int[] arr) {
            Queue<Integer>[] buckets = new Queue[10];
            for (int i = 0; i < 10; i++) {
                buckets[i] = new LinkedList<>();
            }
            boolean sorted = false;
            int exp = 1;
            while (!sorted) {
                sorted = true;
                for (int item : arr) {
                    int bucket = (item / exp) % 10;
                    if (bucket > 0) sorted = false;
                    buckets[bucket].add(item);
                }
                exp *= 10;
                int index = 0;
                for (Queue<Integer> bucket : buckets) {
                    while (!bucket.isEmpty()) {
                        arr[index++] = bucket.remove();
                    }
                }
            }
        }
        @Override
        public String toString() {
            return "LSD Sort";
        }
    }

    /**
     * MSD Sort implementation.
     */
    public static class MSDSort implements SortingAlgorithm {
        @Override
        public void sort(int[] a, int k) {
            k = Math.min(k, a.length);
//            int maxLength = 0;
//            int[] temp = new int[k];
//            for (int i = 0; i < k; i += 1) {
//                temp[i] = a[i];
//            }
//            for (int i = 0; i < k; i++) {
//                String number = Integer.toString(a[i]);
//                if (number.length() > maxLength) {
//                    maxLength = number.length();
//                }
//            }
//            for (int i = maxLength; i >= 1; i -= 1) {
//                msd(temp, i);
//            }
//            for (int i = 0; i < k; i += 1) {
//                a[i] = temp[i];
//            }
            int[] temp = new int[k];
            msd(a, 0, k - 1, 0, temp);

        }
        public void msd(int[] a, int start, int end, int k, int[] temp) {
            int right = 1 << 8;
            int m = right - 1;
            int[] buckets = new int[right + 1];
            int shift = 32 - 8 * k - 8;
            for (int i = start; i <= end; i++) {
                int c = (a[i] >> shift) & m;
                buckets[c + 1]++;
            }
            for (int r = 0; r < right; r++) {
                buckets[r + 1] += buckets[r];
            }
            for (int i = start; i <= end; i++) {
                int c = (a[i] >> shift) & m;
                temp[buckets[c]++] = a[i];
            }
            for (int i = start; i <= end; i++) {
                a[i] = temp[i - start];
            }
            if (k == 4) {
                return;
            }
            if (buckets[0] > 0) {
                msd(a, start, start + buckets[0] - 1, k + 1, temp);
            }
            for (int r = 0; r < right; r++)
                if (buckets[r + 1] > buckets[r])
                    msd(a, start + buckets[r], start + buckets[r + 1] - 1, k + 1, temp);

        }
//        public void msd(int[] a, int k) {
//            HashMap<Integer, ArrayDeque<Integer>> buckets = new HashMap<>();
//            for (int i = 0; i < 10; i += 1) {
//                buckets.put(i, new ArrayDeque<>());
//            }
//            for (int i : a) {
//                for (int j = 1; j < k; j += 1) {
//                    i = i / 10;
//                }
//                int mod = i % 10;
//                buckets.get(mod).add(i);
//            }
//            int ind = 0;
//            for (int i = 0; i < 10; i += 1) {
//                ArrayDeque<Integer> bucket = buckets.get(i);
//                while (!bucket.isEmpty()) {
//                    a[ind] = bucket.remove();
//                    ind += 1;
//                }
//            }
//        }

        @Override
        public String toString() {
            return "MSD Sort";
        }
    }

    /** Exchange A[I] and A[J]. */
    private static void swap(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}
