import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BST {
    private Node root;
    private int size;
    static final int COUNT = 10;

    public BST() {
        this.size = 0;
    }

    public void add(int val) {
        this.root = add(val, this.root);
    }

    private Node add(int val, Node n) {
        if (n == null) {
            size++;
            return new Node(val);
        } else if (n.val == val) {
            return n;
        } else if (val < n.val) {
            n.left = add(val, n.left);
        } else {
            n.right = add(val, n.right);
        }
        return n;
    }

    public boolean contains(int val) {
        return contains(this.root, val);
    }

    private boolean contains(Node n, int val) {
        if (n == null) {
            return false;
        } else if (val == n.val) {
            return true;
        } else if (val < n.val) {
            return contains(n.left, val);
        }
        return contains(n.right, val);
    }
    public int height() {
        return height(root);
    }

    private static int height(Node node) {
        if (node != null) {
            return 1 + Math.max(height(node.left), height(node.right));
        }
        return 0;
    }
    void print2DUtil(Node root, int space)
    {
        // Base case
        if (root == null)
            return;

        // Increase distance between levels
        space += COUNT;

        // Process right child first
        print2DUtil(root.right, space);

        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.print(root.val + "\n");

        // Process left child
        print2DUtil(root.left, space);
    }

    // Wrapper over print2DUtil()
    void print2D(Node root)
    {
        // Pass initial space count as 0
        print2DUtil(root, 0);
    }

    public class Node {
        private int val;
        private Node left, right;

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public Node(int val) {
            this.val = val;
        }
    }
}
