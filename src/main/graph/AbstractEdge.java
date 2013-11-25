package main.graph;

public class AbstractEdge<N, E> {
	
	private E edge;
	private N start;
	private N end;

	public AbstractEdge(E edge, N start, N end) {
		super();
		this.edge = edge;
		this.start = start;
		this.end = end;
	}

}
