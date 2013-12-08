package main.graph.algorithms;

import main.graph.Graph;
import main.graph.NodeEdge;
import main.graph.example.IntegerWeightedEdge;

import java.util.*;

/**
 * User: filip
 * Date: 08.12.13
 * Time: 16:56
 */
public class Dijkstra<N> {

    private PriorityQueue<DijkstraNode<N>> unvisited;
    private Map<N, DijkstraNode<N>> all;
    private final Graph<N, IntegerWeightedEdge> graph;
    private DijkstraNode<N> current;
    private N to;

    public Dijkstra(Graph<N, IntegerWeightedEdge> g, N from, N to) {
        graph = g;
        this.to = to;
        Set<DijkstraNode<N>> dijkstraNodes = new HashSet<>();
        all = new HashMap<>();
        for (N n : g.getNodes()) {
            if (n.equals(from)) {
                DijkstraNode<N> dijkstraNode = new DijkstraNode(n, 0);
                dijkstraNodes.add(dijkstraNode);
                all.put(n, dijkstraNode);
                current = dijkstraNode;
            } else {
                DijkstraNode<N> dijkstraNode = new DijkstraNode(n, Integer.MAX_VALUE);
                dijkstraNodes.add(dijkstraNode);
                all.put(n, dijkstraNode);
            }
        }
        unvisited = new PriorityQueue<>(dijkstraNodes);
    }

    public int run() {
        DijkstraNode<N> dn = all.get(to);
        while (unvisited.contains(dn)) {
            calculateNeighbours();
            current = unvisited.peek();
        }
        for (DijkstraNode<N> dn2 : all.values()) {
            System.out.println(dn2.getN() + ": " + dn2.dist);
        }
        return all.get(to).getDist();
    }

    private void calculateNeighbours() {
        Set<NodeEdge<N, IntegerWeightedEdge>> nexts = graph.getNextNodeEdges(current.getN());
        for (NodeEdge<N, IntegerWeightedEdge> next : nexts) {
            DijkstraNode<N> dn = all.get(next.getNode());
            if (unvisited.contains(dn)) {
                int newDist = current.getDist() + next.getEdge().getWeight();
                if (newDist < dn.getDist()) {
                    unvisited.remove(dn);
                    dn.setDist(newDist);
                    unvisited.add(dn);
                }
            }
        }
        unvisited.remove(current);
    }

    private class DijkstraNode<N> implements Comparable<DijkstraNode<N>> {

        private final N n;
        private int dist;

        private DijkstraNode(N n, int dist) {
            this.n = n;
            this.dist = dist;
        }

        private N getN() {
            return n;
        }

        private int getDist() {
            return dist;
        }

        private void setDist(int dist) {
            this.dist = dist;
        }

        @Override
        public int compareTo(DijkstraNode<N> o) {
            return Integer.compare(this.dist, o.getDist());
        }

    }

}
