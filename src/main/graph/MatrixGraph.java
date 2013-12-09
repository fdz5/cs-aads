package main.graph;

import main.graph.example.LabeledNode;

import java.util.*;

/**
 * Adjacency matrix implementation of the Graph interface.
 * Performance is really poor...
 *
 * @param <N> Node class (nodes must be unique in term of equals method)
 * @param <E> Edge class (edges must be unique in term of equals method)
 * @author Filip Dziedzic
 */
public class MatrixGraph<N, E> implements Graph<N, E> {

    private AbstractEdge<E>[][] adjMatrix;
    private List<N> nodes;
    private Set<E> edges;

    public MatrixGraph() {
        adjMatrix = new AbstractEdge[0][0];
        nodes = new ArrayList<>();
        edges = new HashSet<>();
    }

    @Override
    public void build(int[][] edges) {
//        for (int i=0; i<edges.length; i++) {
//            for (int j=0; j<edges[i].length; j++) {
//                LabeledNode n = new LabeledNode(String.valueOf(i));
//            }
//        }
//        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addNode(N n) {
        if (nodes.contains(n))
            throw new IllegalArgumentException("Node already exists");
        nodes.add(n);
        enlarge();
    }

    @Override
    public void deleteNode(N n) {
        reduce(id(n));
        nodes.remove(n);
    }

    @Override
    public void addEdge(E e, N n1, N n2) {
        if (!nodes.contains(n1) || !nodes.contains(n2))
            throw new IllegalArgumentException("Nodes do not exist in the graph");
        if (!edges.contains(e))
            edges.add(e);
        AbstractEdge<E> ae = adjMatrix[id(n1)][id(n2)];
        if (ae != null) {
            ae.add(e);
        } else {
            ae = new AbstractEdge<>(e);
            adjMatrix[id(n1)][id(n2)] = ae;
        }
    }

    @Override
    public void deleteEdge(E e) {
        if (!edges.contains(e))
            throw new IllegalArgumentException("Edge does not exist in the graph");
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                if (adjMatrix[i][j] != null) {
                    for (E currE : adjMatrix[i][j].getEdges()) {
                        if (currE.equals(e)) {
                            adjMatrix[i][j].delEdge(currE);
                            if (adjMatrix[i][j].getEdges().size() == 0) {
                                adjMatrix[i][j] = null;
                                edges.remove(e);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public Set<N> getNextNodes(N n) {
        Set<N> nexts = new HashSet<>();
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                if (adjMatrix[i][j] != null) {
                    N f1 = nodes.get(i);
                    N f2 = nodes.get(j);
                    if (f1.equals(n))
                        nexts.add(f2);
                }
            }
        }
        return nexts;
    }

    @Override
    public Set<N> getPreviousNodes(N n) {
        Set<N> prevs = new HashSet<>();
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                if (adjMatrix[i][j] != null) {
                    N f1 = nodes.get(i);
                    N f2 = nodes.get(j);
                    if (f2.equals(n))
                        prevs.add(f1);
                }
            }
        }
        return prevs;
    }

    @Override
    public Set<N> getNeighboringNodes(N n) {
        Set<N> neighbours = getPreviousNodes(n);
        neighbours.addAll(getNextNodes(n));
        return neighbours;
    }

    @Override
    public Set<E> getNeighboringEdges(N n) {
//        Set<E> neighbours = new HashSet<>();
//        AbstractEdge<E>[] row = adjMatrix[id(n)];
//        for (int i = 0; i < row.length; i++) {
//            if (row[i] != null)
//                neighbours.add(row[i].getEdges());
//        }
//        // TODO add edges from columns
//
////        for (int i = 0; i < adjMatrix.length; i++) {
////            if (row[i][id(n)] != null)
////
////            if (row[i] != null)
////                neighbours.add(row[i].getEdges());
////        }
        return null;
    }

    @Override
    public boolean containsNose(N n) {
        return nodes.contains(n);
    }

    @Override
    public boolean containsEdge(E e) {
        return edges.contains(e);
    }

    @Override
    public List<N> findEnds(E e) {
        if (!edges.contains(e))
            throw new IllegalArgumentException("Edge does not exist in the graph");
        List<N> ends = new ArrayList<>();
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                if (adjMatrix[i][j] != null) {
                    for (E currE : adjMatrix[i][j].getEdges()) {
                        if (currE.equals(e)) {
                            ends.add(nodes.get(i));
                            ends.add(nodes.get(j));
                            break;
                        }
                    }
                }
            }
        }
        return ends;
    }

    @Override
    public int countNodes() {
        return nodes.size();
    }

    @Override
    public int countEdges() {
        int sum = 0;
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                if (adjMatrix[i][j] != null)
                    for (E ignored : adjMatrix[i][j].getEdges())
                        sum++;
            }
        }
        return sum;
    }

    @Override
    public boolean areNeighbours(N n1, N n2) {
        return (adjMatrix[id(n1)][id(n2)] != null || adjMatrix[id(n2)][id(n1)] != null);
    }

    @Override
    public Set<NodeEdge<N, E>> getNextNodeEdges(N n) {
        Set<NodeEdge<N, E>> nexts = new HashSet<>();
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                if (adjMatrix[i][j] != null) {
                    N f1 = nodes.get(i);
                    N f2 = nodes.get(j);
                    if (f1.equals(n)) {
                        E shortest = getShortest(adjMatrix[i][j].getEdges());
                        nexts.add(new NodeEdge<>(f2, shortest));
                    }
                }
            }
        }
        return nexts;
    }

    private E getShortest(List<E> edges) {
        PriorityQueue<E> queue = new PriorityQueue<>(edges);
        return queue.peek();
    }

    @Override
    public Set<N> getNodes() {
        return new HashSet<>(nodes);
    }


    private void enlarge() {
        int size = adjMatrix.length + 1;
        AbstractEdge<E>[][] target = new AbstractEdge[size][size];
        for (int i = 0; i < adjMatrix.length; i++) {
            System.arraycopy(adjMatrix[i], 0, target[i], 0, adjMatrix.length);
        }
        adjMatrix = target;
    }

    private void reduce(int del) {
        int size = adjMatrix.length - 1;
        AbstractEdge<E>[][] target = new AbstractEdge[size][size];
        int p = 0;
        for (int i = 0; i < adjMatrix.length; ++i) {
            if (i == del)
                continue;
            int q = 0;
            for (int j = 0; j < adjMatrix.length; ++j) {
                if (j == del)
                    continue;
                target[p][q] = adjMatrix[i][j];
                ++q;
            }
            ++p;
        }
        adjMatrix = target;
    }

    private int id(N n) {
        return nodes.indexOf(n);
    }


}
