package main.graph;

import java.util.*;

/**
 * Neighbourhood list implementation of the Graph interface.
 *
 * @param <N> Node class (nodes must be unique in term of equals method)
 * @param <E> Edge class (edges must be unique in term of equals method)
 * @author Filip Dziedzic
 */
public class ListGraph<N, E> implements Graph<N, E> {

    Map<N, AbstractNode<N, E>> graph;
    Map<E, N> edge2StNode;

    public ListGraph() {
        graph = new HashMap<>();
        edge2StNode = new HashMap<>();
    }

    @Override
    public void addNode(N n) {
        if (graph.containsKey(n))
            throw new IllegalArgumentException("Node already exists");
        AbstractNode<N, E> an = new AbstractNode(n);
        graph.put(n, an);
    }

    @Override
    public void deleteNode(N n) {
        if (!graph.containsKey(n))
            throw new IllegalArgumentException("Node does not exist");
        AbstractNode<N, E> an = graph.get(n);
        for (NodeEdge ne : an.getNexts()) {
            edge2StNode.remove(ne.getEdge());
        }
        graph.remove(n);
    }

    @Override
    public void addEdge(E e, N n1, N n2) {
        if (edge2StNode.containsKey(e))
            throw new IllegalArgumentException("Edge already exists in the graph");
        AbstractNode<N, E> an = graph.get(n1);
        an.addNextNode(n2, e);
        edge2StNode.put(e, n1);
        graph.put(n1, an);
    }

    @Override
    public void deleteEdge(E e) {
        if (!edge2StNode.containsKey(e))
            throw new IllegalArgumentException("Edge does not exist in the graph");
        N n = edge2StNode.get(e);
        AbstractNode<N, E> an = graph.get(n);
        an.removeNextNode(e);
        edge2StNode.remove(e);
    }

    @Override
    public Set<N> getNeighboringNodes(N n) {
        if (!graph.containsKey(n))
            throw new IllegalArgumentException("Node does not exists in grap");
        Set<N> neighbours = new HashSet<>(getPreviousNodes(n));
        neighbours.addAll(getNextNodes(n));
        return neighbours;
    }

    @Override
    public Set<N> getNextNodes(N n) {
        if (!graph.containsKey(n))
            throw new IllegalArgumentException("Node does not exists in grap");
        HashSet<N> nexts = new HashSet<>();
        for (NodeEdge<N, E> ne : graph.get(n).getNexts()) {
            nexts.add(ne.getNode());
        }
        return new HashSet<>(nexts);
    }

    @Override
    public Set<N> getPreviousNodes(N n) {
        if (!graph.containsKey(n))
            throw new IllegalArgumentException("Node does not exists in grap");
        Iterator<AbstractNode<N, E>> iter = graph.values().iterator();
        Set<N> neighbours = new HashSet<>();
        while (iter.hasNext()) {
            AbstractNode<N, E> an = iter.next();
            for (NodeEdge<N, E> ne : an.getNexts()) {
                if (ne.getNode().equals(n))
                    neighbours.add(an.getNode());
            }
        }
        return neighbours;
    }

    @Override
    public Set<E> getNeighboringEdges(N n) {
        return null;
    }

    @Override
    public boolean containsNose(N n) {
        return graph.containsKey(n);
    }

    @Override
    public boolean containsEdge(E e) {
        return edge2StNode.containsKey(e);
    }

    @Override
    public List<N> findEnds(E e) {
        if (!edge2StNode.containsKey(e))
            throw new IllegalArgumentException("Edge does not exist in the graph");
        List<N> ends = new ArrayList<>(2);
        N start = edge2StNode.get(e);
        ends.add(start);

        AbstractNode<N, E> an = graph.get(start);
        for (NodeEdge<N, E> ne : an.getNexts()) {
            if (ne.getEdge().equals(e)) {
                ends.add(ne.getNode());
            }
        }

        return ends;
    }

    @Override
    public int countNodes() {
        return graph.size();
    }

    @Override
    public int countEdges() {
        return edge2StNode.size();
    }

    @Override
    public boolean areNeighbours(N n1, N n2) {
        if (!graph.containsKey(n1) || !graph.containsKey(n2))
            throw new IllegalArgumentException("Both nodes must exist in graph");
        return isNext(n1, n2) || isNext(n2, n1);
    }

    @Override
    public Set<NodeEdge<N, E>> getNextNodeEdges(N n) {
        return new HashSet<>(graph.get(n).getNexts());
    }

    @Override
    public Set<N> getNodes() {
        return this.graph.keySet();
    }

    private boolean isNext(N n1, N n2) {
        AbstractNode<N, E> an = graph.get(n1);
        for (NodeEdge<N, E> ne : an.getNexts()) {
            if (ne.getNode().equals(n2))
                return true;
        }
        return false;
    }

}
