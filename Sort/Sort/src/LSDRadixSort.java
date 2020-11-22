public class LSDRadixSort implements Sort{

    @Override
    public int[] sort(int[] unsorted) {
        int[] ordered = unsorted;
        int d = 1, max = max(unsorted);
        while(d < max) {
            int[] count = new int[10];
            for(int n: unsorted) {
                count[n / d % 10]++;
            }
            int sum = 0;
            for(int i = 0; i < count.length; i++) {
                int index = sum;
                sum += count[i];
                count[i] = index;
            }
            ordered = new int[unsorted.length];
            for(int n : unsorted) {
                ordered[count[n / d % 10]] = n;
                count[n / d % 10]++;
            }
            unsorted = ordered;
            d *= 10;
        }
        return ordered;
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
}
