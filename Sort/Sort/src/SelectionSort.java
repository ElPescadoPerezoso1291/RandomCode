public class SelectionSort implements Sort{
    @Override
    public int[] sort(int[] unsorted) {
        for (int i = 0; i < unsorted.length; i++) {
            for (int j = i; j < unsorted.length; j++) {
                if (unsorted[j] < unsorted[i]) {
                    int temp = unsorted[j];
                    unsorted[j] = unsorted[i];
                    unsorted[i] = temp;
                }
            }
        }
        return unsorted;
    }
}
