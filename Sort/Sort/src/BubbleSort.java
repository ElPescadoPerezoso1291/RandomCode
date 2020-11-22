public class BubbleSort implements Sort {
    @Override
    public int[] sort(int[] unsorted) {
        int n = unsorted.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (unsorted[j] > unsorted[j + 1]) {
                    int temp = unsorted[j];
                    unsorted[j] = unsorted[j + 1];
                    unsorted[j + 1] = temp;
                }
            }
        }
        return unsorted;
    }
}
