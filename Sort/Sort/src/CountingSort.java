public class CountingSort implements Sort {
    @Override
    public int[] sort(int[] unsorted) {
        int[] possibleVals = new int[max(unsorted) + 1];

        for (int n : unsorted) {
            possibleVals[n]++;
        }
        int sum = 0;
        for (int i = 0; i < possibleVals.length; i++) {
            int index = sum;
            sum += possibleVals[i];
            possibleVals[i] = index;
        }
        int[] ordered = new int[unsorted.length];
        for (int n : unsorted) {
            ordered[possibleVals[n]] = n;
            possibleVals[n]++;
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
