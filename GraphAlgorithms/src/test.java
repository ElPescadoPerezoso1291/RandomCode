public class test {
    private static WeightedDirectedGraph lectureGraph() {
        WeightedDirectedGraph wdg = new WeightedDirectedGraph(7);
        /* Edges from vertex 0. */
        wdg.addEdge(0, 1, 2);
        wdg.addEdge(0, 2, 1);

        wdg.addEdge(1, 2, 5);
        wdg.addEdge(1, 3, 11);
        wdg.addEdge(1, 4, 3);

        wdg.addEdge(2, 5, 15);

        wdg.addEdge(3, 4, 2);

        wdg.addEdge(4, 2, 1);
        wdg.addEdge(4, 5, 4);
        wdg.addEdge(4, 6, 5);

        wdg.addEdge(6, 3, 1);
        wdg.addEdge(6, 5, 1);
        return wdg;
    }

    private static WeightedDirectedGraph lectureGraph2() {
        WeightedDirectedGraph wdg = new WeightedDirectedGraph(6);
        /* Edges from vertex 0. */
        wdg.addEdge(0, 1, 50);
        wdg.addEdge(0, 2, 20);

        wdg.addEdge(1, 4, 20);

        wdg.addEdge(2, 3, 10);

        wdg.addEdge(3, 4, 70);

        wdg.addEdge(4, 3, 10);
        wdg.addEdge(4, 5, 100);
        return wdg;
    }
    public static void main(String[] args)
    {
        Dijkstra<Integer> p = new Dijkstra<>(0, new IntegerHopGraph());
        p.printTree();
    }
}
