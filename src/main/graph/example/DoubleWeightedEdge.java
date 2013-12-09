package main.graph.example;

import main.graph.AbstractWeightedEdge;

/**
 * User: filip
 * Date: 02.12.13
 * Time: 00:00
 */
public class DoubleWeightedEdge extends AbstractWeightedEdge<Double> {

    public DoubleWeightedEdge(Double weight) {
        super(weight);
    }

    @Override
    public Double addDist(Double w1, Double w2) {
        return w1 + w2;
    }

    @Override
    public int compareTo(AbstractWeightedEdge<Double> other) {
        return Double.compare(this.getWeight(), other.getWeight());
    }

}
