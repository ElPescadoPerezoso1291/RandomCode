public class SelectionSort implements Sort {
    @Override
    public int[] sort(int[] unsorted) {
        for (int i = 0; i < unsorted.length; i++) {
            int indexSwitch = getSmallestIndex(unsorted, i);
            int temp = unsorted[indexSwitch];
            unsorted[indexSwitch] = unsorted[i];
            unsorted[i] = temp;
        }
        return unsorted;
    }

    public int getSmallestIndex(int[] unsorted, int start) {
        int min = Integer.MAX_VALUE;
        int index = 0;
        for(int i = start; i < unsorted.length; i++) {
            if(unsorted[i] < min) {
                min = unsorted[i];
                index = i;
            }
        }
        return index;
    }
}
