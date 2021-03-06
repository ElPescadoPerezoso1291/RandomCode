import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] deque = (T[]) new Object[8];
    private int size = 0, FIRSTNext = 3, LASTNext = 4;

    public static void main(String[] args) {
        int[] x = {1, 2, 3, 4};
        System.out.println(x);
    }
    public ArrayDeque() {}

    private int indexOverflow(int i) {
        return Math.floorMod(i, deque.length);
    }

    private int getLastIndex() {
        return indexOverflow(LASTNext - 1);
    }

    private int getFirstIndex() {
        return indexOverflow(FIRSTNext + 1);
    }

    private void resize(int x) {
        T[] newArray = (T[]) new Object[x];
        for (int i = 0; i < size; i++) {
            newArray[i] = get(i);
        }
        deque = newArray;
        FIRSTNext = newArray.length - 1;
        LASTNext = size;
    }

    private void resize() {
        if (size == deque.length) {
            resize(deque.length * 4);
        } else if (size < deque.length / 4 && size > 32) {
            resize(deque.length / 4);
        }
    }

    @Override
    public void addFirst(T item) {
        resize();
        deque[FIRSTNext] = item;
        FIRSTNext = indexOverflow(FIRSTNext - 1);
        size++;
    }

    @Override
    public void addLast(T item) {
        resize();
        deque[LASTNext] = item;
        LASTNext = indexOverflow(LASTNext + 1);
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        T toRemove = null;
        if (!isEmpty()) {
            resize();
            toRemove = deque[getFirstIndex()];
            deque[getFirstIndex()] = null;
            FIRSTNext = indexOverflow(FIRSTNext + 1);
            size--;
        }
        return toRemove;
    }

    @Override
    public T removeLast() {
        T toRemove = null;
        if (!isEmpty()) {
            resize();
            toRemove = deque[getLastIndex()];
            deque[getLastIndex()] = null;
            LASTNext = indexOverflow(LASTNext - 1);
            size--;
        }
        return toRemove;
    }

    @Override
    public T get(int index) {
        return deque[indexOverflow(getFirstIndex() + index)];
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private class ArrayDequeIterator implements Iterator<T>{
        int length = size();
        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < length;
        }

        @Override
        public T next() {
            T returned = get(currentIndex);
            currentIndex++;
            return returned;
        }
    }
}
