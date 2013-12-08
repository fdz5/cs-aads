package main.graph;

/**
 * User: filip
 * Date: 08.12.13
 * Time: 17:09
 */
public abstract class AbstractWeightedEdge<W> implements Comparable<AbstractWeightedEdge<W>> {

    protected final W weight;

    public AbstractWeightedEdge(W weight) {
        this.weight = weight;
    }

    public abstract W addDist(W w1, W w2);

    public W getWeight() {
        return weight;
    }

    @Override
    public abstract int compareTo(AbstractWeightedEdge<W> other);

}
