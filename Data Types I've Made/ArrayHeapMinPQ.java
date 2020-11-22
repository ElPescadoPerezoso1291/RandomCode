import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private final ArrayList<Node<T>> heap;
    private final HashMap<T, Node<T>> hash;

    public ArrayHeapMinPQ() {
        heap = new ArrayList<>();
        hash = new HashMap<>();
    }

    //@source Josh hug
    private static void printSimpleHeapDrawing(Object[] heap) {
        int depth = ((int) (Math.log(heap.length) / Math.log(2)));
        int level = 0;
        int itemsUntilNext = (int) Math.pow(2, level);
        for (int j = 0; j < depth; j++) {
            System.out.print(" ");
        }

        for (int i = 1; i < heap.length; i++) {
            System.out.printf("%d ", heap[i]);
            if (i == itemsUntilNext) {
                System.out.println();
                level++;
                itemsUntilNext += Math.pow(2, level);
                depth--;
                for (int j = 0; j < depth; j++) {
                    System.out.print(" ");
                }
            }
        }
        System.out.println();
    }

    private static void main(String[] args) {
        //sanity testing passed
        ArrayHeapMinPQ<Integer> x = new ArrayHeapMinPQ<>();
        x.add(1, 1);
        x.add(5, 5);
        x.add(1, 1);
        x.add(6, 6);
        x.add(5, 5);
        x.add(6, 6);
        x.add(3, 3);
        x.add(7, 7);
        x.add(7, 7);
        x.add(8, 8);
        x.add(3, 3);
        x.add(5, 5);
        x.removeSmallest();
        x.removeSmallest();
        Integer[] a = new Integer[x.heap.size() + 1];
        for (int i = 1; i < x.heap.size() + 1; i++) {
            a[i] = x.heap.get(i - 1).data;
        }
        printSimpleHeapDrawing(a);
    }

    private int getParentIndex(int k) {
        if (k == 0) {
            return 0;
        }
        return (k - 1) / 2;
    }

    private int getLeftChild(int k) {
        int child = k * 2 + 1;
        if (child < size()) {
            return child;
        }
        return k;
    }

    private int getRightChild(int k) {
        int child = k * 2 + 2;
        if (child < size()) {
            return child;
        }
        return k;
    }

    private void swim(int index) {
        Node<T> swimmer = heap.get(index);
        Node<T> parent = heap.get(getParentIndex(index));
        int cp = swimmer.compareTo(parent);
        if (cp < 0) {
            //switching values here, should take O(logN)
            heap.set(index, parent);
            heap.get(index).index = index;
            heap.set(getParentIndex(index), swimmer);
            heap.get(getParentIndex(index)).index = getParentIndex(index);
            swim(getParentIndex(index));
        }
    }


    private void sink(int index) {
        //should take O(logN)
        Node<T> leftChild = heap.get(getLeftChild(index));
        Node<T> rightChild = heap.get(getRightChild(index));
        Node<T> parent = heap.get(index);
        int indexSwitch;
        Node<T> smallest;
        if (leftChild.compareTo(rightChild) < 0) {
            smallest = leftChild;
            indexSwitch = getLeftChild(index);
        } else {
            smallest = rightChild;
            indexSwitch = getRightChild(index);
        }
        if (parent.compareTo(smallest) > 0) {
            heap.set(index, smallest);
            heap.get(index).index = index;
            heap.set(indexSwitch, parent);
            heap.get(indexSwitch).index = indexSwitch;
            sink(indexSwitch);
        }
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        Node<T> newNode = new Node(item, priority, heap.size());
        hash.put(item, newNode);
        heap.add(newNode);
        swim(heap.size() - 1);
    }

    @Override
    public boolean contains(T item) {
        return hash.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return heap.get(0).data;
    }

    @Override
    public T removeSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        if (size() == 1) {
            return heap.remove(0).data;
        }
        T removed = getSmallest();
        hash.remove(removed);
        heap.set(0, heap.remove(heap.size() - 1));
        heap.get(0).index = 0;
        sink(0);
        return removed;
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        }
        Node<T> swimmer = hash.get(item);
        double oPriority = swimmer.priority;
        swimmer.priority = priority;
        if (oPriority < priority) {
            sink(swimmer.index);
        } else {
            swim(swimmer.index);
        }
    }

    private class Node<T> implements Comparable<Node> {
        double priority;
        T data;
        int index;

        Node(T d, double p, int i) {
            this.data = d;
            this.priority = p;
            this.index = i;
        }

        public int compareTo(Node other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.priority, other.priority);
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((Node) o).data.equals(data);
            }
        }

        @Override
        public int hashCode() {
            return data.hashCode();
        }
    }
}
