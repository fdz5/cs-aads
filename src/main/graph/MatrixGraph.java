package main.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MatrixGraph<N, E> implements Graph<N, E> {

    private AbstractEdge[][] adjMatrix;
    private List<N> nodes;
    private Set<E> edges;

    public MatrixGraph() {
        adjMatrix = new AbstractEdge[0][0];
        nodes = new ArrayList<>();
        edges = new HashSet<>();
    }

    @Override
    public void addNode(N n) {
        if (nodes.contains(n))
            throw new IllegalArgumentException("LabeledNode already exists");
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
        AbstractEdge<E> ae = new AbstractEdge<>(e);
        adjMatrix[id(n1)][id(n2)] = ae;
    }

    @Override
    public void deleteEdge(N n1, N n2) {
        if (!nodes.contains(n1) || !nodes.contains(n2))
            throw new IllegalArgumentException("Nodes do not exist in the graph");
        adjMatrix[id(n1)][id(n2)] = null;
    }

    @Override
    public Set<N> getNeighboringNodes(N n) {
        Set<N> neighbours = new HashSet<>();
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                if (adjMatrix[i][j] != null) {
                    N f1 = nodes.get(i);
                    N f2 = nodes.get(j);
                    if (f1.equals(n))
                        neighbours.add(f2);
                    else if (f2.equals(n))
                        neighbours.add(f1);
                }
            }
        }
        return neighbours;
    }

    @Override
    public Set<E> getNeighboringEdges(N n) {
        Set<E> neighbours = new HashSet<>();
        AbstractEdge<E>[] row = adjMatrix[id(n)];
        for (int i = 0; i < row.length; i++) {
            if (row[i] != null)
                neighbours.add(row[i].getEdge());
        }
        // TODO add edges from columns

//        for (int i = 0; i < adjMatrix.length; i++) {
//            if (row[i][id(n)] != null)
//
//            if (row[i] != null)
//                neighbours.add(row[i].getEdge());
//        }
        return null;
    }

    @Override
    public boolean findNode(N n) {
        return nodes.contains(n);
    }

    @Override
    public boolean findEdge(E e) {
        return edges.contains(e);
    }

    @Override
    public List<N> findEnds(E n) {
        // TODO Auto-generated method stub
        return null;
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
                    sum++;
            }
        }
        return sum;
    }

    @Override
    public boolean areNeighbours(N n1, N n2) {
        return (adjMatrix[id(n1)][id(n2)] != null || adjMatrix[id(n2)][id(n1)] != null);
    }


    private void enlarge() {
        int size = adjMatrix.length + 1;
        AbstractEdge[][] target = new AbstractEdge[size][size];
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                target[i][j] = adjMatrix[i][j];
            }
        }
        adjMatrix = target;
    }

    private void reduce(int del) {
        int size = adjMatrix.length - 1;
        AbstractEdge[][] target = new AbstractEdge[size][size];
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
