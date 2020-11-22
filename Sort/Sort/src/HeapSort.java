import MinPQ.ArrayHeapMinPQ;

public class HeapSort implements Sort {

    @Override
    public int[] sort(int[] unsorted) {
        ArrayHeapMinPQ<Integer> sorter = new ArrayHeapMinPQ<>();
        for (int i = 0; i < unsorted.length; i++) {
            sorter.add(unsorted[i], unsorted[i]);
        }
        for (int i = 0; i < unsorted.length; i++) {
            unsorted[i] = sorter.removeSmallest();
        }
        return unsorted;
    }
}
