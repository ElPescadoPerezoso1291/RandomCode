import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        Node(K k, V v, Node l, Node r) {
            key = k;
            value = v;
            left = l;
            right = r;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        size = 0;
    }

    public void printInOrder()
    {
        
    }

    private void printInOrder(Node start)
    {
        if(start == null)
        {
            return;
        }
        System.out.println(start.key);
        printInOrder(start.left);
        printInOrder(start.right);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        if (isEmpty()) {
            return;
        }
        clear(root);
        root = null;
        size = 0;
    }

    private void clear(Node start) {
        if (start == null) {
            return;
        }
        clear(start.right);
        clear(start.left);
        start.left = null;
        start.right = null;
        return;
    }

    public boolean containsKey(K key) {
        if (isEmpty()) {
            return false;
        }
        return containsKey(key, root);
    }

    public boolean containsKey(K key, Node start) {
        if (start == null) {
            return false;
        }
        if (key.equals(start.key)) {
            return true;
        } else if (key.compareTo(start.key) < 0) {
            return containsKey(key, start.left);
        }
        return containsKey(key, start.right);
    }

    public V get(K key) {
        return get(key, root);
    }

    public V get(K key, Node start) {
        if (start == null) {
            return null;
        } else if (start.key.equals(key)) {
            return start.value;
        } else if (key.compareTo(start.key) < 0) {
            return get(key, start.left);
        }
        return get(key, start.right);
    }

    public int size() {
        return size;
    }

    public void put(K key, V value) {
        put(key, value, root);
    }

    public Node put(K key, V value, Node start) {
        if (start == null) {
            return new Node(key, value, null, null);
        } else if (key.compareTo(start.key) < 0) {
            start.left = put(key, value, start.left);
        } else if (key.compareTo(start.key) > 0) {
            start.right = put(key, value, start.right);
        }
        return start;
    }

    private Node getMax(Node start) {
        if (start.right == null) {
            return start;
        }
        return getMax(start.right);
    }

    public Set<K> keySet() {
        TreeSet<K> theSet = new TreeSet<>();
        keySet(theSet, root);
        return theSet;
    }

    private void keySet(TreeSet<K> soFar, Node start) {
        if (start == null) {
            return;
        }
        soFar.add(start.key);
        keySet(soFar, start.left);
        keySet(soFar, start.right);
    }

    public V remove(K key) {
        V removed = get(key);
        if (removed != null) {
            size--;
        }
        root = remove(key, root);
        return removed;
    }

    public Node remove(K key, Node start) {
        if (start == null) {
            return null;
        }
        if (start.key.equals(key)) {
            if (start.left == null && start.right == null) {
                return null;
            } else if (start.left == null) {
                return start.right;
            } else if (start.right == null) {
                return start.left;
            } else {
                Node maximum = getMax(start.left);
                remove(maximum.key, root);
                start.key = maximum.key;
                start.value = maximum.value;
                return start;
            }
        } else if (key.compareTo(start.key) < 0) {
            start.left = remove(key, start.left);
        } else if (key.compareTo(start.key) > 0) {
            start.right = remove(key, start.right);
        }
        return start;
    }

    public V remove(K key, V value) {
        V removed = get(key);
        if (removed != null) {
            size--;
        }
        root = remove(key, value, root);
        return removed;
    }
    private Node remove(K key, V value, Node start)
    {
        if (start == null) {
            return null;
        }
        if (start.key.equals(key) && start.value.equals(value)) {
            if (start.left == null && start.right == null) {
                return null;
            } else if (start.left == null) {
                return start.right;
            } else if (start.right == null) {
                return start.left;
            } else {
                Node maximum = getMax(start.left);
                remove(maximum.key, root);
                start.key = maximum.key;
                start.value = maximum.value;
                return start;
            }
        } else if (key.compareTo(start.key) < 0) {
            start.left = remove(key, start.left);
        } else if (key.compareTo(start.key) > 0) {
            start.right = remove(key, start.right);
        }
        return start;
    }
}