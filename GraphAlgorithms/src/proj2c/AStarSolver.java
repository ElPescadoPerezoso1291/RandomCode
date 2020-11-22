package bearmaps.proj2c;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private ArrayHeapMinPQ<Vertex> pq = new ArrayHeapMinPQ<>();
    private double weight = 0;
    private int statesExplored = 0;
    private List<Vertex> sol = new ArrayList<>();
    private HashMap<Vertex, Double> distances = new HashMap<>();
    private HashMap<Vertex, Vertex> edgeTo = new HashMap<>();
    private double time = 0;
    private SolverOutcome solved;
    private Stopwatch sw;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        sw = new Stopwatch();
        pq.add(start, 0.0);
        distances.put(start, 0.0);
        statesExplored++;
        while (pq.size() != 0 && sw.elapsedTime() < timeout) {
            Vertex p = pq.removeSmallest();
            if (p.equals(end)) {
                weight = distances.get(end);
                solved = SolverOutcome.SOLVED;
                time = sw.elapsedTime();
                sol.add(end);
                Vertex e = edgeTo.get(end);
                while (e != null) {
                    sol.add(0, e);
                    e = edgeTo.get(e);
                }
                return;
            }
            statesExplored++;
            for (WeightedEdge<Vertex> v : input.neighbors(p)) {
                relax(v, end, input);
            }
        }
        if (pq.size() == 0) {
            solved = SolverOutcome.UNSOLVABLE;
            sol = new ArrayList<>();
        } else {
            solved = SolverOutcome.TIMEOUT;
            sol = new ArrayList<>();
        }
    }

    private void relax(WeightedEdge<Vertex> v, Vertex goal, AStarGraph<Vertex> input) {
        Vertex p = v.from();
        Vertex q = v.to();
        double w = v.weight();
        if (distances.get(p) + w < distances.getOrDefault(q, Double.MAX_VALUE)) {
            distances.put(q, distances.get(p) + w);
            edgeTo.put(q, p);
            if (pq.contains(q)) {
                pq.changePriority(q, distances.get(q) + input.estimatedDistanceToGoal(q, goal));
            } else {
                pq.add(q, input.estimatedDistanceToGoal(q, goal) + distances.get(q));
            }
        }
    }

    @Override
    public SolverOutcome outcome() {
        return solved;
    }

    @Override
    public List<Vertex> solution() {
        return this.sol;
    }

    @Override
    public double solutionWeight() {
        return this.weight;
    }

    @Override
    public int numStatesExplored() {
        return this.statesExplored;
    }

    @Override
    public double explorationTime() {
        return this.time;
    }
}
