import java.util.*;

public class TopKFrequent {
    public static void main(String[] args) {
        String[] input = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        System.out.println(topKFrequent(input, 2));
    }
    public static class Pair {
        String s;
        int count;

        public Pair(String s, int count) {
            this.s = s;
            this.count = count;
        }

        public boolean equals(Object o) {
            Pair p = (Pair) o;
            return s.equals(p.s) && count == p.count;
        }
    }

    public static class pairComparator implements Comparator<Pair> {
        public int compare(Pair a, Pair b) {
            return a.count - b.count;
        }
    }

    public static List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> wordCounter = new HashMap<>();
        for(String s : words) {
            wordCounter.put(s, wordCounter.getOrDefault(s, 0) + 1);
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>(new pairComparator());
        for(String s : wordCounter.keySet()) {
            pq.offer(new Pair(s, wordCounter.get(s)));
            if(pq.size() > k) {
                pq.poll();
            }
        }
        List<String> returned = new ArrayList<>();
        int s = pq.size();
        for(int i = 0; i < s; i++) {
            returned.add(pq.poll().s);
        }
        return returned;
    }
}
