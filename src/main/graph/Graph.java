package main.graph;

import java.util.List;
import java.util.Set;

/**
 * Simple Graph interface made for CS course at AGH-UST
 *
 * @param <N> Node type
 * @param <E> Edge type
 * @author Filip Dziedzic
 */
public interface Graph<N, E> {

    /**
     * Builds graph from the adj matrix
     *
     * @param edges
     */
    public void build(int[][] edges);

    /**
     * Adds new node to the graph, duplicates (by means of equals function) are not possible
     *
     * @param n
     */
    public void addNode(N n);

    /**
     * Deletes the node from the graph
     *
     * @param n
     */
    public void deleteNode(N n);

    /**
     * Add edge to the graph between n1 (start node) and n2 (end node).
     * It's important since the directed nature of the graph.
     * Edges like nodes have to be unique.
     *
     * @param e
     * @param n1
     * @param n2
     */
    public void addEdge(E e, N n1, N n2);

    /**
     * Deletes edge fro the graph.
     *
     * @param e
     */
    public void deleteEdge(E e);

    /**
     * Returns all neighbours to the current node (previous and next nodes).
     *
     * @param n
     * @return
     */
    public Set<N> getNeighboringNodes(N n);

    /**
     * Returns only next neighbours to the specified node.
     *
     * @param n
     * @return
     */
    public Set<N> getNextNodes(N n);

    /**
     * Returns only previous nodes to the specified node.
     *
     * @param n
     * @return
     */
    public Set<N> getPreviousNodes(N n);

    public Set<E> getNeighboringEdges(N n);

    /**
     * Checks whether graph contains specified node.
     *
     * @param n
     * @return
     */
    public boolean containsNose(N n);

    /**
     * Checks whether graph contains specified edge.
     *
     * @param e
     * @return
     */
    public boolean containsEdge(E e);

    /**
     * Search for the start and end node of specified edge
     *
     * @param e
     * @return List containing two elements,
     *         first for start, second for end node.
     */
    public List<N> findEnds(E e);

    /**
     * Count nodes in the graph
     *
     * @return
     */
    public int countNodes();

    /**
     * count edges in the graph
     *
     * @return
     */
    public int countEdges();

    /**
     * Checks whether two nodes are neighbour to each other.
     * The order of the nodes does not matter.
     *
     * @param n1
     * @param n2
     * @return
     */
    public boolean areNeighbours(N n1, N n2);

    public Set<NodeEdge<N, E>> getNextNodeEdges(N n);

    public Set<N> getNodes();

}
