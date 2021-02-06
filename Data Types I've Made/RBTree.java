import java.util.ArrayList;

public class RBTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;
    private int size;
    static final int COUNT = 10;

    public RBTree() {
        this.size = 0;
    }

    public static void main(String[] args) {
        RBTree t = new RBTree();
        int N = 100;
        for(int i = 0; i < N; i++) {
            int rando = (int) (Math.random() * N);
            t.add(rando);
        }
        t.print2D();
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
    void print2D()
    {
        // Pass initial space count as 0
        print2DUtil(root, 0);
    }

    private static int height(Node node) {
        if (node != null) {
            return 1 + Math.max(height(node.left), height(node.right));
        }
        return 0;
    }

    private void rotateRight(Node node) {
        if (node.left != null) {
            Node right = new Node(node.val, node.left.right, node.right, RED);
            node.val = node.left.val;
            node.left = node.left.left;
            node.right = right;
            node.color = BLACK;
        }
    }

    private void rotateLeft(Node node) {
        if (node.right != null) {
            Node left = new Node(node.val, node.left, node.right.left, RED);
            node.val = node.right.val;
            node.right = node.right.right;
            node.left = left;
        }
    }

    private void flip(Node node) {
        node.left.color = BLACK;
        node.right.color = BLACK;
        node.color = RED;
    }

    public void add(int val) {
        this.root = add(this.root, val);
    }

    private Node add(Node node, int val) {
        if (node == null) {
            this.size++;
            return new Node(val, null, null, RED);
        } else if (val == node.val) {
            return node;
        } else if (val < node.val) {
            node.left = add(node.left, val);
        } else {
            node.right = add(node.right, val);
        } if (node.left != null
                && node.right != null
                && node.left.color == RED
                && node.right.color == RED) {
            flip(node);
        } if (node.right != null && node.right.color == RED) {
            rotateLeft(node);
        } if (node.left != null
                && node.left.left != null
                && node.left.color == RED
                && node.left.left.color == RED) {
            rotateRight(node);
            flip(node);
        }
        return node;
    }

    public boolean contains(int val) {
        return contains(this.root, val);
    }

    private boolean contains(Node node, int val) {
        if (node == null) {
            return false;
        } else if (val == node.val) {
            return true;
        } else if (val < node.val) {
            return contains(node.left, val);
        }
        return contains(node.right, val);
    }

    public int size() {
        return this.size();
    }

    public int height() {
        return height(root);
    }

    public void printTree() {
        printTree(root, "");
    }

    private void printTree(Node t, String spaces) {
        if (t != null) {
            if(t.color == RED) {
                System.out.println(spaces + "[" + t.val + "]");
            }
            else {
                System.out.println(spaces + t.val);
            }
            printTree(t.left, spaces + "   ");
            printTree(t.right, spaces + "   ");
        }
    }

    public class Node {
        private boolean color;
        private int val;
        private Node left, right;

        public Node(int val, Node left, Node right, boolean color) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.color = color;
        }
    }
}
