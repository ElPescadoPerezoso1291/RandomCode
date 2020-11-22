import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private ArrayList<Node>[] buckets;
    private double loadFactor;
    private int size;

    public MyHashMap() {
        loadFactor = 0.75;
        size = 0;
        buckets = new ArrayList[16];
        for (int i = 0; i < 16; i++) {
            buckets[i] = new ArrayList<Node>();
        }
    }

    public MyHashMap(int intialSize) {
        loadFactor = 0.75;
        size = 0;
        buckets = new ArrayList[intialSize];
        for (int i = 0; i < intialSize; i++) {
            buckets[i] = new ArrayList<Node>();
        }
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.loadFactor = loadFactor;
        size = 0;
        buckets = new ArrayList[initialSize];
        for (int i = 0; i < initialSize; i++) {
            buckets[i] = new ArrayList<Node>();
        }
    }

    private double getCurrentLoadFactor() {
        return size / buckets.length;
    }

    public Iterator<K> iterator() {
        return null;
    }

    public void put(K key, V value) {
        ArrayList<Node> ls = buckets[Math.floorMod(key.hashCode(), buckets.length)];
        if (containsKey(key)) {
            for (Node n : ls) {
                if (n.key.equals(key)) {
                    n.value = value;
                    return;
                }
            }
        } else {
            ls.add(new Node(key, value));
            size++;
        }
        if (getCurrentLoadFactor() >= loadFactor) {
            resize(2);
        }
    }

    private void resize(int factor)
    {
        MyHashMap<K, V> newLength = new MyHashMap<K, V>(buckets.length * factor, loadFactor);
        for (int i = 0; i < buckets.length; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                newLength.put(buckets[i].get(j).key, buckets[i].get(j).value);
            }
        }
        buckets = newLength.buckets;
    }

    private void downsize(int factor)
    {
        MyHashMap<K, V> newLength = new MyHashMap<K, V>(buckets.length / factor, loadFactor);
        for (int i = 0; i < buckets.length; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                newLength.put(buckets[i].get(j).key, buckets[i].get(j).value);
            }
        }
        buckets = newLength.buckets;
    }

    /**
     * Removes all of the mappings from this map.
     */
    public void clear() {
        size = 0;
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     */
    public boolean containsKey(K key) {
        int code = key.hashCode();
        ArrayList<Node> ls = buckets[Math.floorMod(code, buckets.length)];
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        int code = key.hashCode();
        ArrayList<Node> ls = buckets[Math.floorMod(code, buckets.length)];
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).key.equals(key)) {
                return ls.get(i).value;
            }
        }
        return null;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    public int size() {
        return size;
    }

    public Set<K> keySet() {

        TreeSet<K> set = new TreeSet<>();
        for (ArrayList<Node> ls : buckets) {
            for (int i = 0; i < ls.size(); i++) {
                set.add(ls.get(i).key);
            }
        }
        return set;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        ArrayList<Node> ls = buckets[Math.floorMod(key.hashCode(), buckets.length)];
        for(int i = 0; i < ls.size(); i++)
        {
            if(key.equals(ls.get(i).key))
            {
                size--;
                Node removed = ls.get(i);
                ls.remove(i);
                if(getCurrentLoadFactor() <= 0.5)
                {
                    downsize(2);
                }
                return removed.value;
            }
        }
        return null;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {
        ArrayList<Node> ls = buckets[Math.floorMod(key.hashCode(), buckets.length)];
        for(int i = 0; i < ls.size(); i++)
        {
            if(key.equals(ls.get(i).key) && value.equals(ls.get(i).value))
            {
                size--;
                Node removed = ls.get(i);
                ls.remove(i);
                if(getCurrentLoadFactor() <= 0.5)
                {
                    downsize(2);
                }
                return removed.value;
            }
        }
        return null;
    }

}
