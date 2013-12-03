package main.graph;

/**
 * User: filip
 * Date: 02.12.13
 * Time: 09:31
 */
public class NodeEdge<N, E> {

    private final N node;
    private final E edge;

    public NodeEdge(N node, E Edge) {
        this.node = node;
        this.edge = Edge;
    }

    public E getEdge() {
        return edge;
    }

    public N getNode() {
        return node;
    }

}
