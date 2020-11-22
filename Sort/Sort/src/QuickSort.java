import java.util.Arrays;

public class QuickSort implements Sort {

    public int[] OutOfPlacesort(int[] unsorted) {
        if (unsorted.length <= 1) {
            return unsorted;
        }
        int pivot = partition(unsorted);
        int[] left = OutOfPlacesort(Arrays.copyOfRange(unsorted, 0, pivot));
        int[] right = OutOfPlacesort(Arrays.copyOfRange(unsorted, pivot + 1, unsorted.length));
        return concatenate(left, right, unsorted[pivot]);
    }

    private int[] concatenate(int[] a, int[] b, int y) {
        int[] x = new int[a.length + b.length + 1];
        System.arraycopy(a, 0, x, 0, a.length);
        x[a.length] = y;
        System.arraycopy(b, 0, x, a.length + 1, b.length);
        return x;
    }

    private int partition(int[] unsorted) {
        int partition = unsorted[0], lessThan = 1, greaterThan = unsorted.length - 1;
        while (lessThan <= greaterThan) {
            if (unsorted[lessThan] >= partition && unsorted[greaterThan] <= partition) {
                int temp = unsorted[lessThan];
                unsorted[lessThan] = unsorted[greaterThan];
                unsorted[greaterThan] = temp;
                lessThan++;
                greaterThan--;
            } else {
                if (unsorted[lessThan] < partition) {
                    lessThan++;
                }
                if (unsorted[greaterThan] > partition) {
                    greaterThan--;
                }
            }
        }
        unsorted[0] = unsorted[greaterThan];
        unsorted[greaterThan] = partition;
        return greaterThan;
    }

    public int[] sort(int[] unsorted) {
        inPlaceSort(unsorted, 0, unsorted.length - 1);
        return unsorted;
    }

    private void inPlaceSort(int[] unsorted, int start, int end) {
        if (end - start <= 15) {
            InsertionSort.inPlaceSort(unsorted, start, end);
            return;
        }
        int pivot = partition(unsorted, start, end);
        inPlaceSort(unsorted, start, pivot - 1);
        inPlaceSort(unsorted, pivot + 1, end);
    }

    private int partition(int[] unsorted, int start, int end) {
        int partition = unsorted[start], lessThan = start + 1, greaterThan = end;
        while (lessThan <= greaterThan) {
            if (unsorted[lessThan] >= partition && unsorted[greaterThan] <= partition) {
                int temp = unsorted[lessThan];
                unsorted[lessThan] = unsorted[greaterThan];
                unsorted[greaterThan] = temp;
                lessThan++;
                greaterThan--;
            } else {
                if (unsorted[lessThan] < partition) {
                    lessThan++;
                }
                if (unsorted[greaterThan] > partition) {
                    greaterThan--;
                }
            }
        }
        unsorted[start] = unsorted[greaterThan];
        unsorted[greaterThan] = partition;
        return greaterThan;
    }
}
