package main.graph;

import java.util.List;

public class AbstractNode<N, E> {
	
	private N node;
	private List<E> from;
	private List<E> to;

	public AbstractNode(N node) {
		super();
		this.node = node;
	}
	
}
