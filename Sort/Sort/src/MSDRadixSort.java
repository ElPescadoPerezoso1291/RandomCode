public class MSDRadixSort implements Sort {
//not implemented
    @Override
    public int[] sort(int[] unsorted) {
        sort(unsorted, 0, 0, unsorted.length - 1);
        return unsorted;
    }

    private void sort(int[] unsorted, int d, int start, int end) {
        if(max(unsorted) < Math.pow(10, d)) {
            return;
        }
        int[] count = new int[10];
        int[] countStart = new int[10];
        int[] countEnd = new int[10];
        for (int i = start; i <= end; i++) {
            count[digit(d, unsorted[i])]++;
        }
        int sum = 0;
        for (int i = 0; i < count.length; i++) {
            int index = sum;
            countStart[i] = sum;
            sum += count[i];
            countEnd[i] = sum;
            count[i] = index;
        }
        int[] ordered = new int[unsorted.length];
        for (int i = start; i <= end; i++) {
            ordered[count[digit(d, unsorted[i])]] = unsorted[i];
            count[digit(d, unsorted[i])]++;
        }
        System.arraycopy(ordered, start, unsorted, 0, end + 1 - start);
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                sort(unsorted, d + 1, countStart[i], countEnd[i] - 1);
            }
        }
    }

    private int max(int[] unsorted) {
        int maxi = 0;
        for (int n : unsorted) {
            if (n > maxi) {
                maxi = n;
            }
        }
        return maxi;
    }

    private int digit(int d, int n) {
        if (Math.pow(10, d) > n) {
            return 0;
        }
        return (n / (int) Math.pow(10, (int) (Math.log10(n)) - d)) % 10;
    }
}
