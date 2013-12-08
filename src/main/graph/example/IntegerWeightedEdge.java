package main.graph.example;

import main.graph.AbstractWeightedEdge;

/**
 * User: filip
 * Date: 02.12.13
 * Time: 00:00
 */
public class IntegerWeightedEdge extends AbstractWeightedEdge<Integer> {

    public IntegerWeightedEdge(Integer weight) {
        super(weight);
    }

    @Override
    public Integer addDist(Integer w1, Integer w2) {
        return w1 + w2;
    }

    @Override
    public int compareTo(AbstractWeightedEdge<Integer> other) {
        return Integer.compare(this.getWeight(), other.getWeight());
    }

}
