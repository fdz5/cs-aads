package main.graph;

import java.util.ArrayList;
import java.util.List;

public class AbstractEdge<E> {
	
	private final List<E> edges;

	public AbstractEdge(E edge) {
		edges = new ArrayList<>();
        edges.add(edge);
	}

    public void add(E e) {
        edges.add(e);
    }

    public AbstractEdge copy() {
        return new AbstractEdge<>(edges);
    }

    public void delEdge(E e) {
        edges.remove(e);
    }

    public List<E> getEdges() {
        return edges;
    }
}
