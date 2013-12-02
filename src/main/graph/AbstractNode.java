package main.graph;

import java.util.List;

public class AbstractNode<N, E> {

    private final N node;
//    private List<NodeEdge> prevs;
    private List<NodeEdge> nexts;

    public AbstractNode(N node) {
        super();
        this.node = node;
    }

//    public void addPrevNode(N n, E e) {
//        NodeEdge ne = new NodeEdge(n, e);
//        prevs.add(ne);
//    }

    public void addNextNode(N n, E e) {
        NodeEdge ne = new NodeEdge(n, e);
        nexts.add(ne);
    }

    public N getNode() {
        return node;
    }

 //   public List<NodeEdge> getPrevs() {
 //       return prevs;
 //   }

    public List<NodeEdge> getNexts() {
        return nexts;
    }

}
