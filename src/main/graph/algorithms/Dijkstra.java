package main.graph.algorithms;

import main.graph.Graph;
import main.graph.NodeEdge;
import main.graph.example.DoubleWeightedEdge;

import java.util.*;

/**
 * User: filip
 * Date: 08.12.13
 * Time: 16:56
 */
public class Dijkstra<N> {

    private PriorityQueue<DijkstraNode<N>> unvisited;
    private Map<N, DijkstraNode<N>> all;
    private final Graph<N, DoubleWeightedEdge> graph;
    private DijkstraNode<N> current;
    private N from;
    private N to;

    public Dijkstra(Graph<N, DoubleWeightedEdge> g) {
        graph = g;
    }

    public double init(N from, N to) {
        this.from = from;
        this.to = to;
        Set<DijkstraNode<N>> dijkstraNodes = new HashSet<>();
        all = new HashMap<>();
        for (N n : graph.getNodes()) {
            if (n.equals(from)) {
                DijkstraNode<N> dijkstraNode = new DijkstraNode(n, 0);
                dijkstraNodes.add(dijkstraNode);
                all.put(n, dijkstraNode);
                current = dijkstraNode;
            } else {
                DijkstraNode<N> dijkstraNode = new DijkstraNode(n, Double.POSITIVE_INFINITY);
                dijkstraNodes.add(dijkstraNode);
                all.put(n, dijkstraNode);
            }
        }
        unvisited = new PriorityQueue<>(dijkstraNodes);
        return run();
    }

    private double run() {
        DijkstraNode<N> dn = all.get(to);
        while (unvisitedLeft()) {
            calculateNeighbours();
            current = unvisited.peek();
        }
        for (DijkstraNode<N> dn2 : all.values()) {
            System.out.println("From: " + this.from + " to: " + dn2.getN() + " = " + dn2.dist);
        }
        System.out.println("");
        return all.get(to).getDist();
    }

    private void calculateNeighbours() {
        Set<NodeEdge<N, DoubleWeightedEdge>> nexts = graph.getNextNodeEdges(current.getN());
        for (NodeEdge<N, DoubleWeightedEdge> next : nexts) {
            DijkstraNode<N> dn = all.get(next.getNode());
            if (unvisited.contains(dn)) {
                double newDist = current.getDist() + next.getEdge().getWeight();
                if (newDist < dn.getDist()) {
                    unvisited.remove(dn);
                    dn.setDist(newDist);
                    unvisited.add(dn);
                }
            }
        }
        unvisited.remove(current);
    }

    private boolean unvisitedLeft() {
        return !(unvisited.isEmpty() || unvisited.peek().getDist() == Double.POSITIVE_INFINITY);
    }

    private class DijkstraNode<N> implements Comparable<DijkstraNode<N>> {

        private final N n;
        private double dist;

        private DijkstraNode(N n, double dist) {
            this.n = n;
            this.dist = dist;
        }

        private N getN() {
            return n;
        }

        private double getDist() {
            return dist;
        }

        private void setDist(double dist) {
            this.dist = dist;
        }

        @Override
        public int compareTo(DijkstraNode<N> o) {
            return Double.compare(this.dist, o.getDist());
        }

    }

}
