
import java.util.*;
public class Trie {
    private Node root = new Node('`', new HashMap<>(), false);
    private int size = 0;
    public Trie() {
    }
    public List<String> getKeysWithPrefix(String prefix) {
        List<String> ls = new ArrayList<>();
        getKeysWithPrefix(root, prefix, ls, new StringBuilder());
        return ls;
    }
    private void getKeysWithPrefix(Node current, String prefix, List<String> soFar, StringBuilder str) {
        if(current == null) {
            return;
        }
        if(prefix.length() == 0) {
            if(current.end) {
                soFar.add(str.toString());
            }
            for(Node v : current.branches.values()) {
                str.append(v.value);
                getKeysWithPrefix(v, prefix, soFar, str);
                str.deleteCharAt(str.length() - 1);
            }
            return;
        }
        str.append(prefix.charAt(0));
        getKeysWithPrefix(current.branches.get(prefix.charAt(0)), prefix.substring(1), soFar, str);
    }
    public void put(String word) {
        put(word, root);
    }

    private void put(String word, Node current) {
        if(word.length() == 0) {
            current.end = true;
            return;
        }
        char firstChar = word.charAt(0);
        if (current.branches.containsKey(firstChar)) {
            put(word.substring(1), current.branches.get(firstChar));
        } else {
            current.branches.put(firstChar, new Node(firstChar, new HashMap<>(), false));
            put(word.substring(1), current.branches.get(firstChar));
        }
    }

    private class Node {
        char value;
        HashMap<Character, Node> branches;
        boolean end;

        Node(char value, HashMap<Character, Node> branches, boolean end) {
            this.value = value;
            this.branches = branches;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.put("hello");
        trie.put("why");
        trie.put("hell");
        trie.put("helltaker");
        System.out.println(trie.getKeysWithPrefix("he"));
    }
}
