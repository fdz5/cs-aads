package main.graph;

/**
 * User: filip
 * Date: 02.12.13
 * Time: 09:31
 */
public class NodeEdge<N, E> {

    private final E edge;
    private final N node;

    public NodeEdge(E Edge, N node) {
        this.edge = Edge;
        this.node = node;
    }

    public E getEdge() {
        return edge;
    }

    public N getNode() {
        return node;
    }

}
