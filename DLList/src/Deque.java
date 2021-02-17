import java.util.Iterator;

public interface Deque<T> extends Iterable<T> {
    /**
     * adds an item to the front of the list
     * @param item
     */
    void addFirst(T item);

    /**
     * adds an item to the back of the list
     * @param item
     */
    void addLast(T item);

    /**
     * returns whether if the list is empty
     * @return True if the list is empty, False if not
     */
    boolean isEmpty();

    /**
     * return the size of the list
     * @return the size of the list
     */
    int size();

    /**
     * Prints all elements of the deque in order
     */
    void printDeque();

    /**
     * Removes the first element of the list
     * @return the first element of the list. If it does not exist, return null
     */
    T removeFirst();

    /**
     * removes the last element of the list
     * @return the last element of the list. If it does not exist, return null
     */
    T removeLast();

    /**
     * gets the element at index INDEX
     * @param index, the index we wish to find
     * @return the element at the given index. If it does not exist, return null
     */
    T get(int index);

    @Override
    Iterator<T> iterator();
}
