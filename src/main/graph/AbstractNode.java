package main.graph;

import java.util.ArrayList;
import java.util.List;

public class AbstractNode<N, E> {

    private final N node;
    private List<NodeEdge<N, E>> nexts;

    public AbstractNode(N node) {
        super();
        this.node = node;
        nexts = new ArrayList<>();
    }

    public void addNextNode(N n, E e) {
        nexts.add(new NodeEdge<>(n, e));
    }

    public void removeNextNode(E e) {
        for (NodeEdge<N, E> ne : nexts) {
            if (ne.getEdge().equals(e)) {
                removeNextNode(ne);
                break;
            }
        }
    }

    public void removeNextNode(NodeEdge ne) {
        nexts.remove(ne);
    }

    public N getNode() {
        return node;
    }

    public List<NodeEdge<N, E>> getNexts() {
        return nexts;
    }

}
