import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SortingTests {
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }
    @Test
    public void timeSorts()
    {
        printSort(new BubbleSort());
        printSort(new SelectionSort());
        printSort(new InsertionSort());
        printSort(new HeapSort());
        printSort(new MergeSort());
        printSort(new QuickSort());
        printSort(new CountingSort());
        printSort(new LSDRadixSort());
    }
    private static void printSort(Sort sorter) {
        ArrayList<Double> times = new ArrayList<>();
        ArrayList<Integer> ops = new ArrayList<>();
        ArrayList<Integer> Ns = new ArrayList<>();
        for (int N = 1000; N < 10000000; N *= 2) {
            int[] sample = new int[N];
            for (int j = 0; j < N; j++) {
                sample[j] = (int) (Math.random() * N);
            }
            Stopwatch sw = new Stopwatch();
            sorter.sort(sample);
            times.add(sw.elapsedTime());
            ops.add(N);
            Ns.add(N);
        }
        printTimingTable(Ns, times, ops);
    }

    @Test
    public void testCorrectness() {
        Sort sorter = new BubbleSort(); //interchangable sorter
        int N = 10;
        for (int i = 0; i < N; i++) {
            int[] x = new int[N];
            int[] y = new int[N];
            for (int j = 0; j < N; j++) {
                int z = (int) (Math.random() * N);
                x[j] = z;
                y[j] = z;
            }
            x = sorter.sort(x);
            Arrays.sort(y);
            for (int j = 0; j < N; j++) {
                assertEquals(x[j], y[j]);
            }
        }
    }
    public static int digit(int d, int n) {
        if(Math.pow(10, d) > n) {
            return 0;
        }
        return (n / (int) Math.pow(10, (int) (Math.log10(n)) - d)) % 10;
    }
    public static void main(String[] args) {

        for(int i = 0; i < 9; i++) {
            System.out.println(digit(i, 287137341));
        }
    }
}
