public class MSDRadixSort implements Sort{

    @Override
    public int[] sort(int[] unsorted) {
        return new int[0];
    }
    private int[] sort(int[] unsorted, int digit) {
        if(digit > max(unsorted)) {
            return unsorted;
        }
        int[] count = new int[10];
        for(int n: unsorted) {
            count[n / digit % 10]++;
        }
        int sum = 0;
        for(int i = 0; i < count.length; i++) {
            int index = sum;
            sum += count[i];
            count[i] = index;
        }
        int[] ordered = new int[unsorted.length];
        for(int n : unsorted) {
            ordered[count[n / digit % 10]] = n;
            count[n / digit % 10]++;
        }
        return unsorted;
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
