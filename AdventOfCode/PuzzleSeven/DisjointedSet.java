package AdventOfCode.PuzzleSeven;
import java.util.HashMap;

public class DisjointedSet {
    private HashMap<String, String> parents = new HashMap<>();

    public DisjointedSet() {}

    public void add(String h) {
        parents.put(h, "");
    }

    public boolean containsVertex(String h) {
        return parents.containsKey(h);
    }
}
