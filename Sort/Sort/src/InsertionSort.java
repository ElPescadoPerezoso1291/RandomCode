public class InsertionSort implements Sort{

    public int[] outPlaceSort(int[] unsorted) {
        int[] sorted = new int[unsorted.length];
        for (int i = 0; i < unsorted.length; i++) {
            sorted[i] = unsorted[i];
            int index = i;
            while (index != 0 && sorted[index] < sorted[index - 1]) {
                int temp = sorted[index];
                sorted[index] = sorted[index - 1];
                sorted[index - 1] = temp;
                index -= 1;
            }
        }
        return sorted;
    }
    @Override
    public int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int index = i;
            while (index != 0 && arr[index] < arr[index - 1]) {
                int temp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = temp;
                index -= 1;
            }
        }
        return arr;
    }

    public static int[] inPlaceSort(int[] arr, int start, int end) {
        for (int i = start; i < end + 1; i++) {
            int index = i;
            while (index != 0 && arr[index] < arr[index - 1]) {
                int temp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = temp;
                index -= 1;
            }
        }
        return arr;
    }
}
