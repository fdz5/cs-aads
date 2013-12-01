package main.graph;

public class AbstractEdge<E> {
	
	private final E edge;

	public AbstractEdge(E edge) {
		this.edge = edge;
	}

    public AbstractEdge copy() {
        return new AbstractEdge<E>(edge);
    }

    public E getEdge() {
        return edge;
    }

}
