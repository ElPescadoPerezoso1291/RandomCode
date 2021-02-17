import java.util.*;
public class bruh {
    public static void main(String[] args) {
        ListNode[] input = { new ListNode(1, new ListNode(4, new ListNode(5))),
                new ListNode(1, new ListNode(3, new ListNode(4))),
                new ListNode(2, new ListNode(6))};
        mergeKLists(input);
    }
    public static ListNode mergeKLists(ListNode[] lists) {
        //O(Nlog(L))
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new ListNodeComparator());
        for (ListNode l : lists) {
            pq.offer(l);
        }
        return merge(pq);
    }
     
    public static ListNode merge(PriorityQueue<ListNode> pq) {
        if (pq.isEmpty()) {
            return null;
        }
        ListNode minimum = pq.poll();
        if (minimum.next != null) {
            pq.offer(minimum.next);
        }
        return new ListNode(minimum.val, merge(pq));
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static class ListNodeComparator implements Comparator<ListNode> {
        public int compare(ListNode a, ListNode b) {
            return a.val - b.val
        }
    }
}
