import java.util.Iterator;

public class DLList<T> implements Deque<T>, Iterable<T> {
    private Node sentinel = new Node(null);
    private int size = 0;

    public DLList() {}

    @Override
    public void addFirst(T item) {
        sentinel.next.prev = new Node(sentinel, item, sentinel.next);
        sentinel.next = sentinel.next.prev;
        size++;
    }

    @Override
    public void addLast(T item) {
        sentinel.prev.next = new Node(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
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
        for (Node n = sentinel.next; n != sentinel; n = n.next) {
            System.out.print(n.item + " ");
        }
    }

    @Override
    public T removeFirst() {
        T removed = null;
        if (size > 0) {
            removed = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size--;
        }
        return removed;
    }

    @Override
    public T removeLast() {
        T removed = null;
        if (size > 0) {
            removed = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size--;
        }
        return removed;
    }

    @Override
    public T get(int index) {
        return get(index, sentinel.next);
    }

    @Override
    public Iterator<T> iterator() {
        return new DLListIterator();
    }

    private T get(int index, Node curr) {
        if (curr == sentinel) {
            return null;
        } else if (index == 0) {
            return curr.item;
        }
        return get(index - 1, curr.next);
    }

    private class Node {
        T item;
        Node next;
        Node prev;

        public Node(Node prev, T item, Node next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        public Node(T item) {
            this.item = item;
            this.next = this;
            this.prev = this;
        }
    }

    private class DLListIterator implements Iterator<T> {
        Node current = sentinel;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T returned = current.item;
            current = current.next;
            return returned;
        }
    }

}
