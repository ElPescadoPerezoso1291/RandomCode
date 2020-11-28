import java.util.PriorityQueue;

public class MinimumPathSum {
    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 1,};
        int[] b = new int[]{1, 5, 1};
        int[] c = new int[]{4, 2, 1};
        int[][] grid1 = new int[][]{a, b, c};
        int[][] grid = new int[][]{
                new int[]{0,7,7,8,1,2,4,3,0,0,5,9,8,3,6,5,1,0},
                new int[]{2,1,1,0,8,1,3,3,9,9,5,8,7,5,7,5,5,8},
                new int[]{9,2,3,1,2,8,1,2,3,7,9,7,9,3,0,0,3,8},
                new int[]{3,9,3,4,8,1,2,6,8,9,3,4,9,4,8,3,6,2},
                new int[]{3,7,4,7,6,5,6,5,8,6,7,3,6,2,2,9,9,3},
                new int[]{2,3,1,1,5,4,7,4,0,7,7,6,9,1,5,5,0,3},
                new int[]{0,8,8,8,4,7,1,0,2,6,1,1,1,6,4,2,7,9},
                new int[]{8,6,6,8,3,3,5,4,6,2,9,8,6,9,6,6,9,2},
                new int[]{6,2,2,8,0,6,1,1,4,5,3,1,7,3,9,3,2,2},
                new int[]{8,9,8,5,3,7,5,9,8,2,8,7,4,4,1,9,2,2},
                new int[]{7,3,3,1,0,9,4,7,2,3,2,6,7,1,7,7,8,1},
                new int[]{4,3,2,2,7,0,1,4,4,4,3,8,6,2,1,2,5,4},
                new int[]{1,9,3,5,4,6,4,3,7,1,0,7,2,4,0,7,8,0},
                new int[]{7,1,4,2,5,9,0,4,1,4,6,6,8,9,7,1,4,3},
                new int[]{9,8,6,8,2,6,5,6,2,8,3,2,8,1,5,4,5,2},
                new int[]{3,7,8,6,3,4,2,3,5,1,7,2,4,6,0,2,5,4},
                new int[]{8,2,1,2,2,6,6,0,7,3,6,4,5,9,4,4,5,7}};
        MinimumPathSum ps = new MinimumPathSum();
        //System.out.println(ps.minimumPathSum(grid1));
        for(int x  : x()) {
            System.out.println(x);
        }
    }
    public static int[] x() {
        System.out.println("1 2 3");
        return new int[]{1, 2, 3};
    }

    /**
     * we can probably use the A* algorithm here, or something similar to it
     * We do not have to keep track of the vertices, we simply need to keep track of the
     * sum. Perhaps using a static Priority Queue will suffice.
     */
    public int minimumPathSum(int[][] grid) {
        Node[][] g = convert(grid);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(g[0][0]);
        g[0][0].distance = grid[0][0];
        while (pq.size() != 0) {
            Node smallest = pq.poll();
            smallest.marked = true;
            if (smallest.i == grid.length - 1 && smallest.j == grid[0].length - 1) {
                return smallest.distance;
            }
            for (Node n : neighbors(smallest, g)) {
                if(!n.marked) {
                    n.distance = smallest.distance + grid[n.i][n.j];
                    pq.add(n);
                }
            }
        }
        return 0;
    }

    private Node[][] convert(int[][] grid) {
        Node[][] n = new Node[grid.length][];
        for(int i = 0; i < grid.length; i++) {
            n[i] = new Node[grid[0].length];
            for(int j = 0; j < n[i].length; j++) {
                n[i][j] = new Node(i, j, 0);
            }
        }
        return n;
    }
    private Node[] neighbors(Node smallest, Node[][] grid) {
        if(smallest.i < grid.length - 1 && smallest.j < grid[0].length - 1) {
            return new Node[]{grid[smallest.i][smallest.j + 1], grid[smallest.i + 1][smallest.j]};
        }
        else if(smallest.i == grid.length - 1) {
            return new Node[]{grid[smallest.i][smallest.j + 1]};
        }
        else {
            return new Node[]{grid[smallest.i + 1][smallest.j]};
        }
    }

    private class Node implements Comparable<Node> {
        int i, j, distance;
        boolean marked;

        Node(int i, int j, int distance) {
            this.i = i;
            this.j = j;
            this.distance = distance;
            marked = false;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.distance, o.distance);
        }
    }
}
