import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Kevin Huynh
 * @version 1.0
 * @userid khuynh44 (i.e. gburdell3)
 * @GTID 903550619 (i.e. 900000000)
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class Sorting {

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array or comparator"
                    + " cannot be null");
        }
        for (int n = 1; n < arr.length; n++) {
            int i = n;
            while (i != 0 && comparator.compare(arr[i], arr[i - 1]) < 0) {
                T temp = arr[i];
                arr[i] = arr[i - 1];
                arr[i - 1] = temp;
                i--;
            }
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array or comparator"
                    + " cannot be null");
        }
        rMergeSort(arr, comparator);
    }

    /**
     * Private helper method for merge sort. Uses recursion to split
     * array into multiples arrays.
     * @param arr The array being split or recursed in.
     * @param comparator The comparator object to compare elements in the array.
     * @param <T> The type of data the array contains.
     * @return The newly sorted array.
     */
    private static <T> T[] rMergeSort(T[] arr, Comparator<T> comparator) {
        if (arr.length == 1) {
            return arr;
        }
        int length = arr.length;
        int midIndex = length / 2;
        T[] left = (T[]) new Object[midIndex];
        T[] right = (T[]) new Object[arr.length - midIndex];
        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }
        for (int i = 0; i < right.length; i++) {
            right[i] = arr[midIndex + i];
        }
        left = rMergeSort(left, comparator);
        right = rMergeSort(right, comparator);
        int i = 0;
        int j = 0;
        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) < 0) {
                arr[i + j] = left[i];
                i++;
            } else {
                arr[i + j] = right[j];
                j++;
            }
        }
        while (i < left.length) {
            arr[i + j] = left[i];
            i++;
        }
        while (j < right.length) {
            arr[i + j] = right[j];
            j++;
        }
        return arr;
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            int temp = 1;
            int length = 0;
            while (temp <= Math.abs(arr[i])) {
                length++;
                temp *= 10;
            }
            if (length > k) {
                k = length;
            }
        }
        ArrayList<Integer>[] buckets = new ArrayList[19];
        int mod = 10;
        int divisor = 1;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < arr.length; j++) {
                int digit = (arr[j] % mod) / divisor;
                if (buckets[digit + 9] == null) {
                    buckets[digit + 9] = new ArrayList<>();
                }
                buckets[digit + 9].add(arr[j]);
            }
            int index = 0;
            for (int j = 0; j < buckets.length; j++) {
                while (buckets[j] != null && !buckets[j].isEmpty()) {
                    arr[index] = buckets[j].remove(0);
                    index++;
                }
            }
            mod *= 10;
            divisor *= 10;
        }
    }

    /**
     * Implement kth select.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param k          the index to retrieve data from + 1 (due to
     *                   0-indexing) if the array was sorted; the 'k' in "kth
     *                   select"; e.g. if k == 1, return the smallest element
     *                   in the array
     * @param arr        the array that should be modified after the method
     *                   is finished executing as needed
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @return the kth smallest element
     * @throws java.lang.IllegalArgumentException if the array or comparator
     *                                            or rand is null or k is not
     *                                            in the range of 1 to arr
     *                                            .length
     */
    public static <T> T kthSelect(int k, T[] arr, Comparator<T> comparator,
                                  Random rand) {
        if (arr == null || comparator == null
                || rand == null || k < 1 || k > arr.length) {
            throw new IllegalArgumentException("Array, comparator,"
                    + " or rand cannot be null or k is not in range of array");
        }
        return quicksort(arr, 0, arr.length - 1, rand, comparator, k);
    }

    /**
     * Private helper method for kthSelect method. Uses recursion to quick sort
     * array while finding kth element in array.
     * @param arr The array being recursed in to find kth smallest element.
     * @param start The startng index of the array.
     * @param end The ending index of the array.
     * @param rand The random number generator object.
     * @param comparator Comparator object to compare elements in the array.
     * @param k The kth smallest element that is besing searched for.
     * @param <T> The type of data the array holds.
     * @return The newly sorted array.
     */
    private static <T> T quicksort(T[] arr, int start, int end, Random rand,
                                   Comparator<T> comparator, int k) {
        if (end - start < 1) {
            return arr[end - start];
        }
        int pivotIdx = start + rand.nextInt(end - start + 1);
        T pivotVal = arr[pivotIdx];
        T temp = arr[start];
        arr[start] = arr[pivotIdx];
        arr[pivotIdx] = temp;
        int i = start + 1;
        int j = end;
        while (i <= j) {
            while (i <= j && comparator.compare(arr[i], pivotVal) <= 0) {
                i++;
            }
            while (i <= j && comparator.compare(arr[j], pivotVal) >= 0) {
                j--;
            }
            if (i <= j) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        temp = arr[start];
        arr[start] = arr[j];
        arr[j] = temp;
        if (j == k - 1) {
            return arr[j];
        }
        if (j > k - 1) {
            return quicksort(arr, start, j - 1, rand, comparator, k);
        } else {
            return quicksort(arr, j + 1, end, rand, comparator, k);
        }
    }
}
