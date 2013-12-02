package main.graph;

import java.util.*;

public class ListGraph<N, E> implements Graph<N, E> {

    Map<N, AbstractNode> graph;
    List<N> nodes;
    List<E> edges;
    // TODO add edges map

    public ListGraph() {
        graph = new HashMap<>();
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    @Override
    public void addNode(N n) {
        if (nodes.contains(n))
            throw new IllegalArgumentException("Node already exists");
        nodes.add(n);
        AbstractNode an = new AbstractNode(n);
        graph.put(n, an);
    }

    @Override
    public void deleteNode(N n) {
        if (!nodes.contains(n))
            throw new IllegalArgumentException("Node does not exist");
        AbstractNode<N, E> an = graph.get(n);
        for (NodeEdge ne : an.getNexts()) {
            edges.remove(ne.getEdge());
        }
        graph.remove(n);
        nodes.remove(n);
    }

    @Override
    public void addEdge(E e, N n1, N n2) {
        if (edges.contains(e))
            throw new IllegalArgumentException("Edge already exists in the graph");
        edges.add(e);
        AbstractNode an = new AbstractNode(n1);
        an.addNextNode(n2, e);
        graph.put(n1, an);
    }

    @Override
    public void deleteEdge(E e) {
        if (!edges.contains(e))
            throw new IllegalArgumentException("Node does not exist in the graph");

        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Set<N> getNeighboringNodes(N n) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Set<N> getNextNodes(N n) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Set<N> getPreviousNodes(N n) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Set<E> getNeighboringEdges(N n) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean findNode(N n) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean findEdge(E e) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<N> findEnds(E e) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int countNodes() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int countEdges() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean areNeighbours(N n1, N n2) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
