import java.util.List;
public interface Graph<Vertex> {
    List<WeightedEdge<Vertex>> neighbors(Vertex v);
}
