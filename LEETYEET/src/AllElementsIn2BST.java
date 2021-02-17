import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AllElementsIn2BST {
    public static void main(String[] args) {
        TreeNode x = new TreeNode(2, new TreeNode(1), new TreeNode(4));
        TreeNode y = new TreeNode(1, new TreeNode(0), new TreeNode(3));
        System.out.println(getAllElements(x, y));
    }

    public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        ArrayList<Integer> root1Ls = new ArrayList<>(), root2Ls = new ArrayList<>();
        postOrder(root1, root1Ls);
        postOrder(root2, root2Ls);
        return merge(root1Ls, root2Ls);
    }

    private static void postOrder(TreeNode root, List<Integer> ls) {
        if (root != null) {
            postOrder(root.left, ls);
            ls.add(root.val);
            postOrder(root.right, ls);
        }
    }

    private static List<Integer> merge(List<Integer> a, List<Integer> b) {
        List<Integer> newList = new ArrayList<>();
        int s = a.size() + b.size();
        for (int i = 0; i < s; i++) {
            if (a.size() == 0) {
                newList.add(b.remove(0));
            } else if (b.size() == 0) {
                newList.add(a.remove(0));
            } else if (a.get(0) == b.get(0) || a.get(0) < b.get(0)) {
                newList.add(a.remove(0));
            } else {
                newList.add(b.remove(0));
            }
        }
        return newList;
    }

    private static List<Integer> inOrder(TreeNode t) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> traversal = new ArrayList<>();
        pushLeft(t, stack);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            traversal.add(current.val);
            pushLeft(current.right, stack);
        }
        return traversal;
    }

    private static void pushLeft(TreeNode t, Stack<TreeNode> s) {
        while (t != null) {
            s.push(t);
            t = t.left;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
