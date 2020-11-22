import edu.princeton.cs.algs4.Insertion;

import java.util.Arrays;

public class MergeSort implements Sort{

    @Override
    public int[] sort(int[] unsorted) {
        if (unsorted.length <= 15) {
            return new InsertionSort().sort(unsorted);
        }
        int[] left = sort(Arrays.copyOfRange(unsorted, 0, (unsorted.length + 1) / 2));
        int[] right = sort(Arrays.copyOfRange(unsorted, (unsorted.length + 1) / 2, unsorted.length));
        return merge(left, right);
    }

    //runs in O(N) time in respect to a and b
    private int[] merge(int[] a, int[] b) {
        int[] sorted = new int[a.length + b.length];
        int indexA = 0, indexB = 0;
        for (int i = 0; i < sorted.length; i++) {
            if (indexA < a.length && (indexB >= b.length || a[indexA] <= b[indexB])) {
                sorted[i] = a[indexA];
                indexA++;
            } else if (indexA >= a.length || indexB < b.length && a[indexA] > b[indexB]) {
                sorted[i] = b[indexB];
                indexB++;
            }
        }
        return sorted;
    }
}
