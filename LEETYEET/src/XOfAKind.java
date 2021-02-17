import java.util.HashMap;

public class XOfAKind {
    class Solution {
        public boolean hasGroupsSizeX(int[] deck) {
            HashMap<Integer, Integer> counter = new HashMap<>();
            for (int x : deck) {
                counter.put(x, counter.getOrDefault(x, 0) + 1);
            }
            int current = counter.get(deck[0]);
            for (int x : counter.values()) {
                if (GCD(current, x) == 1 || x == 1) {
                    return false;
                }
                current = x;
            }
            return true;
        }

        public int GCD(int a, int b) {
            if (a % b == 0) {
                return b;
            } else {
                return GCD(b, a % b);
            }
        }
    }
}
