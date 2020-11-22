import java.util.ArrayList;
import java.util.HashMap;

public class Dijkstra<Vertex> {
    private HashMap<Vertex, NodeVertex> vertices = new HashMap<>();
    private ArrayHeapMinPQ<Vertex> pq = new ArrayHeapMinPQ<>();
    private Vertex start;

    public Dijkstra(Vertex start, Graph<Vertex> graph) {
        this.start = start;
        vertices.put(start, new NodeVertex(start, null, 0, false));
        pq.add(start, 0.0);
        shortestPaths(vertices.get(start), graph, 0);
    }

    private void shortestPaths(NodeVertex current, Graph<Vertex> graph, int count) {
        pq.removeSmallest();
        for (WeightedEdge<Vertex> edge : graph.neighbors(current.vertex)) {
            Vertex start = edge.from();
            Vertex toward = edge.to();
            NodeVertex towardNode = vertices.get(toward);
            if (towardNode == null || !towardNode.marked) {
                if (vertices.containsKey(toward) && current.distance + edge.weight() < towardNode.distance) {
                    pq.changePriority(towardNode.vertex, current.distance + edge.weight());
                    towardNode.distance = current.distance + edge.weight();
                    towardNode.edgeTo = current.vertex;
                } else if (!vertices.containsKey(toward)) {
                    vertices.put(toward, new NodeVertex(toward, start, current.distance + edge.weight(), false));
                    towardNode = vertices.get(toward);
                    pq.add(towardNode.vertex, current.distance + edge.weight());
                }
            }
        }
        if (pq.size() == 0 || count > 2500) {
            return;
        }
        NodeVertex smallestVertex = vertices.get(pq.getSmallest());
        smallestVertex.marked = true;
        shortestPaths(smallestVertex, graph, count + 1);
    }

    private void printEdges() {
        for (NodeVertex v : vertices.values()) {
            System.out.print(v.vertex + "" + v.edgeTo + " ");
        }
    }

    public void printTree() {
        printTree(vertices.get(start), "");
    }

    private void printTree(NodeVertex current, String spaces) {
        ArrayList<NodeVertex> branches = new ArrayList<>();
        for (NodeVertex v : vertices.values()) {
            if (v.edgeTo == null) {
                continue;
            }
            if (v.edgeTo.equals(current.vertex)) {
                branches.add(v);
            }
        }
        System.out.println(spaces + current.vertex + "(" + current.distance + ")");
        if (branches.size() == 0) {
            return;
        }
        for (NodeVertex v : branches) {
            printTree(v, spaces + "   ");
        }
    }


    private class NodeVertex {
        private Vertex vertex, edgeTo;
        private double distance;
        private boolean marked;

        public NodeVertex(Vertex v, Vertex e, double d, boolean marked) {
            this.vertex = v;
            this.edgeTo = e;
            this.distance = d;
            this.marked = marked;
        }
    }
}
